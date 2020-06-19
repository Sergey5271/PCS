package com.pcs.repository;

import com.pcs.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends Repository<User, Integer> {

    @Query("SELECT DISTINCT user FROM User user left join fetch user.symptom WHERE user.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
    Collection<User> findByLastNames(@Param("lastName") String lastName);


    @Query("SELECT user FROM User user left join fetch user.symptom WHERE user.id =:id")
    @Transactional(readOnly = true)
    User findById(@Param("id") Integer id);

    void save(User owner);

    Optional<User> findByLastName(String lastName);
}
