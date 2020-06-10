package com.pcs.repository;

import com.pcs.model.Symptom;
import com.pcs.model.SymptomType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SymptomRepository extends Repository<Symptom, Integer> {

    @Query("SELECT stype FROM SymptomType stype ORDER BY stype.name")
    @Transactional(readOnly = true)
    List<SymptomType> findSymptomTypes();


    @Transactional(readOnly = true)
    Symptom findById(Integer id);


    void save(Symptom pet);

}
