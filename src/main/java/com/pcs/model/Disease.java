package com.pcs.model;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "diseases")
public class Disease extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private DiseaseType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private Set<Visit> visits = new LinkedHashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public DiseaseType getType() {
        return type;
    }

    public void setType(DiseaseType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected Set<Visit> getVisitsInternal() {
        if (this.visits == null) {
            this.visits = new HashSet<>();
        }
        return this.visits;
    }

    protected void setVisitsInternal(Collection<Visit> visits) {
        this.visits = new LinkedHashSet<>(visits);
    }

    public List<Visit> getVisits() {
        List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
        PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    public void addVisits(Visit visit) {
        getVisitsInternal().add(visit);
        visit.setDiseaseId(this.getId());
    }
}
