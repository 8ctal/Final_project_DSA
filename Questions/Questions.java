package Questions;

import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class Questions {
    private String question;
    private boolean answer;

    public Questions(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public static void print(List<Questions> questions) {
        for (Questions question : questions) {
            System.out.println(question);
        }
    }

    public static List<Questions> readQuestions() throws IOException {
        List<Questions> qList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("Questions/questions.txt")))) {
            String line = reader.readLine();
            while (line != null) {
                qList.add(new Questions(line, false));
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return qList;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question='" + question + '\'' +
                ", answer=" + answer +
                '}';
    }
}