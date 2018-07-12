package az.kerimov.mars.repository;

import az.kerimov.mars.entity.Game;
import az.kerimov.mars.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
    Player findById(Integer id);
    List<Player> findAllByGame(Game game);
}
