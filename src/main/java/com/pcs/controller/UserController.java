package com.pcs.controller;

import com.pcs.model.User;
import com.pcs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;


@Controller
public class UserController {

    private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/createOrUpdateUserForm";

    private final UserRepository userRepository;


    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/user/new")
    public String initCreationForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/user/new")
    public String processCreationForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.userRepository.save(user);
            return "redirect:/users/" + user.getId();
        }
    }
}
