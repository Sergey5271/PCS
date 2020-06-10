package com.pcs.controller;

import java.util.Map;

import javax.validation.Valid;


import com.pcs.model.Symptom;
import com.pcs.model.Visit;
import com.pcs.repository.SymptomRepository;
import com.pcs.repository.VisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VisitController {

    private final VisitRepository visitRepository;
    private final SymptomRepository symptomRepository;

    public VisitController(VisitRepository visitRepository, SymptomRepository symptomRepository) {
        this.visitRepository = visitRepository;
        this.symptomRepository = symptomRepository;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadSymptomWithVisit(@PathVariable("symptomId") int symptomId, Map<String, Object> model) {
        Symptom symptom = this.symptomRepository.findById(symptomId);
        symptom.setVisitsInternal(this.visitRepository.findBySymptomId(symptomId));
        model.put("symptom", symptom);
        Visit visit = new Visit();
        symptom.addVisit(visit);
        return visit;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/users/*/symptoms/{symptomId}/visits/new")
    public String initNewVisitForm(@PathVariable("symptomId") int symptomId, Map<String, Object> model) {
        return "symptoms/createOrUpdateVisitForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/users/{userId}/symptoms/{symptomId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "symptoms/createOrUpdateVisitForm";
        }
        else {
            this.visitRepository.save(visit);
            return "redirect:/users/{userId}";
        }
    }

}
