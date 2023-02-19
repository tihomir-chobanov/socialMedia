package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;
import util.Constants;

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
            if (!isPostNumberInList) System.out.println(Constants.POST_IS_MISSING);
        } else {
            System.out.println(Constants.USER_UNKNOWN);
        }
    }

    private static void viewPostById(SocialMedia socialMedia, int i) {
        int postId = i + 1;
        System.out.println("HTML view for post " + postId + " created.");
        System.out.println(socialMedia.getUserPosts().get(i).getNickname() + " " + socialMedia.getUserPosts().get(i).getContent() + " " + socialMedia.getUserPosts().get(i).getId());
    }


    public static void removePost(SocialMedia socialMedia, String[] inputSplitter) {
        String actor = inputSplitter[0];
        boolean isActorInList = socialMedia.isUserInList(actor);
        int postNumber = Integer.parseInt(inputSplitter[2]);

        if (isActorInList) {
            boolean isPostNumberInList = false;
            for (int i = 0; i < socialMedia.getUserPosts().size(); i++) {
                if (socialMedia.getUserPosts().get(i).getId() == postNumber) {
                    isPostNumberInList = true;
                    socialMedia.getUserPosts().remove(socialMedia.getUserPosts().get(i));
                    System.out.println(Constants.POST_IS_REMOVED);
                }
            }
            if (!isPostNumberInList) System.out.println(Constants.POST_IS_MISSING);
        } else {
            System.out.println(Constants.USER_UNKNOWN);
        }
    }
}
