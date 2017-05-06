package com.goit.startup.entity;

import java.io.Serializable;

/**
 * TODO
 */
public class Investment implements Serializable {
    private long id;
    private int amount;
    private User user;
    private Startup startup;

    private Investment() {
    }

    public static Builder newBuilder() {
        return new Investment().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setId(long id) {
            Investment.this.id = id;

            return this;
        }

        public Builder setAmount(int amount) {
            Investment.this.amount = amount;

            return this;
        }

        public Builder setUser(User user) {
            Investment.this.user = user;

            return this;
        }

        public Builder setStartup(Startup startup) {
            Investment.this.startup = startup;

            return this;
        }

        public Investment build() {
            return Investment.this;
        }


    }

    @Override
    public String toString() {
        return "Investment{" +
                "id=" + id +
                ", amount=" + amount +
                ", user=" + user +
                ", startup=" + startup +
                '}';
    }

}
