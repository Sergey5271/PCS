package com.pcs.controller;

import com.pcs.model.User;
import com.pcs.model.PostureLevel;
import com.pcs.repository.PostureRepository;
import com.pcs.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GraphController {

    private final PostureRepository postureRepository;
    private final UserRepository userRepository;

    public GraphController(PostureRepository postureRepository, UserRepository userRepository) {
        this.postureRepository = postureRepository;
        this.userRepository = userRepository;
    }

//    @GetMapping("/users/{userId}")
//    public ModelAndView showOwner(@PathVariable("userId") int userId) {
//        ModelAndView mav = new ModelAndView("users/userDetails");
//        User user = this.userRepository.findById(userId);
//        for (Symptom symptom : user.getSymptom()) {
//            symptom.setVisitsInternal(visitRepository.findBySymptomId(symptom.getId()));
//        }
//        mav.addObject(user);
//        return mav;
//    }

    @GetMapping("/listOfUsersGraph")
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
        else {
            // multiple owners found
            model.put("selections", results);
            return "listOfUsers";
        }
    }

    @GetMapping("/displayGraph/{userId}")
    public ModelAndView showGraphById(@PathVariable("userId") int userId) {
        ModelAndView mav = new ModelAndView("postureGraphDetails");
        //  User user = this.userRepository.findById(userId);

        List<PostureLevel> listOfPosture = postureRepository.findByUserId(userId);

        Map<LocalDate, Integer> surveyMap = new LinkedHashMap<>();

        for (PostureLevel level : listOfPosture) {
            surveyMap.put(level.getDate(), level.getBackFrontLevel());
        }

        Map<LocalDate, Integer> surveyMap1 = new LinkedHashMap<>();

        for (PostureLevel level : listOfPosture) {
            surveyMap1.put(level.getDate(), level.getBackSideLevel());
        }

        Map<LocalDate, Integer> surveyMap2 = new LinkedHashMap<>();

        for (PostureLevel level : listOfPosture) {
            surveyMap2.put(level.getDate(), level.getNeckLevel());
        }

        mav.addObject("surveyMap", surveyMap);
        mav.addObject("surveyMap1", surveyMap1);
        mav.addObject("surveyMap2", surveyMap2);
        return mav;
    }


    @GetMapping("/displayGraph")
    public String barGraph(Model model) {


        List<PostureLevel> listOfPosture = postureRepository.findAll();

        Map<LocalDate, Integer> surveyMap = new LinkedHashMap<>();

        for (PostureLevel level : listOfPosture) {
            surveyMap.put(level.getDate(), level.getBackFrontLevel());
        }

        Map<LocalDate, Integer> surveyMap1 = new LinkedHashMap<>();

        for (PostureLevel level : listOfPosture) {
            surveyMap1.put(level.getDate(), level.getBackSideLevel());
        }

        Map<LocalDate, Integer> surveyMap2 = new LinkedHashMap<>();

        for (PostureLevel level : listOfPosture) {
            surveyMap2.put(level.getDate(), level.getNeckLevel());
        }

        model.addAttribute("surveyMap", surveyMap);
        model.addAttribute("surveyMap1", surveyMap1);
        model.addAttribute("surveyMap2", surveyMap2);
        return "postureGraph";
    }


}
