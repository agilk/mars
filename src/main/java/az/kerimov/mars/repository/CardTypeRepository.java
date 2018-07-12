package az.kerimov.mars.repository;

import az.kerimov.mars.entity.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Integer> {
    CardType findById(Integer id);
}
