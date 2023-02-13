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

    public boolean isUserInList(String user, SocialMedia socialMedia) {
        boolean isUserInList = false;
        for (int i = 0; i < socialMedia.getUsers().size(); i++) {
            if (socialMedia.getUsers().get(i).getNickname().equals(user)) {
                isUserInList = true;
                break;
            }
        }
        return isUserInList;
    }

    public boolean isUserUnique(String newNickname, SocialMedia socialMedia) {
        boolean isNewNicknameUnique = true;
        for (AbstractUser user : users) {
            if (user.getNickname().equals(newNickname)) {
                isNewNicknameUnique = false;
                break;
            }
        }
        return isNewNicknameUnique;
    }


    public void changeNickname(SocialMedia socialMedia, String[] inputSplitter) {
        String userToChange = inputSplitter[0];
        String newNickname = inputSplitter[2];

        boolean isUserToChangeInUsers = socialMedia.isUserInList(userToChange, socialMedia);
        boolean isNewNickNameUnique =  socialMedia.isUserUnique(newNickname, socialMedia);


        if (!isUserToChangeInUsers) {
            System.out.println("The user is not in this socialMedia");
        }
        if (!isNewNickNameUnique) {
            System.out.println("The nickname is already used in this socialMedia.");
        }

        if (isUserToChangeInUsers && isNewNickNameUnique) {
            changeNicknameInUsers(userToChange, newNickname);
            changeNicknameInUserPosts(userToChange, newNickname);
            System.out.println("Nickname changed in users and userPosts");
        }

    }

    private void changeNicknameInUserPosts(String userToChange, String newNickname) {
        for (UserPost userPost : userPosts) {
            if (userPost.getNickname().equals(userToChange))
                userPost.setNickname(newNickname);
        }
    }

    private void changeNicknameInUsers(String userToChange, String newNickname) {
        for (AbstractUser user : users) {
            if (user.getNickname().equals(userToChange)) {
                user.setNickname(newNickname);
            }
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
