package Population;

/*import lombok.*; */

public class Infected extends Person {
    public Infected(String name, int age, InfectionType infectionType) {
        super(name, age, true, infectionType);
    }

}

