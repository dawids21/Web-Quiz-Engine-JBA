package engine.quiz;

import engine.quiz.models.QuizEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends PagingAndSortingRepository<QuizEntity, Long> {

}
