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
        boolean isActorInList = socialMedia.isUserInList(actor);
        int postNumber = Integer.parseInt(inputSplitter[2]);

        if (isActorInList) {
            boolean isPostNumberInList = false;
            for (int i = 0; i < socialMedia.getUserPosts().size(); i++) {
                if (socialMedia.getUserPosts().get(i).getId() == postNumber) {
                    isPostNumberInList = true;
                    viewPostById(socialMedia, i);
                }
            }
            if (!isPostNumberInList) System.out.println("No such post in collection!");
        } else {
            System.out.println("User is not in this socialMedia");
        }
    }

    private static void viewPostById(SocialMedia socialMedia, int i) {
        System.out.println(socialMedia.getUserPosts().get(i).getNickname() + " " + socialMedia.getUserPosts().get(i).getContent() + " " + socialMedia.getUserPosts().get(i).getId());
    }


}
