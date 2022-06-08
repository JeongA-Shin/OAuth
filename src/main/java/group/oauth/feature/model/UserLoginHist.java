package group.oauth.feature.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginHist {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_login_hist_id", nullable = false)
  private UUID userLoginHistId;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name="login_dt")
  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  private DateTime loginDt;

  public void defaultValue() {
    this.loginDt = DateTime.now();
  }

  @PrePersist //db에 저장 전
  public void onPrePersist() {
    this.defaultValue();
  }



}
