package az.kerimov.mars.repository;

import az.kerimov.mars.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Game findByIdAndHash(Integer id, String hash);

}
