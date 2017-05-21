package com.goit.startup.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

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

    @Column(name = "image_id")
    private long imageId;

    /**
     * A list of investments that users already  made in this startup
     */
    @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Investment> investments;

    /**
     * An author of this startup
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    /**
     * Default constructor
     */
    public Startup() {
        name = "";
        description = "";
        minInvestment = 0;
        needInvestment = 0;
        this.imageId = 1L;
        investments = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Startup{" + super.toString() +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", minInvestment=" + minInvestment +
                ", needInvestment=" + needInvestment +
                ", investments=" + investments +
                ", authorId=" + author.getId() +
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Startup startup = (Startup) o;

        if (minInvestment != startup.minInvestment) return false;
        if (needInvestment != startup.needInvestment) return false;
        if (imageId != startup.imageId) return false;
        if (name != null ? !name.equals(startup.name) : startup.name != null) return false;
        if (description != null ? !description.equals(startup.description) : startup.description != null) return false;
        return author != null ? author.equals(startup.author) : startup.author == null;

    }


    /**
     * Method for getting a hashcode value of the instance
     *
     * @return an integer, hash code value of the instance
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + minInvestment;
        result = 31 * result + needInvestment;
        result = 31 * result + (int) (imageId ^ (imageId >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }


    /**
     * The method returns the current amount of investment in this startup
     *
     * @return the current amount of investment in this startup
     */
    public int getCurrentInvestments() {
        int sum = 0;
        for (Investment investment : investments) {
            sum += investment.getAmount();
        }
        return sum;
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
        this.name = isNotBlank(name) ? name:"";
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
        this.description = isNotBlank(description) ? description:"";
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
        this.minInvestment = minInvestment > 0 ? minInvestment:0;
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
        this.needInvestment = needInvestment > 0 ? needInvestment:0;
    }

    /**
     * A getter for the field investments.
     *
     * @return A list of investments that users already  made in this startup.
     */
    public Set<Investment> getInvestments() {
        return investments;
    }

    /**
     * A setter for the field investments.
     *
     * @param investments a list of investments.
     */
    public void setInvestments(Set<Investment> investments) {
        this.investments = investments!=null ? investments : new HashSet<>();
    }

    /**
     * A getter for the field author
     *
     * @return this startup's author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * A setter for the field author
     *
     * @param user this startup's author
     */
    public void setAuthor(User user) {
        this.author = user;
    }


    public void makeInvestment(Investment investment) {
        this.investments.add(investment);
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}
