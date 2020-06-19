package com.pcs.controller;

import com.pcs.model.Symptom;
import com.pcs.model.User;
import com.pcs.repository.UserRepository;
import com.pcs.repository.VisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;


@Controller
public class UserController {

    private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/createOrUpdateUserForm";

    private final UserRepository userRepository;
    private final VisitRepository visitRepository;

    public UserController(UserRepository userRepository, VisitRepository visitRepository) {
        this.userRepository = userRepository;
        this.visitRepository = visitRepository;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/users/new")
    public String initCreationForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/users/new")
    public String processCreationForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.userRepository.save(user);
            return "redirect:/users/" + user.getId();
        }
    }

    @GetMapping("/users/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("user", new User());
        return "users/findUsers";
    }

    @GetMapping("/users")
    public String processFindForm(User user, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /owners to return all records
        if (user.getLastName() == null) {
            user.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        Collection<User> results = this.userRepository.findByLastNames(user.getLastName());
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "users/findUsers";
        }
        else if (results.size() == 1) {
            // 1 owner found
            user = results.iterator().next();
            return "redirect:/users/" + user.getId();
        }
        else {
            // multiple owners found
            model.put("selections", results);
            return "users/userList";
        }
    }

    @GetMapping("/users/{userId}/edit")
    public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
        User user = this.userRepository.findById(userId);
        model.addAttribute(user);
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/users/{userId}/edit")
    public String processUpdateUserForm(@Valid User user, BindingResult result,
                                         @PathVariable("userId") int userId) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        }
        else {
            user.setId(userId);
            this.userRepository.save(user);
            return "redirect:/users/{userId}";
        }
    }


    @GetMapping("/users/{userId}")
    public ModelAndView showOwner(@PathVariable("userId") int userId) {
        ModelAndView mav = new ModelAndView("users/userDetails");
        User user = this.userRepository.findById(userId);
        for (Symptom symptom : user.getSymptom()) {
            symptom.setVisitsInternal(visitRepository.findBySymptomId(symptom.getId()));
        }
        mav.addObject(user);
        return mav;
    }
}
