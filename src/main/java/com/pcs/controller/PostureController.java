package com.pcs.controller;

import com.pcs.model.PostureLevel;
import com.pcs.model.User;
import com.pcs.repository.PostureRepository;
import com.pcs.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/users/{userId}")
public class PostureController {

    private static final String VIEWS_SYMPTOMS_CREATE_OR_UPDATE_FORM = "posture/createOrUpdatePostureForm";

    private final PostureRepository postureRepository;
    private final UserRepository userRepository;

    public PostureController(PostureRepository postureRepository, UserRepository userRepository) {
        this.postureRepository = postureRepository;
        this.userRepository = userRepository;
    }


    @ModelAttribute("user")
    public User findUser(@PathVariable("userId") int userId) {
        return this.userRepository.findById(userId);
    }

    @InitBinder("user")
    public void initUserBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/posture/new")
    public String initCreationForm(User user, ModelMap model) {
        PostureLevel postureLevel = new PostureLevel();
        user.addPosture(postureLevel);
        model.put("postureLevel", postureLevel);
        return VIEWS_SYMPTOMS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/posture/new")
    public String processCreationForm(User user, @Valid PostureLevel postureLevel, BindingResult result, ModelMap model) {

        user.addPosture(postureLevel);
        if (result.hasErrors()) {
            model.put("postureLevel", postureLevel);
            return VIEWS_SYMPTOMS_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.postureRepository.save(postureLevel);
            return "redirect:/users/{userId}";
        }
    }


}
