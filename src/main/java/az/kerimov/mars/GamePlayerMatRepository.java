package az.kerimov.mars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlayerMatRepository extends JpaRepository<GamePlayerMat, Integer> {
    GamePlayerMat findById(Integer id);
    GamePlayerMat findByGame(Game game);
}
