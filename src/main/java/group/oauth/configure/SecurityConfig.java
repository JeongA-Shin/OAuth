package group.oauth.configure;

import group.oauth.feature.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//자바 기반의 설정에서는 WebSecurityConfigurerAdapter를 상속받은 클래스에
// @EnableWebSecurity 어노테이션을 명시하는 것만으로도 springSecurityFilterChain가 자동으로 포함되어집니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsServiceImpl userDetailsService;


  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception { //얘가 시작점임.
    return super.authenticationManager(); //인자로 받은 Authentication이 유효한 인증인지 확인하고,  "Authentication" 객체를 리턴
  }

  @Bean
  public PasswordEncoder encoder() {
    //PasswordEncoder는 스프링 시큐리티의 인터페이스 객체
    // PasswordEncoder의 구현체를 대입해주고 이를 """스프링 빈으로""" 등록하는 과정이 필요하다.
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public TokenStore tokenStore() {
    return new InMemoryTokenStore();
  } //token store로 JWTTokenStore를 사용하겠다

  //인증 객체 만들기
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //AuthenticationManagerBuilder를 통해 인증 객체를 만들 수 있도록 제공하고 있습니다
    //애초에 .authenticationManager()는 인자로 받은 Authentication이 유효한 인증인지 확인하고, "Authentication" 객체를 리턴
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(encoder());
  }

  /*
   * 스프링 시큐리티 규칙 설정
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().and()//spring-security에서 cors를 적용한다는 설정 //인증 성공 여부와 무관하게 Origin 헤더가 있는 모든 요청에 대해 CORS 헤더를 포함한 응답을 해준다.
        .csrf().disable()//우리가 만들고자 하는 rest api에서는 csrf 공격으로부터 안전하고 매번 api 요청으로부터 csrf 토큰을 받지 않아도 되어 이 기능을 disable() 하는 것이 더 좋은 판단으로 보인다.
        .anonymous().disable()//인증되지 않은 사용자가 접근할 수 없도록(disable) 함
        .authorizeRequests() //authorizeRequests()는 시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미합니다.
        .antMatchers("/swagger-ui.html").permitAll(); //ntMatchers()는 특정한 경로를 지정합니다.
    // ///"/swagger-ui.html"에는 전체 접근 허용
    //  .antMatchers("/myPage").hasRole("ADMIN")//admin이라는 롤을 가진 사용자만 접근 허용
  }


  /*
   * 스프링 시큐리티 룰을 무시하게 하는 Url 규칙(여기 등록하면 규칙 적용하지 않음)
   */
  @Override
  public void configure(WebSecurity web) throws Exception {

    web.ignoring().antMatchers(
        "/v2/api-docs", "/configuration/ui",
        "/swagger-resources", "/configuration/security", "/swagger-ui.html",
        "/webjars/**", "/swagger/**", "/api/login/**", "/api/users/add");

  }


}
