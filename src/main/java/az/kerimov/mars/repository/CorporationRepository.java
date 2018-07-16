package az.kerimov.mars.repository;

import az.kerimov.mars.entity.CardDeck;
import az.kerimov.mars.entity.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorporationRepository extends JpaRepository<Corporation, Integer> {
    List<Corporation> findAll();
    Corporation findById(Integer id);
    List<Corporation> findAllByCardDeck(CardDeck cardDeck);
}
