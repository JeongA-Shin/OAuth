package group.oauth.feature.security;

import group.oauth.feature.model.User;
import group.oauth.feature.repository.UserRepository;
import java.util.Arrays;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//개발자가 해야 하는 것은 UserDetailsService 인터페이스를 구현하고 인증 매니저에 연결시켜주면 됩니다.
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  //비밀번호를 체크하는 로직을 구현하려면 UserDetailsService 인터페이스를 구현한 클래스가 필요합니다.
  //DB에 있는 이용자의 정보를 가져오려면, UserDetailsService 인터페이스를 사용한다.
  //출처: https://to-dy.tistory.com/72 [todyDev:티스토리]

  private final UserRepository repository; //사용자 식별번호 및 로그인을 위한 정볼르 가지고 있음

  //UserDetailsService 인터페이스는 화면에서 입력한 이용자의 이름 혹은 로그인 아이디을 가지고 loadUserByUsername() 메소드를 호출하여
  // DB에 있는(== 리포지토리에 있는) 이용자의 정보를 UserDetails 형으로 가져온다.
  @Override
  public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
    User user = repository.findByLoginId(loginId).get(); //파라미터로 넘겨진 로그인 아이디에 해당되는 유저

    //스프링 시큐리티에서는 로그인 하면 org.springframework.security.core.userdetails.User 클래스로 리턴하게 됩니다.
    // 그리고 UserDetailService를 이용해 사용자 정보를 읽어냅니다.

    return new org.springframework.security.core.userdetails.User(user.getLoginId(),
        user.getPassword(), getAuthorities(user.getRole()));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(String role) { //계정이 가지고 있는 권한 목록을 리턴한다
    return Arrays.asList(new SimpleGrantedAuthority(role));
  }
}
