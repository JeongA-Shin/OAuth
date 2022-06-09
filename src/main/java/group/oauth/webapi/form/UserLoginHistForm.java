package group.oauth.webapi.form;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

public class UserLoginHistForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "사용자 식별번호", example = "0")
      private UUID userId;

    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "로그인 일시")
      private DateTime loginDt;

      @ApiModelProperty(value = "사용자 성명")
      private String userName;


    }
  }



}
