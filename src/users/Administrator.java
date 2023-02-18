package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;
import userPost.UserPost;
import util.Constants;

@Getter
@Setter
public class Administrator extends Moderator {

    public Administrator(String role, String nickname, int age) {
        super(role, nickname, age);
    }

        public void addModerator(SocialMedia socialMedia, String[] inputSplitter) {
            if (inputSplitter[0].equals(socialMedia.getAdministrator().getNickname())) {
                String nameFromInput = inputSplitter[2];
                int ageFromInput = Integer.parseInt(inputSplitter[3]);
                Moderator m = new Moderator("Moderator", nameFromInput, ageFromInput);
                socialMedia.getUsers().add(m);
                System.out.println(Constants.MODERATOR_IS_ADDED);
                socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
            } else {
                System.out.println(Constants.YOU_ARE_NOT_AN_ADMIN);
            }
        }
    // addRegular format
    public void addRegular(SocialMedia socialMedia, String[] inputSplitter) {
        if (inputSplitter[0].equals(socialMedia.getAdministrator().getNickname())) {
            String nameFromInput = inputSplitter[2];
            int ageFromInput = Integer.parseInt(inputSplitter[3]);
            Regular r = new Regular("Regular", nameFromInput, ageFromInput);
            socialMedia.getUsers().add(r);
            System.out.println(Constants.REGULAR_IS_ADDED);
            socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
        } else {
            System.out.println(Constants.YOU_ARE_NOT_AN_ADMIN);
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
        for (int i = 0; i < socialMedia.getUserPosts().size(); i++) {
            if (socialMedia.getUserPosts().get(i).getNickname().equals(userToRemove)) {
                socialMedia.getUserPosts().remove(socialMedia.getUserPosts().get(i));
                //error if user has 2 posts removes only one!
            }
        }
        System.out.println(Constants.USERPOSTS_AFTER_REMOVING);
        socialMedia.getNicknameTypeContentAndIdAboutPost();
    }

    private static void removeFromUsers(SocialMedia socialMedia, String userToRemove) {
        System.out.println(Constants.USERLIST_AFTER_REMOVING);
        for (int i = 0; i < socialMedia.getUsers().size(); i++) {
            if (socialMedia.getUsers().get(i).getNickname().equals(userToRemove)) {
                socialMedia.getUsers().remove(socialMedia.getUsers().get(i));
                socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
            }
        }
    }
}


