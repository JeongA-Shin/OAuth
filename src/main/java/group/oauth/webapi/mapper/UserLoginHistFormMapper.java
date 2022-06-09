package group.oauth.webapi.mapper;

import group.oauth.feature.model.UserLoginHist;
import group.oauth.webapi.form.UserLoginHistForm.Output.GetAll;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    implementationName = "UserLoginHistFormMapperImpl"
)
public abstract class UserLoginHistFormMapper {

  public abstract List<GetAll> toGetAllList(List<UserLoginHist> in);
}
