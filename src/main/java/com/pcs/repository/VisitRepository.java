package com.pcs.repository;

import com.pcs.model.Visit;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface VisitRepository extends Repository<Visit, Integer> {

    /**
     * Save a <code>Visit</code> to the data store, either inserting or updating it.
     *
     * @param visit the <code>Visit</code> to save
     * @see com.pcs.model.BaseEntity#isNew
     */
    void save(Visit visit) throws DataAccessException;

    List<Visit> findByDiseaseId(Integer userId);
}
