package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;
@Getter
@Setter
public class AbstractUser {

    String role;
    String nickname;
    int age;
    SocialMedia socialMedia;



    public AbstractUser(String role, String nickname, int age) {
        this.role = role;
        this.nickname = nickname;
        this.age = age;
    }

}
