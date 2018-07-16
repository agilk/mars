package az.kerimov.mars.repository;

import az.kerimov.mars.entity.GameCard;
import az.kerimov.mars.entity.Game;
import az.kerimov.mars.entity.GamePlayerCard;
import az.kerimov.mars.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamePlayerCardRepository extends JpaRepository<GamePlayerCard, Integer> {
    GamePlayerCard findById(Integer id);

    GamePlayerCard findByGameAndCard(Game game, GameCard card);

    List<GamePlayerCard> findAllByGameAndPlayerAndGenerationId(Game game, Player player, Integer generationId);

}
