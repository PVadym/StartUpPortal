package com.goit.startup.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity class, describe startup entity, implements a set of standard methods for working with this entity.
 * Extends {@link Model}
 *
 * @author Slava Malhinich
 * @version 1.0
 */
@Entity
@Table(name = "startups")
public class Startup extends Model {

    /**
     * Startup's name
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Startup's description
     */
    @Column(name = "description")
    private String description;

    /**
     * Minimum amount that user can invest in this startup
     */
    @Column(name = "min_investment")
    private int minInvestment;

    /**
     * Amount that need to collect to launch this startup
     */
    @Column(name = "need_investment")
    private int needInvestment;

    /**
     * A list of investments that users already  made in this startup
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "startup_id")
    private List<Investment> investments;

    /**
     * Default constructor
     */
    public Startup() {
        name = "";
        description = "";
        minInvestment = 0;
        needInvestment = 0;
        investments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Startup{" + super.toString() +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", minInvestment=" + minInvestment +
                ", needInvestment=" + needInvestment +
                ", investments=" + investments +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o))
            return false;

        Startup startup = (Startup) o;

        if (minInvestment != startup.minInvestment) return false;
        if (needInvestment != startup.needInvestment) return false;
        if (!name.equals(startup.name)) return false;
        if (description != null ? !description.equals(startup.description) : startup.description != null) return false;
        return investments != null ? investments.equals(startup.investments) : startup.investments == null;
    }

    /**
     * Method for getting a hashcode value of the instance
     *
     * @return an integer, hash code value of the instance
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + minInvestment;
        result = 31 * result + needInvestment;
        result = 31 * result + (investments != null ? investments.hashCode() : 0);
        return result;
    }

    /**
     * A getter for the field name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * A setter for the field name.
     *
     * @param name a name if startup.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A getter for the field description.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * A setter for field description.
     *
     * @param description a stastup's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * A getter for the field minInvestment.
     *
     * @return value of the field minInvestment.
     */
    public int getMinInvestment() {
        return minInvestment;
    }

    /**
     * A setter for the field minInvestment
     *
     * @param minInvestment a new value for field minInvestment.
     */
    public void setMinInvestment(int minInvestment) {
        this.minInvestment = minInvestment;
    }

    /**
     * A getter for the field needInvestment.
     *
     * @return value of the field needInvestment.
     */
    public int getNeedInvestment() {
        return needInvestment;
    }

    /**
     * A setter for the field needInvestment.
     *
     * @param needInvestment a new value for field needInvestment.
     */
    public void setNeedInvestment(int needInvestment) {
        this.needInvestment = needInvestment;
    }

    /**
     * A getter for the field investments.
     *
     * @return A list of investments that users already  made in this startup.
     */
    public List<Investment> getInvestments() {
        return investments;
    }

    /**
     * A setter for the field investments.
     *
     * @param investments a list of investments.
     */
    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }
}
