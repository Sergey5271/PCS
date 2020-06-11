package com.pcs.controller;

import com.pcs.model.Exercise;
import com.pcs.repository.ExerciseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;


@Controller
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;


    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping("/exercises")
    public String processFindForm(Exercise exercise, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /owners to return all records
        if (exercise.getName() == null) {
            exercise.setName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        Collection<Exercise> results = this.exerciseRepository.findByName(exercise.getName());
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("name", "notFound", "not found");
            return "users/findUsers";
        }
        else if (results.size() == 1) {
            // 1 owner found
            exercise = results.iterator().next();
            return "redirect:/exercise/" + exercise.getId();
        }
        else {
            // multiple owners found
            model.put("selections", results);
            return "exercise/exerciseList";
        }
    }

    @GetMapping("/exercises/{exerciseId}")
    public ModelAndView showExercise(@PathVariable("exerciseId") int exerciseId) {
        ModelAndView mav = new ModelAndView("exercise/exerciseDetails");
        Exercise exercise = this.exerciseRepository.findById(exerciseId);

        mav.addObject(exercise);
        return mav;
    }

}
