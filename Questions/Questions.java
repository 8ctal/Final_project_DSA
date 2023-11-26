package Questions;

import java.io.*;
import java.util.*;

public class Questions {
    private List<String> questions = new ArrayList<String>();
    private boolean answer;

    public Questions() {
        try {
            questions = readQuestions();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void print() {
        for (String question : questions) {
            System.out.println(question);
        }
    }


    public List<String> readQuestions() throws IOException {
        List<String> qList = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("Questions/questions.txt")))) {
            String line = reader.readLine();
            while (line != null) {
                qList.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return qList;
    }

}