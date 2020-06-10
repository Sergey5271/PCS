package com.pcs.repository;

import com.pcs.model.Visit;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface VisitRepository extends Repository<Visit, Integer> {

    void save(Visit visit) throws DataAccessException;

    List<Visit> findBySymptomId(Integer symptomId);
}
