package com.goit.startup.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    private User author;

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
                "} ";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Investment that = (Investment) o;

        return amount == that.amount;

    }


    /**
     * Method for getting a hashcode value of the instance
     *
     * @return an integer, hash code value of the instance
     */

    @Override
    public int hashCode() {
        return amount;
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
        this.amount = amount > 0 ? amount : 0;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
