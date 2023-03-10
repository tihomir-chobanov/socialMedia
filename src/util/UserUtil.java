package util;

import socialMedia.SocialMedia;
import userPost.UserPost;
import users.AbstractUser;
import users.Moderator;
import users.Regular;


public class UserUtil {

    public static void addUser(SocialMedia socialMedia, String[] inputSplitter, String userType) {
        String actor = inputSplitter[0];
        String nameOfUserToAdd = inputSplitter[2];
        if (socialMedia.isUserInList(nameOfUserToAdd)) {
            System.out.println(Constants.NICKNAME_IS_USED);
            return;
        }
        if (!actor.equals(socialMedia.getAdministrator().getNickname())) {
            System.out.println(Constants.USER_UNKNOWN);
            return;
        }
        String nameFromInput = inputSplitter[2];
        int ageFromInput = Integer.parseInt(inputSplitter[3]);
        AbstractUser user = null;
        if (userType.equals("add_moderator")) {
            user = new Moderator("Moderator", nameFromInput, ageFromInput);
        } else if (userType.equals("add_user")) {
            user = new Regular("Regular", nameFromInput, ageFromInput);
        }
        if (user != null) {
            socialMedia.getUsers().add(user);
            System.out.println(nameFromInput + " created.");
            UserUtil.printNumberRoleNameAgeAndBlockAboutUsers(socialMedia);
        }
    }

    public static void removeUser(SocialMedia socialMedia, String[] inputSplitter) {
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
        PostUtil.printNicknameTypeContentAndIdAboutPost(socialMedia);
    }

    private static void removeFromUsers(SocialMedia socialMedia, String userToRemove) {
        socialMedia.getUsers().removeIf(user -> user.getNickname().equals(userToRemove));
        System.out.println(userToRemove + " removed.");
        System.out.println(Constants.USERLIST_AFTER_REMOVING);
        UserUtil.printNumberRoleNameAgeAndBlockAboutUsers(socialMedia);
    }

    public static void changeNickname(SocialMedia socialMedia, String[] inputSplitter) {
        String userToChange = inputSplitter[0];
        String newNickname = inputSplitter[2];
        boolean isUserToChangeInUsers = socialMedia.isUserInList(userToChange);
        boolean isNewNickNameUnique =  socialMedia.isUserUnique(newNickname);

        if (!isUserToChangeInUsers) System.out.println(Constants.USER_UNKNOWN);
        if (!isNewNickNameUnique) System.out.println(Constants.NICKNAME_IS_USED);

        if (isUserToChangeInUsers && isNewNickNameUnique) {
            changeNicknameInUsers(userToChange, newNickname, socialMedia);
            changeNicknameInUserPosts(userToChange, newNickname, socialMedia);
            System.out.println("User " + userToChange + " is now known as " + newNickname + ".");
            UserUtil.printNumberRoleNameAgeAndBlockAboutUsers(socialMedia);
        }
    }

    private static void changeNicknameInUserPosts(String userToChange, String newNickname, SocialMedia socialMedia) {
        for (UserPost userPost : socialMedia.getUserPosts()) {
            if (userPost.getNickname().equals(userToChange))
                userPost.setNickname(newNickname);
        }
    }

    private static void changeNicknameInUsers(String userToChange, String newNickname, SocialMedia socialMedia) {
        for (AbstractUser user : socialMedia.getUsers()) {
            if (user.getNickname().equals(userToChange)) {
                user.setNickname(newNickname);
            }
        }
    }

    public static void printNumberOfUsers(SocialMedia socialMedia) {
        int numberOfUsers = socialMedia.getUsers().size();
        System.out.println("There are " + numberOfUsers + " users: ");
    }

    public static void printOldestAndYoungestUsers(SocialMedia socialMedia) {
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

    public static void printNumberOfBlockedUsers(SocialMedia socialMedia) {
        int number = 0;
        for (AbstractUser user : socialMedia.getUsers()) {
            if (user.isBlocked()) {
                number++;
            }
        }
        System.out.println((number == 0) ? "There aren't any blocked users" : "There are " + number + " blocked users.");
    }

    public static void printNameRoleNumberOfPostsAboutUsers(SocialMedia socialMedia) {
        for (AbstractUser user : socialMedia.getUsers()) {
            System.out.println(user.getNickname() + " - " + user.getRole() + ", " + user.getPersonalPostsList().size() + " posts.");
        }
    }

    public static void blockOrUnblockUser(SocialMedia socialMedia, String[] inputSplitter, boolean shouldBlock) {
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
                socialMedia.getUsers().get(indexOfBlockedUser).setBlocked(shouldBlock);
                if (shouldBlock) {
                    System.out.println(socialMedia.getUsers().get(indexOfBlockedUser).getNickname() + " blocked.");
                } else {
                    System.out.println(Constants.USER_IS_UNBLOCKED);
                }
                UserUtil.printNumberRoleNameAgeAndBlockAboutUsers(socialMedia);
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

    public static AbstractUser getUserByNickname(SocialMedia socialMedia, String nickname) {
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

    public static void printNumberRoleNameAgeAndBlockAboutUsers(SocialMedia socialMedia) {
        int number = 1;
        for (AbstractUser user : socialMedia.getUsers()) {
            System.out.println("User #" + number++ + ": " + user.getRole() + " " + user.getNickname() + " " + user.getAge() + " " + user.isBlocked());
        }
    }

    public static void info(SocialMedia socialMedia) {
        UserUtil.printNumberOfUsers(socialMedia);
        UserUtil.printNameRoleNumberOfPostsAboutUsers(socialMedia);
        UserUtil.printNumberOfBlockedUsers(socialMedia);
        UserUtil.printOldestAndYoungestUsers(socialMedia);
    }


}
