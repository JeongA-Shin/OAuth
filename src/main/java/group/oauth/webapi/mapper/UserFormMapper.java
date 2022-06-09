package group.oauth.webapi.mapper;

import group.oauth.feature.model.User;
import group.oauth.webapi.form.UserForm;
import group.oauth.webapi.form.UserForm.Output.GetAll;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
    implementationName = "UserFormMapperImpl",
    builder=@Builder(disableBuilder = true),  //!!! lombok의  builder가 아니ㅏㄷ!! 이거 땜에 빌드 오류 엄청 남 ㅠㅜ
    componentModel = "spring"
)
public abstract class UserFormMapper {

  public abstract User toUser(UserForm.Input.Add in);

  public abstract UserForm.Output.Get toGet(User in);

  public abstract List<GetAll> toGetAllList(List<User> in);
}
