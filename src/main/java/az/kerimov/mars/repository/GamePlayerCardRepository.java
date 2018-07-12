package az.kerimov.mars.repository;

import az.kerimov.mars.entity.Card;
import az.kerimov.mars.entity.Game;
import az.kerimov.mars.entity.GamePlayerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlayerCardRepository extends JpaRepository<GamePlayerCard, Integer> {
    GamePlayerCard findById(Integer id);

    GamePlayerCard findByGameAndCard(Game game, Card card);

}
