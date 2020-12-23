package com.atec.samuraiinventory.jira.dto;

import java.util.Objects;

public class User {
    private String name;
    private String pin;

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(pin, user.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pin);
    }
}
