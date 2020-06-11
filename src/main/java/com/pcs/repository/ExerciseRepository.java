package com.pcs.repository;

import com.pcs.model.Exercise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


public interface ExerciseRepository extends Repository<Exercise, Integer> {

    void save(Exercise exercise);

    @Query("SELECT DISTINCT exercise FROM Exercise exercise WHERE exercise.name LIKE :name%")
    @Transactional(readOnly = true)
    Collection<Exercise> findByName(@Param("name") String name);


    @Query("SELECT exercise FROM Exercise exercise  WHERE exercise.id =:id")
    @Transactional(readOnly = true)
    Exercise findById(@Param("id") Integer id);
}

