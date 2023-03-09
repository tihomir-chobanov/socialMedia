package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;
import userPost.UserPost;
import util.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static users.Moderator.getIndexOfUser;


@Getter
@Setter
public abstract class AbstractUser {

    private String role;
    private String nickname;
    private int age;
    private SocialMedia socialMedia;
    private boolean blocked;

    private List<UserPost> personalPostsList = new ArrayList<>();

    public AbstractUser(String role, String nickname, int age) {
        this.role = role;
        this.nickname = nickname;
        this.age = age;
    }

    public static void viewAllPostsByUser(SocialMedia socialMedia, String[] inputSplitter) {
        String actor = inputSplitter[0];
        String searchedUser = inputSplitter[2];
        boolean isActorInList = socialMedia.isUserInList(actor);
        boolean isSearchedUserInList = socialMedia.isUserInList(searchedUser);

        if (isActorInList && isSearchedUserInList ) {
            try {
                File myObj = new File("C:\\Users\\Tihomir Chobanov\\OneDrive - Foundation 0700\\Desktop\\view_all_posts.html");
                FileWriter fw = new FileWriter(myObj, false); // overwrite the file
                BufferedWriter bw = new BufferedWriter(fw);
                for (AbstractUser u : socialMedia.getUsers()) {
                    if (u.getNickname().equals(searchedUser)) {
                        System.out.println("HTML view for all " + searchedUser + "'s posts created.");
                        for (UserPost p : u.getPersonalPostsList()) {
                            String postContentFormatted =  getPostContentFormatted(p);
                            bw.write(postContentFormatted);
                            bw.newLine();
                        }
                    }
                }
                bw.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println(Constants.USER_UNKNOWN);
        }
    }


    private static String getPostContentFormatted(UserPost p) {
        String postContentFormatted = "";
        if (p.getPostType().equals("text")) {
            postContentFormatted = "<p>" + p.getContent() + "</p>";
        } else if (p.getPostType().equals("url")) {
            postContentFormatted = "<a href=\"" + p.getContent()
                    + "\">" + p.getDescription() + "</a>";
        } else {
            postContentFormatted = "<img src=\"" + p.getContent() + "\">";
        }
        return postContentFormatted;
    }
    // i tozi metod v PostUtil da ide!
    public static void addPost(SocialMedia socialMedia, String[] inputSplitter) {
        String user = inputSplitter[0];
        String postType = inputSplitter[2];
        String postContent = inputSplitter[3];
        boolean isUserInList = socialMedia.isUserInList(user);
        int indexOfUser = getIndexOfUser(socialMedia, user);

        if (isUserInList) {
            if (!socialMedia.getUsers().get(indexOfUser).isBlocked()) {
                UserPost post = new UserPost(user, postType, postContent);
                if (post.getPostType().equals("url")) {
                    post.setDescription(inputSplitter[4]);
                }
                socialMedia.getUserPosts().add(post);
                System.out.println("Post " + post.getId() + " created.");
                socialMedia.printNicknameTypeContentAndIdAboutPost();
                socialMedia.getUserByNickname(socialMedia, user).getPersonalPostsList().add(post);
            } else {
                System.out.println("Post not created - user blocked!");
            }
        } else {
            System.out.println(Constants.USER_UNKNOWN);
        }
    }

}
