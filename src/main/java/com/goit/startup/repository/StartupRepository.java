package com.goit.startup.repository;

import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


/**
 * Interface provides a set of methods for the operation with entity {@link Startup}
 *
 * @author Slava Makhinich
 * @version 1.0
 */
public interface StartupRepository extends DataRepository<Startup> {

    /**
     * The method find product by name from database
     *
     * @param name a product`s name
     * @return product with entered name
     */
    Startup findByName(String name);

    /**
     * The method removes product from database
     *
     * @param name a name of product, which must be removed
     */
    void deleteByName(String name);

    /**
     * The method finds all startups in database that contain in their name or description entered keyWord
     *
     * @param keyWord a word that we are traing to find in startups name and description
     * @return a collection of startups that contain entered word in thair name or description
     */
    @Query("select s from Startup s where s.name like %:keyWord% or s.description like %:keyWord%")
    Collection<Startup> findAllByKeyWord(@Param("keyWord") String keyWord);}
