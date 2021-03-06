package engine.quiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class QuizDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Text is mandatory")
    private String text;

    @NotNull(message = "Options are mandatory")
    @Size(min = 2, message = "Must have at least 2 options")
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> answer = new HashSet<>();

    public QuizDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        QuizDto that = (QuizDto) o;
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
        return "QuizDto{" + "title='" + title + '\'' + ", text='" + text + '\'' +
               ", options=" + options + ", answer=" + answer + '}';
    }
}
