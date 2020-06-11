package com.pcs.repository;

import com.pcs.model.PostureLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostureRepository extends JpaRepository<PostureLevel, Integer> {

    Integer getByNeckLevel(Integer name);




}
