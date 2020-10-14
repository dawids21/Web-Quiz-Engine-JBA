package engine;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class SolveJson {

    @NotNull(message = "Answer is mandatory")
    private Set<Integer> answer;

    public SolveJson() {
    }

    public SolveJson(Set<Integer> answer) {
        this.answer = answer;
    }

    public Set<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }
}
