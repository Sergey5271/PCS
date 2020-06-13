package com.pcs.repository;

import com.pcs.model.PostureLevel;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PostureRepository extends Repository<PostureLevel, Integer> {

    void save(PostureLevel postureLevel);

    List<PostureLevel> findAll();

}
