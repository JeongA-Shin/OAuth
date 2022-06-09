package group.oauth.configure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

//클라이언트와 토큰 관리는 Spring Security OAuth 모듈이 담당하지만 사용자 관리는 Spring Security의 몫이다
//따라서 사용자를 관리하는 spring security는 securityConfig에 따로 설정을 해놓았다

//Oauth 개념 중 AuthorizationServer에 대한 설정
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer // oauth 서버에 필요한 기본 설정을 셋팅
public class WebSecurityAuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

  private final TokenStore tokenStore;

  private final AuthenticationManager authenticationManager;

  private final PasswordEncoder passwordEncoder;

  private final UserDetailsService userDetailsService;

  /**
   * API의 요청 클라이언트 정보를 설정
   *
   * <pre>
   * OAuth Client ID, Client Secret 정의
   * 앱에 접속할 클라이언트는 한정되어 있어 인메모리 방식 사용
   * 동적으로 클라이언트 생성이 필요하면 DB 관리방식으로 변경
   * </pre>
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception { // API의 요청 클라이언트 정보를 설정
    clients.inMemory() //inMemory()는 클라이언트(=나. 개발자. 애플리케이션) 정보를 메모리에 저장 개발 환경에 적합 //jdbc()는 데이터베이스에 저장한다. 운영 환경에 적합하다.
        .withClient("jeong")//withClient()로 client_id 값을 설정한다. secret()은 client_secret 값을 설정한다.
        .secret(passwordEncoder.encode("jeong123"))
        .authorizedGrantTypes("password", "refresh_token") //authorizedGrantTypes()는 grant_type(access_token을 획득하기 위한 4가지 인증 방법) 값을 설정한다. 복수개를 저장할 수 있다.
        .scopes("read", "write") //scopes()는 scope 값을 설정한다.
        .accessTokenValiditySeconds(60 * 60) //이 클라이언트로 발급된 토큰의 시간 (단위:초)
        .refreshTokenValiditySeconds(60 * 60 * 60) //// refresh token 만료시간
        .and()
        .withClient("hyun")//클라이언트를 한 명 더 등록
        .secret(passwordEncoder.encode("hyun123"))
        .authorizedGrantTypes("password", "refresh_token")
        .scopes("read", "write")
        .accessTokenValiditySeconds(60 * 60 * 60)
        .refreshTokenValiditySeconds(60 * 60 * 60);
  }


  /**
   * 인증서버 엔드포인트 속성? 설정
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {//인증, 토큰엔드포인트, 토큰 서비스를 정의 할 수 있다.
    //endpoint는  OAuth 권한 부여 요청을 하는 데 사용하는 URL
    endpoints.tokenStore(tokenStore) // 즉, 인증 서버에 token store를 등록
        .authenticationManager(authenticationManager) // 인증 서버에 인증 매니저를 등록
        .userDetailsService(userDetailsService); // 인증 서버에 userDetailsService를 등록
  }

}
