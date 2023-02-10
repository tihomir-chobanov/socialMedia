package socialMedia;
import lombok.Getter;
import lombok.Setter;
import publications.AbstractPost;
import userPost.UserPost;
import users.AbstractUser;
import users.Administrator;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class SocialMedia {

    private Administrator administrator;
    private List<AbstractUser> users = new ArrayList<>();
    private List<AbstractPost> publications = new ArrayList<>();
    private List<UserPost> userPosts = new ArrayList<>();

    public void createUserPosts() {
        for (AbstractUser user : users) {
            for (AbstractPost publication : publications) {
                userPosts.add(new UserPost(user.getNickname(), publication.getContent(), publication.getId()));
            }
        }
    }

    public void infoAboutUsers() {
        for (int i = 0; i < users.size(); i++) {
            int number = i + 1;
            System.out.println("User #" + number + ": " + users.get(i).getNickname() + ", " + users.get(i).getAge() + "");
        }
    }

    public void infoAboutAdmin() {
        System.out.println("Admin " + administrator.getNickname() + ", " + administrator.getAge() + " created!");
    }


}
