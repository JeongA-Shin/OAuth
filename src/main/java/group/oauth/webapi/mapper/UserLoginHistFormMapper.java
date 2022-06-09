package group.oauth.webapi.mapper;

import group.oauth.feature.model.UserLoginHist;
import group.oauth.webapi.form.UserLoginHistForm.Output.GetAll;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
    implementationName = "UserLoginHistFormMapperImpl",
    builder=@Builder(disableBuilder = true), //!!! lombok의  builder가 아니ㅏㄷ!! 이거 땜에 빌드 오류 엄청 남 ㅠㅜ
    componentModel = "spring"
)
public abstract class UserLoginHistFormMapper {

  public abstract List<GetAll> toGetAllList(List<UserLoginHist> in);
}
