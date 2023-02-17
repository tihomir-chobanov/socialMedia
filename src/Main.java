import socialMedia.SocialMedia;
import users.AbstractUser;
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
	    Ivan view_all_posts Kolio
         */

        Scanner scanner = new Scanner(System.in);

        SocialMedia socialMedia = new SocialMedia();
        socialMedia.getUsers().add(socialMedia.getAdministrator());

        //CommandHandler - remove methoda v nov klas, kakto i drugite metodi i te v novi klasove;
        //BuilderPattern / Factory pattern;

        //     Admin add_moderator John 50
        //     Admin add_user Vasil 30
        //     Admin remove_user Vasil
        //     John rename Ivan
        //     Ivan block Vasil
        //     Vasil post image /home/kiril/img/hello.png
        //     John view_post 2

        while (true) {
            String input = getUserInput(scanner);
            String[] inputSplitter = splitTheInput(input);
            String command = inputSplitter[1];
            switch (command) {
                case "add_moderator" -> socialMedia.getAdministrator().addModerator(socialMedia, inputSplitter);
                case "add_user" -> socialMedia.getAdministrator().addRegular(socialMedia, inputSplitter);
                case "remove_user" -> socialMedia.getAdministrator().removeUser(socialMedia, inputSplitter);
                case "rename" -> socialMedia.changeNickname(socialMedia, inputSplitter);
                case "block" -> Moderator.blockUser(socialMedia, inputSplitter);
                case "unblock" -> Moderator.unblockUser(socialMedia, inputSplitter);
                case "post" -> Regular.addPost(socialMedia, inputSplitter);
                case "view_post" -> Regular.viewPost(socialMedia, inputSplitter);
                case "view_all_post" -> AbstractUser.viewAllPostsByUser(socialMedia, inputSplitter);
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