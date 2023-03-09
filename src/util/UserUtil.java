package util;

import socialMedia.SocialMedia;
import users.AbstractUser;

public class UserUtil {

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



}
