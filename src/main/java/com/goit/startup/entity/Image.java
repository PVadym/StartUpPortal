package com.goit.startup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Arrays;

/**
 * The class is a representation of  image entity
 * extends {@link Model}
 *
 * @author Perevoznyk Pavlo
 *         created on 17 may 2017
 * @version 1.0
 */
@Entity
@Table(name = "images")
public class Image extends Model {

    /**
     * Byte array of data for the image
     */
    @Column(name = "data")
    @Lob
    private byte[] data;

    /**
     * Default constructor
     */
    public Image() {
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

        Image image = (Image) o;

        return Arrays.equals(data, image.data);
    }

    /**
     * Method for getting a hashcode value of the instance
     *
     * @return an integer, hash code value of the instance
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    /**
     * Method return a string representation of the {@link Image}.
     *
     * @return a string representation of the instance.
     */
    @Override
    public String toString() {
        return "Image ID: " + this.getId();
    }

    /**
     * A getter for the field data
     *
     * @return a byte array of data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * A setter for the field name.
     *
     * @param data a byte array of data
     */
    public void setData(byte[] data) {
        this.data = data;
    }
}
