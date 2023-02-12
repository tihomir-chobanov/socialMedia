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

    private Administrator administrator;
    private List<AbstractUser> users = new ArrayList<>();
    private List<UserPost> userPosts = new ArrayList<>();

    // private List<String> userNicknames = new ArrayList<>();


    public void changeNickname(String nickName, String newNickname) {
        boolean isUserToChangeInUsers = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getNickname().equals(nickName)) {
                isUserToChangeInUsers = true;
                break;
            }
        }
        boolean isNewNicknameUnique = true;
        for (AbstractUser user : users) {
            if (user.getNickname().equals(newNickname)) {
                isNewNicknameUnique = false;
                break;
            }
        }
        if (!isUserToChangeInUsers) {
            System.out.println("The user is not in this socialMedia");
        }
        if (!isNewNicknameUnique) {
            System.out.println("The nickname is already used in this socialMedia.");
        }

        if (isUserToChangeInUsers && isNewNicknameUnique) {
            for (AbstractUser user : users) {
                if (user.getNickname().equals(nickName)) {
                    user.setNickname(newNickname);
                }
            }
            for (UserPost userPost : userPosts) {
                if (userPost.getNickname().equals(nickName))
                    userPost.setNickname(newNickname);
            }
            System.out.println("Nickname changed in users and userPosts");
        }

    }

    public void getNumberRoleNameAndAgeAboutUsers() {
        int number = 1;
        for (AbstractUser user : users) {
            System.out.println("User #" + number++ + ": " + user.getRole() + " " + user.getNickname() + " " + user.getAge());
        }
    }

    public void getNicknameContentAndIdAboutPost() {
        for (int i = 0; i < userPosts.size(); i++) {
            System.out.println(userPosts.get(i).getNickname() + " " + userPosts.get(i).getContent() + " " + userPosts.get(i).getId());
        }
    }

    public void infoAboutAdmin() {
        System.out.println("Admin " + administrator.getNickname() + ", " + administrator.getAge() + " created!");
    }


}
