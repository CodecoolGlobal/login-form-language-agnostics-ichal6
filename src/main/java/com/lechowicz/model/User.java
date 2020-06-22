package com.lechowicz.model;

import java.util.Objects;

public class User {
    public String name;
    public Integer hashPass;

    public User(String name, int hashPass){
        this.name = name;
        this.hashPass = hashPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                hashPass.equals(user.hashPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hashPass);
    }
}
