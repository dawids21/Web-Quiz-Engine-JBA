package engine.quiz;

import engine.quiz.models.CompletionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompletionRepository
         extends PagingAndSortingRepository<CompletionEntity, Long> {

    Page<CompletionEntity> findAllByAccountEntityEmail(String email, Pageable pageable);
}
