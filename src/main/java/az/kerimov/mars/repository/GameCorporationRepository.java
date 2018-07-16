package az.kerimov.mars.repository;

import az.kerimov.mars.entity.Corporation;
import az.kerimov.mars.entity.Game;
import az.kerimov.mars.entity.GameCorporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameCorporationRepository extends JpaRepository<GameCorporation, Integer> {
    List<GameCorporation> findAllByGameAndPlayerIdAndPicked(Game game, Integer playerId, Integer picked);
    List<GameCorporation> findAllByGameAndPicked(Game game, Integer picked);
    GameCorporation findById(Integer id);
    GameCorporation findByGameAndCorporation(Game game, Corporation corporation);
}
