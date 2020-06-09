package com.pcs.model;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;
import javax.xml.bind.annotation.XmlElement;
@Entity
@Table(name = "doctors")
public class Doctor extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctor_specialties", joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties;

    protected Set<Specialty> getSpecialtiesInternal() {
        if (this.specialties == null) {
            this.specialties = new HashSet<>();
        }
        return this.specialties;
    }

    protected void setSpecialtiesInternal(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    @XmlElement
    public List<Specialty> getSpecialties() {
        List<Specialty> sortedSpecs = new ArrayList<>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    public int getNrOfSpecialties() {
        return getSpecialtiesInternal().size();
    }

    public void addSpecialty(Specialty specialty) {
        getSpecialtiesInternal().add(specialty);
    }



}
