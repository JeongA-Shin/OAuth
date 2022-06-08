package group.oauth.feature.repository;

import group.oauth.feature.model.UserLoginHist;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginHistRepository extends JpaRepository<UserLoginHist,UUID>,
    QuerydslPredicateExecutor<UserLoginHist> {

}
