package az.kerimov.mars.repository;

import az.kerimov.mars.entity.Card;
import az.kerimov.mars.entity.Game;
import az.kerimov.mars.entity.GameCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameCardRepository extends JpaRepository<GameCard, Integer> {
    GameCard findById(Integer id);
    GameCard findByGameAndCard(Game game, Card card);

    List<GameCard> findAllByGameAndGenerationIdAndPlayerId(Game game, Integer generationId, Integer playerId);
    List<GameCard> findAllByGameAndGenerationIdAndPlayerIdAndPicked(Game game, Integer generationId, Integer playerId, Integer picked);
}
