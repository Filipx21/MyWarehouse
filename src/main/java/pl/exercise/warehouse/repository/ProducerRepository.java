package pl.exercise.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.exercise.warehouse.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
