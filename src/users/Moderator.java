package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;
import util.Constants;

@Getter
@Setter
public class Moderator extends Regular {


    public Moderator(String role, String nickname, int age) {
        super(role, nickname, age);
    }


    // combine 2 methods in one with boolean true - block/ false - unblock
    public static void blockUser(SocialMedia socialMedia, String[] inputSplitter) {
        String blocker = inputSplitter[0];
        String blocked = inputSplitter[2];
        boolean isBlockerInList = socialMedia.isUserInList(blocker);
        boolean isBlockedInList = socialMedia.isUserInList(blocked);

        int indexOfBlockerUser = getIndexOfUser(socialMedia, blocker);
        int indexOfBlockedUser = getIndexOfUser(socialMedia, blocked);

        if (!isBlockerInList || !isBlockedInList) {
            System.out.println(Constants.USER_UNKNOWN);
        } else {
            if ("Moderator".equals(socialMedia.getUsers().get(indexOfBlockerUser).getRole())
                    || "Administrator".equals(socialMedia.getUsers().get(indexOfBlockerUser).getRole())) {
                socialMedia.getUsers().get(indexOfBlockedUser).setBlocked(true);
                System.out.println(socialMedia.getUsers().get(indexOfBlockedUser).getNickname() + " blocked.");
                socialMedia.printNumberRoleNameAgeAndBlockAboutUsers();
            } else {
                System.out.println(Constants.BLOCK_FORBIDDEN_FOR_REGULARS);
            }
        }
    }

    public static void unblockUser(SocialMedia socialMedia, String[] inputSplitter) {
        String blocker = inputSplitter[0];
        String blocked = inputSplitter[2];
        boolean isBlockerInList = socialMedia.isUserInList(blocker);
        boolean isBlockedInList = socialMedia.isUserInList(blocked);

        int indexOfBlockerUser = getIndexOfUser(socialMedia, blocker);
        int indexOfBlockedUser = getIndexOfUser(socialMedia, blocked);

        if (!isBlockerInList || !isBlockedInList) {
            System.out.println(Constants.USER_UNKNOWN);
        } else {
            if ("Moderator".equals(socialMedia.getUsers().get(indexOfBlockerUser).getRole())
                    || "Administrator".equals(socialMedia.getUsers().get(indexOfBlockerUser).getRole())) {
                socialMedia.getUsers().get(indexOfBlockedUser).setBlocked(false);
                System.out.println(Constants.USER_IS_UNBLOCKED);
                socialMedia.printNumberRoleNameAgeAndBlockAboutUsers();
            } else {
                System.out.println(Constants.BLOCK_FORBIDDEN_FOR_REGULARS);
            }
        }
    }

    public static int getIndexOfUser(SocialMedia socialMedia, String user) {
        int indexOfUser = 0;
        for (AbstractUser u : socialMedia.getUsers()) {
            if (u.getNickname().equals(user)) {
                indexOfUser = socialMedia.getUsers().indexOf(u);
                break;
            }
        }
        return indexOfUser;
    }



}
