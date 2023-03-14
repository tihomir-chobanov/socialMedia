package socialMedia;
import lombok.Getter;
import lombok.Setter;
import userPost.UserPost;
import users.AbstractUser;
import users.Administrator;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SocialMedia {

    private Administrator administrator = new Administrator("Administrator", "Admin", 50);
    private List<AbstractUser> users = new ArrayList<>();
    private List<UserPost> userPosts = new ArrayList<>();







}
