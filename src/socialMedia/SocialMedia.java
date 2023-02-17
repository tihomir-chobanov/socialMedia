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

    public AbstractUser getUserByNickname(SocialMedia socialMedia, String nickname) {
        int indexOfUser = 100;
        for (int i = 0; i < socialMedia.getUsers().size(); i++) {
            if (socialMedia.getUsers().get(i).getNickname().equals(nickname)) {
                indexOfUser = i;
            }
        }
        if (indexOfUser == 100) {
            System.out.println("The is no such user!");
        }
        return socialMedia.getUsers().get(indexOfUser);
    }

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


    public void changeNickname(SocialMedia socialMedia, String[] inputSplitter) {
        String userToChange = inputSplitter[0];
        String newNickname = inputSplitter[2];

        boolean isUserToChangeInUsers = socialMedia.isUserInList(userToChange);
        boolean isNewNickNameUnique =  socialMedia.isUserUnique(newNickname);

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
            socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
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

    public void getNumberRoleNameAgeAndBlockAboutUsers() {

        int number = 1;
        for (AbstractUser user : users) {
            System.out.println("User #" + number++ + ": " + user.getRole() + " " + user.getNickname() + " " + user.getAge() + " " +user.isBlocked());
        }
    }

    public void getNicknameTypeContentAndIdAboutPost() {
        for (UserPost userPost : userPosts) {
            System.out.println(userPost.getNickname() + " " + userPost.getPostType() + " " + userPost.getContent() + " " + userPost.getId());
        }
    }

    public void infoAboutAdmin() {
        System.out.println("Admin " + administrator.getNickname() + ", " + administrator.getAge() + " created!");
    }


}
