package com.goit.startup.repository;

import com.goit.startup.entity.Startup;
import com.goit.startup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


/**
 * TODO добавить метод поиска, который будет искать введенное слово в названии и в описании одновременно
 *
 * Interface provides a set of methods for the operation with entity {@link Startup}
 *
 * @author Slava Makhinich
 * @version 1.0
 */
public interface StartupRepository extends JpaRepository<Startup, Long> {

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
    void deleteByName(Startup name);

    Collection<Startup> findAllByUser(User user);
}
