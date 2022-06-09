package group.oauth.configure.web;

import group.oauth.configure.OAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final OAuthInterceptor oAuthInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(oAuthInterceptor)
        .addPathPatterns("/oauth/token"); // 다른 건 다 되는데 /oauth/** 는 안된다.
  }

}
