package com.goit.startup.entity;


import javax.persistence.*;

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
     * Investor of investment
     */
    @ManyToOne
    @JoinColumn(name = "investor_id")
    private User investor;

    /**
     * Startup in which investment was invested
     */
    @ManyToOne
    @JoinColumn(name = "startup_id")
    private Startup startup;

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

        if (super.getId() != that.getId()) return false;
        if (amount != that.amount) return false;
        if (investor != null ? !investor.equals(that.investor) : that.investor != null) return false;
        return startup != null ? startup.equals(that.startup) : that.startup == null;
    }

    /**
     * Method for getting a hashcode value of the instance
     *
     * @return an integer, hash code value of the instance
     */
    @Override
    public int hashCode() {
        int result = amount;
        result = 31 * result + (int) super.getId();
        result = 31 * result + (investor != null ? investor.hashCode() : 0);
        result = 31 * result + (startup != null ? startup.hashCode() : 0);
        return result;
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

    /**
     * A getter for the field investor.
     *
     * @return Investor of investment
     */
    public User getInvestor() {
        return investor;
    }

    /**
     * A setter for the field investor.
     *
     * @param investor an investor of investment
     */

    public void setInvestor(User investor) {
        this.investor = investor;
    }

    /**
     * A getter for the field startup.
     *
     * @return startup in which investment was invested
     */
    public Startup getStartup() {
        return startup;
    }

    /**
     * A setter for the field startup.
     *
     * @param startup in which investment was invested
     */
    public void setStartup(Startup startup) {
        this.startup = startup;
    }
}
