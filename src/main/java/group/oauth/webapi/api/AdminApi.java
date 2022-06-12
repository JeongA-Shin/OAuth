package group.oauth.webapi.api;

import group.oauth.webapi.common.form.ResponseForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value = "Admin", tags = {"Admin"})
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
//@PreAuthorize("hasRole('ADMIN')")
public class AdminApi {

    @SneakyThrows
    @ApiOperation("관리자 권한 확인")
    @GetMapping("/get")
    public ResponseForm.Output.Simple get() {
        return ResponseForm.Output.Simple.builder()
                .msg("Admin Access")
                .build();
    }

}
