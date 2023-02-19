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
            if (socialMedia.getUsers().get(indexOfBlockerUser).getRole().equals("Moderator")
                    || socialMedia.getUsers().get(indexOfBlockerUser).getRole().equals("Administrator")) {
                socialMedia.getUsers().get(indexOfBlockedUser).setBlocked(true);
                System.out.println(Constants.USER_IS_BLOCKED);
                socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
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
            if (socialMedia.getUsers().get(indexOfBlockerUser).getRole().equals("Moderator")
                    || socialMedia.getUsers().get(indexOfBlockerUser).getRole().equals("Administrator")) {
                socialMedia.getUsers().get(indexOfBlockedUser).setBlocked(false);
                System.out.println(Constants.USER_IS_UNBLOCKED);
                socialMedia.getNumberRoleNameAgeAndBlockAboutUsers();
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
