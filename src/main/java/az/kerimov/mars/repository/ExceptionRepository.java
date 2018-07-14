package az.kerimov.mars.repository;

import az.kerimov.mars.entity.MException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionRepository extends JpaRepository<MException, Integer> {
    MException findById(Integer id);
}
