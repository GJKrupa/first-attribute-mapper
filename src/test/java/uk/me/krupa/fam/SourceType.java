package uk.me.krupa.fam;

public class SourceType {

    private String name;
    private String age;
    private String height;

    public SourceType(String name, String age, String height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }
}
