package group.oauth.feature.repository;

import group.oauth.feature.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UUID, User>, QuerydslPredicateExecutor<User> {

  Optional<User> findByLoginId(String loginId);

  void deleteByUserId(UUID id);

}
