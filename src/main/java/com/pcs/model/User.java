package com.pcs.model;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Symptom> symptom;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    protected Set<Symptom> getSymptomInternal() {
        if (this.symptom == null) {
            this.symptom = new HashSet<>();
        }
        return this.symptom;
    }

    protected void setSymptomInternal(Set<Symptom> symptom) {
        this.symptom = symptom;
    }

    public List<Symptom> getSymptom() {
        List<Symptom> sortedDisease = new ArrayList<>(getSymptomInternal());
        PropertyComparator.sort(sortedDisease, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedDisease);
    }

    public void addSymptom(Symptom symptom) {
        if (symptom.isNew()) {
            getSymptomInternal().add(symptom);
        }
        symptom.setUser(this);
    }

    public Symptom getSymptom(String name){
        return getSymptom(name, false);
    }

    public Symptom getSymptom(String name, boolean ignoreNew){
        name = name.toLowerCase();
        for (Symptom symptom : getSymptomInternal()) {
            if (!ignoreNew || !symptom.isNew()) {
                String compName = symptom.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return symptom;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId()).append("new", this.isNew()).append("lastName", this.getLastName())
                .append("firstName", this.getFirstName()).append("address", this.address).append("city", this.city)
                .append("telephone", this.telephone).toString();
    }

}
