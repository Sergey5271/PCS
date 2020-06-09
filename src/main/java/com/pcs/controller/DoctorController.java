package com.pcs.controller;
import com.pcs.model.Doctor;
import com.pcs.model.Doctors;
import com.pcs.repository.DoctorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/doctors.html")
    public String showDoctorList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping
        Doctors doctor = new Doctors();
        doctor.getDoctorsList().addAll(this.doctorRepository.findAll());
        model.put("doctors", doctor);
        return "doctors/doctorList";
    }

    @GetMapping({ "/doctors" })
    public @ResponseBody Doctors showResourcesDoctorList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for JSon/Object mapping
        Doctors doctors = new Doctors();
        doctors.getDoctorsList().addAll(this.doctorRepository.findAll());
        return doctors;
    }

}
