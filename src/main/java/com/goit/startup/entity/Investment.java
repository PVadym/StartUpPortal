package com.goit.startup.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The entity class, describe investment entity, implements a set of standard methods for working with this entity
 * and extends {@link Model}
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */

@Entity
@Table(name = "investments")
public class Investment extends Model {

    /**
     * Amount of investment
     */
    @Column(name = "amount", nullable = false)
    private int amount;

    /**
     * Default constructor
     */
    public Investment() {
        this.amount = 0;
    }

    /**
     * Method return a string representation of the investment.
     *
     * @return a string representation of the investment.
     */
    @Override
    public String toString() {
        return "Investment{" + super.toString() +
                "amount=" + amount +
                "} " ;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the obj argument
     */

    @Override
    public boolean equals(Object object) {

        if (!super.equals(object)) return false;

        Investment that = (Investment) object;

        return amount == that.amount;

    }

    /**
     * Method for getting a hashcode value of the instance
     *
     * @return an integer, hash code value of the instance
     */
    @Override
    public int hashCode() {
        int res = 31*this.amount;
        return res;
    }

    /**
     * A getter for the field amount.
     *
     * @return a amount of investment.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * A setter for the field amount.
     *
     * @param amount a amount of investment.
     */
    public void setAmount(int amount) {
        this.amount = amount > 0 ? amount:0;
    }



}
