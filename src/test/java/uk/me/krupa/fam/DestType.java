package uk.me.krupa.fam;

import sun.security.krb5.internal.crypto.Des;

public class DestType {

    private String name;
    private int age;
    private Double height;

    public DestType() {
    }

    public String getName() {
        return name;
    }

    public DestType withName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public DestType withAge(int age) {
        this.age = age;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public DestType withHeight(Double height) {
        this.height = height;
        return this;
    }
}
