package Population;

import Questions.*;
import lombok.*;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Person {
    private boolean infected;
    private boolean vaccinated;
    private int age;
    private int daysInfected;
    private List<Boolean> answers;

    public Boolean getAnswer(int index) {
        return answers.get(index);
    }

    public List<Questions> addAnswer() throws IOException {
        List<Questions> questions = Questions.readQuestions();
        questions.forEach(question -> {
            question.setAnswer(answers.get(questions.indexOf(question)));
        });
        return questions;
    }

}
