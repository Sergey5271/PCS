package com.pcs.repository;

import com.pcs.model.PostureLevel;
import com.pcs.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface PostureRepository extends Repository<PostureLevel, Integer> {

    void save(PostureLevel postureLevel);

    List<PostureLevel> findAll();



    List<PostureLevel> findByUserId(Integer id);

}
