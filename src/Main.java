import socialMedia.SocialMedia;
import users.AbstractUser;
import users.Administrator;
import users.Moderator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*
        Admin add_moderator John 50
        Admin add_user Vasil 30
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
        Scanner scannerAdmin = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        SocialMedia socialMedia = new SocialMedia();
  //      createAdminWithNameAndAge(scannerAdmin, socialMedia);

        //     Admin add_moderator John 50
        //     Admin add_user Vasil 30
        while (true) {
            String input = getUserInput(scanner);
            String[] inputSplitter = splitTheInput(input);
            String command = inputSplitter[1];
            String nameFromInput = inputSplitter[2];
            int ageFromInput = Integer.parseInt(inputSplitter[3]);
                switch (command) {
                    case "add_moderator":
                        // if inputSplitter[1].equals("Admin) .... else Not authorized for this
                        System.out.println("Moderator is added!");
                        Moderator moderator = new Moderator(nameFromInput, ageFromInput);
                        // add moderator to List<AbstractUser>
                        socialMedia.getUsers().add(moderator);
                        socialMedia.infoAboutUsers();
                        break;
                    case "add_user":
                        System.out.println("User is added");
                        break;
                    case "quit":
                        return;
                }

        }


    }

    private static void createAdminWithNameAndAge(Scanner scannerAdmin, SocialMedia socialMedia) {
        System.out.println("Please set name and age of admin:");
        String adminName = scannerAdmin.nextLine();
        int adminAge = scannerAdmin.nextInt();
        socialMedia.setAdministrator(new Administrator(adminName, adminAge));
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