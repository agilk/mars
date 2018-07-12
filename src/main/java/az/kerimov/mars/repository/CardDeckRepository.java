package az.kerimov.mars.repository;

import az.kerimov.mars.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardDeckRepository extends JpaRepository<CardDeck, Integer> {
    CardDeck findById(Integer id);
    List<CardDeck> findAll();
}
