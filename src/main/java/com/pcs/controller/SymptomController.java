package com.pcs.controller;

import com.pcs.model.Symptom;
import com.pcs.model.SymptomType;
import com.pcs.model.User;
import com.pcs.repository.SymptomRepository;
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
public class SymptomController {

    private static final String VIEWS_SYMPTOMS_CREATE_OR_UPDATE_FORM = "symptoms/createOrUpdateSymptomForm";

    private final SymptomRepository symptomRepository;
    private final UserRepository userRepository;

    public SymptomController(SymptomRepository symptomRepository, UserRepository userRepository) {
        this.symptomRepository = symptomRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute("types")
    public Collection<SymptomType> populateSymptomTypes() {
        return this.symptomRepository.findSymptomTypes();
    }

    @ModelAttribute("user")
    public User findUser(@PathVariable("userId") int userId) {
        return this.userRepository.findById(userId);
    }

    @InitBinder("user")
    public void initUserBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/symptoms/new")
    public String initCreationForm(User user, ModelMap model) {
        Symptom symptom = new Symptom();
        user.addSymptom(symptom);
        model.put("symptom", symptom);
        return VIEWS_SYMPTOMS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/symptoms/new")
    public String processCreationForm(User user, @Valid Symptom symptom, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(symptom.getName()) && symptom.isNew() && user.getSymptom(symptom.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        user.addSymptom(symptom);
        if (result.hasErrors()) {
            model.put("symptom", symptom);
            return VIEWS_SYMPTOMS_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.symptomRepository.save(symptom);
            return "redirect:/users/{userId}";
        }
    }

}
