package engine.quiz;

public class AnswerFeedback {

    private static final String SUCCESS_FEEDBACK = "Congratulations, you're right!";
    private static final String FAILURE_FEEDBACK = "Wrong answer! Please, try again.";

    private boolean success;
    private String feedback;

    public AnswerFeedback(boolean success) {
        this.success = success;
        if (success) {
            feedback = SUCCESS_FEEDBACK;
        } else {
            feedback = FAILURE_FEEDBACK;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
