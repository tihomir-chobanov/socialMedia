package socialMedia;

import publications.AbstractPost;
import users.AbstractUser;
import users.Administrator;

import java.util.ArrayList;
import java.util.List;

public class SocialMedia {

    Administrator administrator;
    List<AbstractUser> users = new ArrayList<>();
    List<AbstractPost> publications = new ArrayList<>();



    public void infoAboutAdmin() {
        System.out.println("Admin " + administrator.getNickname() + ", " + administrator.getAge() + " created!");
    }


}
