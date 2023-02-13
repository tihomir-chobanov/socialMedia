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

        Scanner scanner = new Scanner(System.in);

        SocialMedia socialMedia = new SocialMedia();
        socialMedia.getUsers().add(socialMedia.getAdministrator());

        //     Admin add_moderator John 50
        //     Admin add_user Vasil 30
        //     Admin remove_user Vasil
        //     Vasil rename Azis
        //     John block Vasil


        while (true) {
            String input = getUserInput(scanner);
            String[] inputSplitter = splitTheInput(input);
            String command = inputSplitter[1];
            switch (command) {
                case "add_moderator":
                    socialMedia.getAdministrator().addModerator(socialMedia, inputSplitter);
                    break;
                case "add_user":
                    socialMedia.getAdministrator().addRegular(socialMedia, inputSplitter);
                    break;
                case "remove_user":
                    socialMedia.getAdministrator().removeUser(socialMedia, inputSplitter);
                    break;
                case "rename":
                    socialMedia.changeNickname(socialMedia ,inputSplitter);
                    break;
                case "block":
                    Moderator.blockUser(socialMedia, inputSplitter);
                    break;
                case "quit":
                    return;




            }

        }


    }


    private static String[] splitTheInput(String input) {
        return input.split(" ");
    }

    private static String getUserInput(Scanner scanner) {
        System.out.println("\nPlease type your command:");
        return scanner.nextLine();
    }
}