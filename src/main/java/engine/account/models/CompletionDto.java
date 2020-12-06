package engine.account.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class CompletionDto {

    private long id;
    private LocalDateTime completedAt;

    public CompletionDto(long id, LocalDateTime completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompletionDto that = (CompletionDto) o;
        return getId() == that.getId() &&
               Objects.equals(getCompletedAt(), that.getCompletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCompletedAt());
    }

    @Override
    public String toString() {
        return "CompletionDto{" + "id=" + id + ", completedAt=" + completedAt + '}';
    }
}
