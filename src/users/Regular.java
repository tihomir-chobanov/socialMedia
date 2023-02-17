package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;

@Getter
@Setter
public class Regular extends AbstractUser {

    public Regular(String role, String nickname, int age) {
        super(role, nickname, age);
    }

    public static void viewPost(SocialMedia socialMedia, String[] inputSplitter) {
        String actor = inputSplitter[0];
        int postNumber = Integer.parseInt(inputSplitter[2]);

        boolean isActorInList = socialMedia.isUserInList(actor);

        if (!isActorInList) {
            System.out.println("User is not in this socialMedia");
        } else {
            for (int i = 0; i < socialMedia.getUserPosts().size(); i++) {
                if (socialMedia.getUserPosts().get(i).getId() == postNumber) {
                    viewPostById(socialMedia, i);
                }
            }
        }
    }

    private static void viewPostById(SocialMedia socialMedia, int i) {
        System.out.println(socialMedia.getUserPosts().get(i).getNickname() + " " + socialMedia.getUserPosts().get(i).getContent() + " " + socialMedia.getUserPosts().get(i).getId());
    }


}
