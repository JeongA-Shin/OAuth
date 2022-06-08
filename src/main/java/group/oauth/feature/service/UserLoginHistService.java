package group.oauth.feature.service;

import com.querydsl.core.BooleanBuilder;
import group.oauth.feature.model.QUserLoginHist;
import group.oauth.feature.model.UserLoginHist;
import group.oauth.feature.repository.UserLoginHistRepository;
import group.oauth.feature.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserLoginHistService {

  private final UserLoginHistRepository repository;
  private final UserRepository userRepository;

  /**
   * 목록 조회 - 로그인 이력 목록 조회
   *
   * @param userId 검색 조건
   * @return 검색된 목록
   */
  public List<UserLoginHist> getList(UUID userId){ //어차피 검색 조건은 하나밖에 없으니까 Predicate 굳이 안 만들고 걍 파라미터 하나로 받음
    if(userId== null){
      return repository.findAll(); //만약 검색조건이 없으면 그냥 전체 목록 리턴
    }
    return (List<UserLoginHist>)repository.findAll(new BooleanBuilder(QUserLoginHist.userLoginHist.userId.eq(userId)));
  }

  /**
   * 로그인 이력 등록 - 파라미터로 전달된 로그인 아이디에 대해 로그인 이력 생성
   *
   * @param loginId
   * @return
   */
  public UserLoginHist add(String loginId){

    //해당 로그인에 대한 로그인 이력 새로 생성 후 추가
    UserLoginHist userLoginHist=UserLoginHist.builder()
        .userId(userRepository.findByLoginId(loginId).get().getUserId())
        .build();

    return repository.save(userLoginHist);

  }

}
