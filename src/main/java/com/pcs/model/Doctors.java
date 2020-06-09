package com.pcs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Doctors {

    private List<Doctor> doctors;

    @XmlElement
    public List<Doctor> getDoctorsList() {
        if (doctors == null) {
            doctors = new ArrayList<>();
        }
        return doctors;
    }
}
