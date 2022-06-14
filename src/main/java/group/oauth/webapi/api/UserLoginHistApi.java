package group.oauth.webapi.api;


import group.oauth.feature.service.UserLoginHistService;
import group.oauth.webapi.form.UserLoginHistForm;
import group.oauth.webapi.form.UserLoginHistForm.Output.GetAll;
import group.oauth.webapi.mapper.UserLoginHistFormMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value = "UserLoginHist", tags = {"UserLoginHist"})
@RequestMapping(value = "/api/user-login-hist", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserLoginHistApi {

  private final UserLoginHistFormMapper formMapper;

  private final UserLoginHistService service;

  @SneakyThrows
  @ApiOperation("로그인 이력 조회")
  @PostMapping("/get-list")
  public List<GetAll> getList(UserLoginHistForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(in.getUserId()));
  }

}
