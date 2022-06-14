package group.oauth.webapi.api;

import group.oauth.feature.service.UserLoginHistService;
import group.oauth.webapi.form.UserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@Api(value = "Login", tags = {"Login"})
@RequestMapping(value = "/api/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginApi {

  private final UserLoginHistService userLoginHistService;

  @Value("${server.address}")
  private String address;

  @Value("${server.port}")
  private String port;

  @SneakyThrows
  @ApiOperation("로그인 토큰 발급")
  @PostMapping("/get-token")
  public ResponseEntity login(@RequestBody UserForm.Input.Login in, @RequestHeader(value = "Authorization") String authorization){
    //리턴 타입을 단순히 dto (entity)가 아니라 ResponseEntity를 해주면 상태 코드 등 추가적인 정보를 함께 리턴해줄 수 있음

    //System.out.println(authorization); // Basic aHl1bjI6aHl1bjIxMjM=

    String url = String.format("http://%s:%s/oauth/token", address, port);
    RestTemplate restTemplate = new RestTemplate(); //간편하게 Rest 방식 api를 호출할 수 있는 spring 내장 클래스
    // header 설정
    HttpHeaders httpHeaders = new HttpHeaders();
    //httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8"))); // oauth는 body를 x-www-form-urlencoded 이용해야함
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    httpHeaders.add("Authorization", authorization);

    // body 설정
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("username", in.getLoginId());
    map.add("password", in.getPassword());
    map.add("grant_type", "password");

    //설정한 header와 body를 가지고 http entity 만듦 - 요청용
    HttpEntity httpEntity = new HttpEntity(map, httpHeaders);

    try{
      ResponseEntity<String> oauthResponse = restTemplate.exchange(
          url, HttpMethod.POST, httpEntity, String.class
      ); // restTemplate.exchange() 메소드로 api를 호출하게 됩니다.String.class이므로 응답을 string으로 받음

      // String 으로 받은 응답을 json으로 변경
      JSONParser jsonParser = new JSONParser();
      Object obj = jsonParser.parse(oauthResponse.getBody());
      JSONObject responseObj = (JSONObject) obj;

      // 로그인 이력에 추가
      userLoginHistService.add(in.getLoginId());

      return ResponseEntity.ok(responseObj);

    }catch (Exception e){
      throw new IllegalStateException("Login token error");
    }

  }

}
