package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;
import util.Constants;

@Getter
@Setter
public class Administrator extends Moderator {

    public Administrator(String role, String nickname, int age) {
        super(role, nickname, age);
    }

    public void addUser(SocialMedia socialMedia, String[] inputSplitter, String userType) {
        String actor = inputSplitter[0];
        if (actor.equals(socialMedia.getAdministrator().getNickname())) {
            String nameFromInput = inputSplitter[2];
            int ageFromInput = Integer.parseInt(inputSplitter[3]);
            AbstractUser user = null;
            if (userType.equals("add_moderator")) {
                user = new Moderator("Moderator", nameFromInput, ageFromInput);
                System.out.println(nameFromInput + " created.");
            } else if (userType.equals("add_user")) {
                user = new Regular("Regular", nameFromInput, ageFromInput);
                System.out.println(nameFromInput + " created.");
            }
            socialMedia.getUsers().add(user);
            socialMedia.printNumberRoleNameAgeAndBlockAboutUsers();
        } else {
            System.out.println("No such user: " + actor + "!");
        }
    }

    public void removeUser(SocialMedia socialMedia, String[] inputSplitter) {
        if (inputSplitter[0].equals(socialMedia.getAdministrator().getNickname())) {
            String userToRemove = inputSplitter[2];
            socialMedia.isUserInList(userToRemove);
            if (socialMedia.isUserInList(userToRemove)) {
                removeFromUserPosts(socialMedia, userToRemove);
                removeFromUsers(socialMedia, userToRemove);
            } else {
                System.out.println(Constants.USER_UNKNOWN);
            }
        } else {
            System.out.println(Constants.YOU_ARE_NOT_AN_ADMIN);
        }
    }

    private static void removeFromUserPosts(SocialMedia socialMedia, String userToRemove) {
        socialMedia.getUserPosts().removeIf(post -> post.getNickname().equals(userToRemove));
        System.out.println(Constants.USERPOSTS_AFTER_REMOVING);
        socialMedia.printNicknameTypeContentAndIdAboutPost();
    }

    private static void removeFromUsers(SocialMedia socialMedia, String userToRemove) {
        socialMedia.getUsers().removeIf(user -> user.getNickname().equals(userToRemove));
        System.out.println(userToRemove + " removed.");
        System.out.println(Constants.USERLIST_AFTER_REMOVING);
        socialMedia.printNumberRoleNameAgeAndBlockAboutUsers();
    }
}


