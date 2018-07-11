package az.kerimov.mars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlayerCardRepository extends JpaRepository<GamePlayerCard, Integer> {
    GamePlayerCard findById(Integer id);

    GamePlayerCard findByGameAndCard(Game game, Card card);

}
