package Population;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor

public class Person {
    private String name;
    private int age;
    private boolean infected;
    private InfectionType infectionType;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.infected = false;
    }

    public Person(String name, int age, InfectionType infectionType) {
        this.name = name;
        this.age = age;
        this.infected = true;
        this.infectionType = infectionType;
    }

    public enum InfectionType {
        VIRUS,
        BACTERIA,
        FUNGUS,
        NONE // Para representar a una persona no infectada
    }
}
