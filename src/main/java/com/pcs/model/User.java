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
    private Set<Disease> diseases;

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

    protected Set<Disease> getDiseasesInternal() {
        if (this.diseases == null) {
            this.diseases = new HashSet<>();
        }
        return this.diseases;
    }

    protected void setDiseasesInternal(Set<Disease> diseases) {
        this.diseases = diseases;
    }

    public List<Disease> getDisease() {
        List<Disease> sortedDisease = new ArrayList<>(getDiseasesInternal());
        PropertyComparator.sort(sortedDisease, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedDisease);
    }

    public void addPet(Disease disease) {
        if (disease.isNew()) {
            getDiseasesInternal().add(disease);
        }
        disease.setUser(this);
    }

    public Disease getDisease(String name){
        return getDisease(name, false);
    }

    public Disease getDisease(String name, boolean ignoreNew){
        name = name.toLowerCase();
        for (Disease disease : getDiseasesInternal()) {
            if (!ignoreNew || !disease.isNew()) {
                String compName = disease.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return disease;
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
