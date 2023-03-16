package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;
import userPost.UserPost;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public abstract class AbstractUser {

    private String role;
    private String nickname;
    private int age;
    private SocialMedia socialMedia;
    private boolean blocked;

    private List<UserPost> personalPostsList = new ArrayList<>();

    public AbstractUser(String role, String nickname, int age) {
        this.role = role;
        this.nickname = nickname;
        this.age = age;
    }

}
