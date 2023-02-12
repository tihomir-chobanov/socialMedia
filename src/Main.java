import socialMedia.SocialMedia;
import userPost.UserPost;
import users.AbstractUser;
import users.Administrator;
import users.Moderator;
import users.Regular;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*
        Admin add_moderator John 50
        Admin add_user Ivan 30
        Admin remove_user Vasil
        Admin block Kiril
        Kiril unblock Kiril
        Kiril post [image] /home/kiril/img/hello.png
	    Admin post [url] http://www.example.com някакво описание
	    Kiril post [text] Hello, world!
	    Kiril remove_post 8
	    Kiril view_post 10
	    Ivan view_all_posts Kiril
         */
        //     Scanner scannerAdmin = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        SocialMedia socialMedia = new SocialMedia();
        //     createAdminWithNameAndAge(scannerAdmin, socialMedia);



/*        UserPost kirilPost = new UserPost("Kiril", "http://www.example.com", 1);
        UserPost kolioPost = new UserPost("Kolio", "/home/kiril/img/hello.png", 2);
        socialMedia.getUserPosts().add(kirilPost);
        socialMedia.getUserPosts().add(kolioPost);

        socialMedia.getNicknameContentAndIdAboutPost();

        socialMedia.changeNickname("Petko");

        socialMedia.getNicknameContentAndIdAboutPost();*/


        //remove_user code

        //     Admin add_moderator John 50
        //     Admin add_user Vasil 30
        //     Admin remove_user Vasil
        //     Vasil rename Azis


        while (true) {
            String input = getUserInput(scanner);
            String[] inputSplitter = splitTheInput(input);
            String command = inputSplitter[1];
            switch (command) {
                case "add_moderator":
                    addModerator(socialMedia, inputSplitter);
                    break;
                case "add_user":
                    addRegular(socialMedia, inputSplitter);
                    break;
                case "remove_user":
                    if (inputSplitter[0].equals("Admin")) {
                        String userToRemove = inputSplitter[2];
                        boolean isUserToRemoveInUsers = false;
                        for (int i = 0; i < socialMedia.getUsers().size(); i++) {
                            if (socialMedia.getUsers().get(i).getNickname().equals(userToRemove)) {
                                isUserToRemoveInUsers = true;
                                break;
                            }
                        }
                        if (isUserToRemoveInUsers) {
                            for (int i = 0; i < socialMedia.getUsers().size(); i++) {
                                if (socialMedia.getUsers().get(i).getNickname().equals(userToRemove)) {
                                    socialMedia.getUsers().remove(socialMedia.getUsers().get(i));
                                    socialMedia.getNumberRoleNameAndAgeAboutUsers();
                                }
                            }
                            for (int i = 0; i < socialMedia.getUserPosts().size(); i++) {
                                if (socialMedia.getUserPosts().get(i).getNickname().equals(userToRemove)) {
                                    socialMedia.getUserPosts().remove(socialMedia.getUserPosts().get(i));
                                    socialMedia.getNicknameContentAndIdAboutPost();
                                }
                            }
                        }
                    } else {
                        System.out.println("You are not admin");
                    }

                case "rename":
                    String userToChange = inputSplitter[0];
                    String newNickname = inputSplitter[2];
                    socialMedia.changeNickname(userToChange, newNickname);
                    break;
                case "quit":
                    return;




            }

        }


    }

    private static void addRegular(SocialMedia socialMedia, String[] inputSplitter) {
        if (inputSplitter[0].equals("Admin")) {
            String nameFromInput = inputSplitter[2];
            int ageFromInput = Integer.parseInt(inputSplitter[3]);
            Moderator m = new Moderator("Regular", nameFromInput, ageFromInput);
            socialMedia.getUsers().add(m);
            System.out.println("Regular is added!");
            socialMedia.getNumberRoleNameAndAgeAboutUsers();
        } else {
            System.out.println("You are not admin");
        }
    }

    private static void addModerator(SocialMedia socialMedia, String[] inputSplitter) {
        if (inputSplitter[0].equals("Admin")) {
            String nameFromInput = inputSplitter[2];
            int ageFromInput = Integer.parseInt(inputSplitter[3]);
            Moderator m = new Moderator("Moderator", nameFromInput, ageFromInput);
            socialMedia.getUsers().add(m);
            System.out.println("Moderator is added!");
            socialMedia.getNumberRoleNameAndAgeAboutUsers();
        } else {
            System.out.println("You are not admin");
        }
    }


    private static void createAdminWithNameAndAge(Scanner scannerAdmin, SocialMedia socialMedia) {
        System.out.println("Please set name and age of admin:");
        String adminName = scannerAdmin.nextLine();
        int adminAge = scannerAdmin.nextInt();
        socialMedia.setAdministrator(new Administrator("Administrator", adminName, adminAge));
        socialMedia.infoAboutAdmin();
    }

    private static String[] splitTheInput(String input) {
        return input.split(" ");
    }

    private static String getUserInput(Scanner scanner) {
        System.out.println("\nPlease type your command:");
        return scanner.nextLine();
    }
}