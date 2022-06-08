package group.oauth.feature.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) //db에서 자동으로 생성되도록 해준다
  @Column(name="user_id",nullable = false)
  private UUID userId; //유저 식별번호

  @Column(name="login_id")
  private String loginId; //로그인 아이디

  @Column
  private String password;

  @Column
  private String role; //최고 관리자, 관리자, 일반

  @OneToMany //조인할 대상 테이블의 컬럼이 있어야겠지요? 이게 바로 referencedColumnName
  @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
  private List<UserLoginHist> userLoginHistList; //하나의 유저당 여러개의 로그인 이력을 가질 수 있음

}
