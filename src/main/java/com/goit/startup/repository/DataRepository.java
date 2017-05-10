package com.goit.startup.repository;
import com.goit.startup.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interface provides a set of methods for the operation of entities with a database.
 *
 * @param <T> Entity type, extends {@link Model}.
 * @author Вадим
 * @version 1.0
 */
@NoRepositoryBean
public interface DataRepository<T extends Model> extends JpaRepository<T, Long> {
}