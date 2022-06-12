package group.oauth.webapi.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class ResponseForm {

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Simple {

      @ApiModelProperty(value = "응답메시지")
      private String msg;

    }
  }
}
