package com.pcs.repository;

import com.pcs.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

public interface UserRepository extends Repository<User, Integer> {


    /**
     * Retrieve {@link User}s from the data store by last name, returning all owners
     * whose last name <i>starts</i> with the given name.
     * @param lastName Value to search for
     * @return a Collection of matching {@link User}s (or an empty Collection if none
     * found)
     */
    @Query("SELECT DISTINCT user FROM User user left join fetch user.symptom WHERE user.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
    Collection<User> findByLastName(@Param("lastName") String lastName);

    /**
     * Retrieve an {@link User} from the data store by id.
     * @param id the id to search for
     * @return the {@link User} if found
     */
    @Query("SELECT user FROM User user left join fetch user.symptom WHERE user.id =:id")
    @Transactional(readOnly = true)
    User findById(@Param("id") Integer id);

    /**
     * Save an {@link User} to the data store, either inserting or updating it.
     * @param owner the {@link User} to save
     */
    void save(User owner);
}
