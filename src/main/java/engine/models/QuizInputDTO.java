package engine.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class QuizInputDTO {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Text is mandatory")
    private String text;

    @NotNull(message = "Options are mandatory")
    private List<String> options;

    private Set<Integer> answer = new HashSet<>();

    public QuizInputDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Set<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuizInputDTO that = (QuizInputDTO) o;
        return Objects.equals(getTitle(), that.getTitle()) &&
               Objects.equals(getText(), that.getText()) &&
               Objects.equals(getOptions(), that.getOptions()) &&
               Objects.equals(getAnswer(), that.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getText(), getOptions(), getAnswer());
    }

    @Override
    public String toString() {
        return "QuizInputDTO{" + "title='" + title + '\'' + ", text='" + text + '\'' +
               ", options=" + options + ", answer=" + answer + '}';
    }
}
