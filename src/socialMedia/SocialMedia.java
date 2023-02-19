package socialMedia;
import lombok.Getter;
import lombok.Setter;
import userPost.UserPost;
import users.AbstractUser;
import users.Administrator;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SocialMedia {

    private Administrator administrator = new Administrator("Administrator", "Admin", 50);
    private List<AbstractUser> users = new ArrayList<>();
    private List<UserPost> userPosts = new ArrayList<>();

    public AbstractUser getUserByNickname(SocialMedia socialMedia, String nickname) {
        int indexOfUser = -1;
        int i = 0;
        for (AbstractUser user : socialMedia.getUsers()) {
            if (user.getNickname().equals(nickname)) {
                indexOfUser = i;
                break;
            }
            i++;
        }
        if (indexOfUser == -1) {
            System.out.println(Constants.USER_UNKNOWN);
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

        if (!isUserToChangeInUsers) System.out.println(Constants.USER_UNKNOWN);
        if (!isNewNickNameUnique) System.out.println(Constants.NICKNAME_IS_USED);

        if (isUserToChangeInUsers && isNewNickNameUnique) {
            changeNicknameInUsers(userToChange, newNickname);
            changeNicknameInUserPosts(userToChange, newNickname);
            System.out.println(Constants.NICKNAME_IS_CHANGED);
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
            System.out.println("User #" + number++ + ": " + user.getRole() + " " + user.getNickname() + " " + user.getAge() + " " + user.isBlocked());
        }
    }

    public void getNumberOfBlockedUsers() {
        int number = 0;
        for (AbstractUser user : users) {
            if (user.isBlocked()) {
                number++;
            }
        }
        System.out.println((number == 0) ? "There aren't any bloced users" : "There are " + number + " blocked users.");
    }

    public void getNameRoleNumberOfPostsAboutUsers() {
        for (AbstractUser user : users) {
            System.out.println(user.getNickname() + " - " + user.getRole() + ", " + user.getPersonalPostsList().size() + " posts.");
        }
    }

    public void getNicknameTypeContentAndIdAboutPost() {
        for (UserPost userPost : userPosts) {
            System.out.println(userPost.getNickname() + " " + userPost.getPostType() + " " + userPost.getContent() + " " + userPost.getId());
        }
    }

    public void getNumberOfUsers(SocialMedia socialMedia) {
        int numberOfUsers = socialMedia.getUsers().size();
        System.out.println("There are " + numberOfUsers + " users: ");
    }

    public void getOldestAndYoungestUsers(SocialMedia socialMedia) {
        int maxAge = Integer.MIN_VALUE;
        int minAge = Integer.MAX_VALUE;
        AbstractUser oldestUser = null;
        AbstractUser youngestUser = null;

        for (AbstractUser user : socialMedia.getUsers()) {
            if (user.getAge() > maxAge) {
                maxAge = user.getAge();
                oldestUser = user;
            }
            if (user.getAge() < minAge) {
                minAge = user.getAge();
                youngestUser = user;
            }
        }
        System.out.println("oldest " + oldestUser.getNickname() + " " + oldestUser.getAge());
        System.out.println("youngest " + youngestUser.getNickname() + " " + youngestUser.getAge());
    }

    public void info(SocialMedia socialMedia) {
        socialMedia.getNumberOfUsers(socialMedia);
        socialMedia.getNameRoleNumberOfPostsAboutUsers();
        socialMedia.getNumberOfBlockedUsers();
        socialMedia.getOldestAndYoungestUsers(socialMedia);
    }
}
