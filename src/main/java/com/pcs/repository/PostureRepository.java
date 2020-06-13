package com.pcs.repository;

import com.pcs.model.PostureLevel;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface PostureRepository extends Repository<PostureLevel, Integer> {

    void save(PostureLevel postureLevel);

    @Transactional(readOnly = true)
    PostureLevel findById(Integer id);

}
