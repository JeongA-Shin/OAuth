package group.oauth.configure.web;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

public class WebSecurityResourceConfiguration extends ResourceServerConfigurerAdapter {


  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.anonymous().disable()
        .authorizeRequests()
       .antMatchers("/api/admin/**").hasRole("ADMIN")
//        .antMatchers("/api/login/**").permitAll() // 작동 안함 -> SecurityConfig web.ignoring().antMatchers ~ 안에 추가함
        .antMatchers("/api/**").authenticated()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new OAuth2AccessDeniedHandler());
  }

}
