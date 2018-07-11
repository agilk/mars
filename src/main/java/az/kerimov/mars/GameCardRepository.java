package az.kerimov.mars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameCardRepository extends JpaRepository<GameCard, Integer> {
    GameCard findById(Integer id);

    List<GameCard> findAllByGameAndGenerationIdAndPlayerId(Game game, Integer generationId, Integer playerId);
}
