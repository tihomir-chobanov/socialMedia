package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;

@Getter
@Setter
public class Moderator extends Regular {


    public Moderator(String role, String nickname, int age) {
        super(role, nickname, age);
    }

    public static void blockUser(SocialMedia socialMedia, String[] inputSplitter) {
        String blocker = inputSplitter[0];
        String blocked = inputSplitter[2];
        boolean isBlockerInList = socialMedia.isUserInList(blocker);
        boolean isBlockedInList = socialMedia.isUserInList(blocked);

        int indexOfBlockerUser = getIndexOfBlockerUser(socialMedia, blocker);
        int indexOfBlockedUser = getIndexOfBlockedUser(socialMedia, blocked);

        if (!isBlockerInList || !isBlockedInList) {
            System.out.println("User is not in this socialMedia");
        } else {
            if (socialMedia.getUsers().get(indexOfBlockerUser).getRole().equals("Moderator") || socialMedia.getUsers().get(indexOfBlockerUser).getRole().equals("Administrator")) {
                socialMedia.getUsers().get(indexOfBlockedUser).setBlocked(true);
                socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
            } else {
                System.out.println("Regular users are not authorized to block other users.");
            }
        }
    }

    public static void unblockUser(SocialMedia socialMedia, String[] inputSplitter) {
        String blocker = inputSplitter[0];
        String blocked = inputSplitter[2];
        boolean isBlockerInList = socialMedia.isUserInList(blocker);
        boolean isBlockedInList = socialMedia.isUserInList(blocked);

        int indexOfBlockerUser = getIndexOfBlockerUser(socialMedia, blocker);
        int indexOfBlockedUser = getIndexOfBlockedUser(socialMedia, blocked);

        if (!isBlockerInList || !isBlockedInList) {
            System.out.println("User is not in this socialMedia");
        } else {
            if (socialMedia.getUsers().get(indexOfBlockerUser).getRole().equals("Moderator") || socialMedia.getUsers().get(indexOfBlockerUser).getRole().equals("Administrator")) {
                socialMedia.getUsers().get(indexOfBlockedUser).setBlocked(false);
                socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
            } else {
                System.out.println("Regular users are not authorized to block other users.");
            }
        }
    }






    private static int getIndexOfBlockerUser(SocialMedia socialMedia, String blocker) {
        int indexOfBlockerUser = 0;
        for (int i = 0; i < socialMedia.getUsers().size(); i++) {
            if (socialMedia.getUsers().get(i).getNickname().equals(blocker)) {
                indexOfBlockerUser = i;
            }
        }
        return indexOfBlockerUser;
    }

    private static int getIndexOfBlockedUser(SocialMedia socialMedia, String blocked) {
        int indexOfBlockedUser = 0;
        for (int i = 0; i < socialMedia.getUsers().size(); i++) {
            if (socialMedia.getUsers().get(i).getNickname().equals(blocked)) {
                indexOfBlockedUser = i;
            }
        }
        return indexOfBlockedUser;
    }


}
