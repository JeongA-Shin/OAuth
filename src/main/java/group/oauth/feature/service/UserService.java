package group.oauth.feature.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import group.oauth.feature.model.QUser;
import group.oauth.feature.model.User;
import group.oauth.feature.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final PasswordEncoder encoder;

  /**
   * 목록 조회
   *
   * @param search 검색 조건
   * @return 검색된 목록
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<User> getList(Predicate search){
    return (List<User>) repository.findAll(search);
  }

  /**
   * 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public Optional<User> get(UUID id) {
    return repository.findOne(new BooleanBuilder(QUser.user.userId.eq(id)));
  }

  /**
   * 등록
   *
   * @param user
   * @return
   */
  public User add(User user) {
    if (repository.findByLoginId(user.getLoginId()).isPresent()) {
      throw new IllegalStateException("중복된 아이디가 존재합니다.");
    } else {
      user.setPassword(encoder.encode(user.getPassword()));
      return repository.save(user);
    }

  }

  /**
   * 삭제
   *
   * @param id
   * @return
   */
  public void remove(UUID id) {
    repository.deleteByUserId(id);
  }


}
