package group.oauth.webapi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class UserForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "아이디")
      private String username;

      @ApiModelProperty(value = "역할")
      private String role;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "아이디")
      private String loginId;

      @ApiModelProperty(value = "성명")
      private String userName;

      @ApiModelProperty(value = "비밀번호")
      private String password;

      @ApiModelProperty(value = "역할")
      private String role;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Login {

      @ApiModelProperty(value = "아이디")
      private String loginId;

      @ApiModelProperty(value = "비밀번호")
      private String password;

    }
  }

  public static class Output{

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "사용자 식별번호", example = "0")
      // swagger에서 int type은 example="0" 설정 안해주면 경고
      private UUID userId;

      @ApiModelProperty(value = "아이디")
      private String loginId;

      @ApiModelProperty(value = "성명")
      private String userName;

      @ApiModelProperty(value = "비밀번호")
      private String password;

      @ApiModelProperty(value = "역할")
      private String role;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "사용자 식별번호", example = "0")
      private UUID userId;

      @ApiModelProperty(value = "아이디")
      private String loginId;

      @ApiModelProperty(value = "성명")
      private String userName;

      @ApiModelProperty(value = "비밀번호")
      private String password;

      @ApiModelProperty(value = "역할")
      private String role;

    }

  }

}
