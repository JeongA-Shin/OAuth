package group.oauth.webapi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import group.oauth.feature.model.QUser;
import group.oauth.webapi.form.UserForm;
import org.jadira.usertype.spi.utils.lang.StringUtils;

public class UserFormPredicate {

  public static Predicate search(UserForm.Input.GetAll in) {

    BooleanBuilder builder = new BooleanBuilder();
    QUser qUser = QUser.user;

    if (StringUtils.isNotEmpty(in.getUsername())) {
      builder.and(qUser.loginId.contains(in.getUsername()));
    }

    if (StringUtils.isNotEmpty(in.getRole())) {
      builder.and(qUser.loginId.contains(in.getRole()));
    }

    return builder;
  }

}
