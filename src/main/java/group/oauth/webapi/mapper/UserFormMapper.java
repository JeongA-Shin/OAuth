package group.oauth.webapi.mapper;

import group.oauth.feature.model.User;
import group.oauth.webapi.form.UserForm;
import group.oauth.webapi.form.UserForm.Output.GetAll;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    implementationName = "UserFormMapperImpl"
)
public abstract class UserFormMapper {

  public abstract User toUser(UserForm.Input.Add in);

  public abstract UserForm.Output.Get toGet(User in);

  public abstract List<GetAll> toGetAllList(List<User> in);
}
