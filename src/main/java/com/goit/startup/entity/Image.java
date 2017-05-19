package com.goit.startup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Arrays;

/**
 * The class is a representation of  image entity
 *
 * @author Perevoznyk Pavlo
 *         created on 17 may 2017
 * @version 1.0
 */
@Entity
@Table(name = "images")
public class Image extends Model {

    @Column(name = "data")
    @Lob
    private byte[] data;


    public Image() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Image image = (Image) o;

        return Arrays.equals(data, image.data);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        return "Image ID: " + this.getId();
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
