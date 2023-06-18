package com.milan.doctorsNepal.utils;

public enum RoleType {
    ADMIN ("Admin"),
    PATIENT ("Patient"),
    DOCTOR ("Doctor");
    private final String value;

    RoleType (String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
