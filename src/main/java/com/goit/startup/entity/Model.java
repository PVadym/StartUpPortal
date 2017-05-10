package com.goit.startup.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The abstract superclass class implements a set of standard methods for working with entities
 *
 * @author Вадим
 * @version 1.0
 */
@MappedSuperclass
public abstract class Model implements Serializable {

    /**
     * The version number for a class
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for each model.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this object is the same as the obj
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(Object object) {
        return (object != null) && (super.equals(object) || (getClass() == object.getClass()));
    }

    /**
     * Returns a string representation of the model.
     *
     * @return A string representation of the model.
     */
    @Override
    public String toString() {
        return "Model{id=" + this.id + "}";
    }

    /**
     * Getter for id of model
     *
     * @return id of model
     */
    public long getId() {
        return this.id;
    }

    /**
     * Setter for id of model
     *
     * @param id The unique identifier for each model.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Abstract method returns a hash code value for the model.
     *
     * @return a hash code value for this model.
     */
    @Override
    public abstract int hashCode();
}
