package engine;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class AnswerJson {

    @NotNull(message = "Answer is mandatory")
    private Set<Integer> answer;

    public AnswerJson() {
    }

    public AnswerJson(Set<Integer> answer) {
        this.answer = answer;
    }

    public Set<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }
}
