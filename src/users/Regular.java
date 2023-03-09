package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;
import util.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

@Getter
@Setter
public class Regular extends AbstractUser {

    public Regular(String role, String nickname, int age) {
        super(role, nickname, age);
    }

    public static void viewPost(SocialMedia socialMedia, String[] inputSplitter) throws IOException {
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

    private static void viewPostById(SocialMedia socialMedia, int i) throws IOException {
        int postId = i + 1;
        System.out.println("HTML view for post " + postId + " created.");
        String postContentFormatted = getPostContentFormatted(socialMedia, i);
        // view_post rename userName_postNumber
        try {
            File myObj = new File("C:\\Users\\Tihomir Chobanov\\OneDrive - Foundation 0700\\Desktop\\view_post.html");
            FileWriter fw = new FileWriter(myObj, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(postContentFormatted);
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static String getPostContentFormatted(SocialMedia socialMedia, int i) {
        String postContentFormatted = "";
        if (socialMedia.getUserPosts().get(i).getPostType().equals("text")) {
            postContentFormatted = "<p>" + socialMedia.getUserPosts().get(i).getContent() + "</p>";
        } else if (socialMedia.getUserPosts().get(i).getPostType().equals("url")) {
            postContentFormatted = "<a href=\"" + socialMedia.getUserPosts().get(i).getContent()
                    + "\">" + socialMedia.getUserPosts().get(i).getDescription() + "</a>";
        } else {
            postContentFormatted = "<img src=\"" + socialMedia.getUserPosts().get(i).getContent() + "\">";
        }
        return postContentFormatted;
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
