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



    // combine the methods in one - deleting isUserInList affects 11 other usages!!!;
    public boolean isUserInList(String name) {
        boolean isUserInList = false;
        for (AbstractUser user: users) {
            if (user.getNickname().equals(name)) {
                isUserInList = true;
                break;
            }
        }
        return isUserInList;
    }

    public boolean isUserUnique(String newNickname) {
        boolean isNewNicknameUnique = true;
        for (AbstractUser user : users) {
            if (user.getNickname().equals(newNickname)) {
                isNewNicknameUnique = false;
                break;
            }
        }
        return isNewNicknameUnique;
    }


}
