package com.pcs.repository;

import com.pcs.model.Disease;
import com.pcs.model.DiseaseType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface DiseaseRepository extends Repository<Disease,Integer> {

    /**
     * Retrieve all {@link DiseaseType}s from the data store.
     * @return a Collection of {@link DiseaseType}s.
     */
    @Query("SELECT pdiseas FROM DiseaseType pdiseas ORDER BY pdiseas.name")
    @Transactional(readOnly = true)
    List<DiseaseType> findDiseaseType();

    /**
     * Retrieve a {@link Disease} from the data store by id.
     * @param id the id to search for
     * @return the {@link Disease} if found
     */
    @Transactional(readOnly = true)
    Disease findById(Integer id);

    /**
     * Save a {@link Disease} to the data store, either inserting or updating it.
     * @param pet the {@link Disease} to save
     */
    void save(Disease pet);

}
