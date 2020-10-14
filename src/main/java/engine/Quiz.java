package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class Quiz {

    private int id;
    private String title;
    private String text;
    private String[] options;
    private Set<Integer> answer;

    public Quiz() {
    }

    public Quiz(String title, String text, String[] options, Set<Integer> answer) {
        this.id = 0;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    @JsonIgnore
    public Set<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }
}
