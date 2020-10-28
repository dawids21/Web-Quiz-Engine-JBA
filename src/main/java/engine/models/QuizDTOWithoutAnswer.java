package engine.models;

import java.util.List;
import java.util.Objects;

public class QuizDTOWithoutAnswer {

    private long id;
    private String title;
    private String text;
    private List<String> options;

    public QuizDTOWithoutAnswer() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuizDTOWithoutAnswer that = (QuizDTOWithoutAnswer) o;
        return getId() == that.getId() && Objects.equals(getTitle(), that.getTitle()) &&
               Objects.equals(getText(), that.getText()) &&
               Objects.equals(getOptions(), that.getOptions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getText(), getOptions());
    }

    @Override
    public String toString() {
        return "QuizDTOWithoutAnswer{" + "id=" + id + ", title='" + title + '\'' +
               ", text='" + text + '\'' + ", options=" + options + '}';
    }
}
