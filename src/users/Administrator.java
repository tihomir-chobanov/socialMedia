package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;

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
                System.out.println("Moderator is added!");
                socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
            } else {
                System.out.println("You are not admin.");
            }
        }
    // addRegular format
    public void addRegular(SocialMedia socialMedia, String[] inputSplitter) {
        if (inputSplitter[0].equals(socialMedia.getAdministrator().getNickname())) {
            String nameFromInput = inputSplitter[2];
            int ageFromInput = Integer.parseInt(inputSplitter[3]);
            Regular r = new Regular("Regular", nameFromInput, ageFromInput);
            socialMedia.getUsers().add(r);
            System.out.println("Regular is added!");
            socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
        } else {
            System.out.println("You are not admin.");
        }
    }


    public void removeUser(SocialMedia socialMedia, String[] inputSplitter) {
        if (inputSplitter[0].equals(socialMedia.getAdministrator().getNickname())) {
            String userToRemove = inputSplitter[2];
            socialMedia.isUserInList(userToRemove);
            if (socialMedia.isUserInList(userToRemove)) {
                removeFromUsers(socialMedia, userToRemove);
                removeFromUserPosts(socialMedia, userToRemove);
            } else {
                System.out.println("User is not in this socialMedia.");
            }
        } else {
            System.out.println("You are not admin.");
        }
    }

    private static void removeFromUserPosts(SocialMedia socialMedia, String userToRemove) {
        for (int i = 0; i < socialMedia.getUserPosts().size(); i++) {
            if (socialMedia.getUserPosts().get(i).getNickname().equals(userToRemove)) {
                socialMedia.getUserPosts().remove(socialMedia.getUserPosts().get(i));
            }
        }
        System.out.println("---UsersPosts after removing: ");
        socialMedia.getNicknameTypeContentAndIdAboutPost();
    }

    private static void removeFromUsers(SocialMedia socialMedia, String userToRemove) {
        System.out.println("---UsersList after removing: ");
        for (int i = 0; i < socialMedia.getUsers().size(); i++) {
            if (socialMedia.getUsers().get(i).getNickname().equals(userToRemove)) {
                socialMedia.getUsers().remove(socialMedia.getUsers().get(i));
                socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
            }
        }
    }
}


