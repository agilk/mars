package az.kerimov.mars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{
    List<Card> findAll();
    List<Card> findAllByCardDeck(CardDeck deck);
    Card findById(Integer id);
}
