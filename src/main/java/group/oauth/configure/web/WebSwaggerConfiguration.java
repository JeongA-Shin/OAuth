package group.oauth.configure.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class WebSwaggerConfiguration {

  @Bean //반드시 빈으로 등록이 되어야 한다!
  public Docket api() {

    // Header Authorization
    ParameterBuilder aParameterBuilder = new ParameterBuilder();
    aParameterBuilder.name("Authorization") //헤더 이름
        .description("Access Tocken") //설명
        .modelRef(new ModelRef("string"))
        .parameterType("header")
        .required(false)
        .build();

    List<Parameter> aParameters = new ArrayList<>();
    aParameters.add(aParameterBuilder.build());

    Docket docket = new Docket(DocumentationType.SWAGGER_2);
    docket.apiInfo(apiInfo())
        .globalOperationParameters(aParameters)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/api/**"))
        .build();

    int tagOrd = 0;
    docket.tags(
        new Tag("User", "사용자 API", ++tagOrd)
        //new Tag("Login", "로그인 API", ++tagOrd),
       // new Tag("Admin", "관리자 API", ++tagOrd),
       // new Tag("UserLoginHist", "사용자 로그인 히스토리 API", ++tagOrd)
    );
    return docket;
  }

  private ApiInfo apiInfo() {

    return new ApiInfoBuilder()
        .title("로그인 연습용 API")
        .description("로그인 API 서비스 입니다")
        .version("1.0.0")
        //.termsOfServiceUrl("https://antstudy.tistory.com/")
        .license("LICENSE")
        .licenseUrl("")
        .build();
  }

  // 완료가 되었으면 오른쪽 URL 로 접속 => http://localhost:8080/swagger-ui.html

}
