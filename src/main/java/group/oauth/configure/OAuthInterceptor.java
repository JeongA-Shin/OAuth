package group.oauth.configure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class OAuthInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
    System.out.println("preHandle");
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
      throws Exception {
    System.out.println("postHandle");
  }


  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
      throws Exception {
    System.out.println("afterCompletion");
  }

}
