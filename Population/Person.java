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



    public static Person createUninfectedPerson(String name, int age) {
        return new Person(name, age, false, InfectionType.NONE);
    }

    public static Person createInfectedPerson(String name, int age, InfectionType infectionType) {
        return new Person(name, age, true, infectionType);
    }

    public enum InfectionType {
        VIRUS,
        BACTERIA,
        FUNGUS,
        NONE // Para representar a una persona no infectada
    }
}
