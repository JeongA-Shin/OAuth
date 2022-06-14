package group.oauth.webapi.api;


import group.oauth.feature.service.UserService;
import group.oauth.webapi.form.UserForm;
import group.oauth.webapi.mapper.UserFormMapper;
import group.oauth.webapi.predicate.UserFormPredicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "User", tags = {"User"})
public class UserApi {

  private final UserFormMapper formMapper;

  private final UserService service;

  @SneakyThrows
  @ApiOperation("사용자 목록 조회")
  @GetMapping("/get-list")
  public List<GetAll> getList(UserForm.Input.GetAll in){
    return formMapper.toGetAllList(service.getList(UserFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("사용자 등록")
  @PostMapping("/add")
  public UserForm.Output.Get add(@RequestBody UserForm.Input.Add in) {
    return formMapper.toGet(service.add(formMapper.toUser(in)));
  }

  @SneakyThrows
  @ApiOperation("사용자 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

}
