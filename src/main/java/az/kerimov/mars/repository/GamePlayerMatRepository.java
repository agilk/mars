package az.kerimov.mars.repository;

import az.kerimov.mars.entity.Game;
import az.kerimov.mars.entity.GamePlayerMat;
import az.kerimov.mars.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlayerMatRepository extends JpaRepository<GamePlayerMat, Integer> {
    GamePlayerMat findById(Integer id);
    GamePlayerMat findByGameAndPlayer(Game game, Player player);
}
