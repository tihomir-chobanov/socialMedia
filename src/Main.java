import executor.CommandHandler;
import socialMedia.SocialMedia;
import util.UserUtil;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
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

	    CommandHandler - remove methoda v nov klas, kakto i drugite metodi i te v novi klasove;
        BuilderPattern / Factory pattern;

        Admin add_moderator John 50
        Admin add_user Vasil 30
        Admin remove_user Vasil
        John rename Ivan
        Ivan block Vasil
        Vasil post image /home/kiril/img/hello.png
        John view_post 2
        Vasil post url https://github.com/tihomir-chobanov this is my gitAccount
        Admin post image C:\\socialMediaPhotos\\spices-cooking.jpg
         */

        Scanner scanner = new Scanner(System.in);
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.getUsers().add(socialMedia.getAdministrator());
        CommandHandler handler = new CommandHandler();

        while (true) {
            String input = getUserInput(scanner);
            String[] inputSplitter = splitTheInput(input);
            if (inputSplitter[0].equals("quit")) {
                return;
            }
            if (inputSplitter[0].equals("info")) {
                UserUtil.info(socialMedia);
            } else {
                String command = inputSplitter[1];
                try {
                    handler.handle(command, socialMedia, inputSplitter);
                } catch (Exception e) {
                    System.out.println("Please enter a valid command");
                }
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
