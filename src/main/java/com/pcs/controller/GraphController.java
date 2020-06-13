package com.pcs.controller;

import com.pcs.model.PostureLevel;
import com.pcs.repository.PostureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GraphController {

    private final PostureRepository postureRepository;

    public GraphController(PostureRepository postureRepository) {
        this.postureRepository = postureRepository;
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


//    @GetMapping("/displayGraph")
//    public String barGraph(Model model) {
//
//
//        Map<String, Integer> surveyMap = new LinkedHashMap<>();
//        surveyMap.put("08.06.2020", 8);
//        surveyMap.put("09.06.2020", 6);
//        surveyMap.put("10.06.2020", 5);
//        surveyMap.put("11.06.2020", 2);
//
//
//        Map<String, Integer> surveyMap1 = new LinkedHashMap<>();
//        surveyMap1.put("08.06.2020", -5);
//        surveyMap1.put("09.06.2020", -7);
//        surveyMap1.put("10.06.2020", -3);
//        surveyMap1.put("11.06.2020", 0);
//
//        model.addAttribute("surveyMap", surveyMap);
//        model.addAttribute("surveyMap1", surveyMap1);
//        return "postureGraph";
//    }


}
