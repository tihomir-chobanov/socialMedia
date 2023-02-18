package users;
import lombok.Getter;
import lombok.Setter;
import socialMedia.SocialMedia;
import userPost.UserPost;
import util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

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
            for (AbstractUser u : socialMedia.getUsers()) {
                if (u.getNickname().equals(searchedUser)) {
                    for (UserPost p : u.getPersonalPostsList()) {
                        System.out.println(p.getNickname() + " " + p.getContent() + " " + p.getId());
                    }
                }
            }
        } else {
            System.out.println(Constants.USER_UNKNOWN);
        }
    }

    public static void addPost(SocialMedia socialMedia, String[] inputSplitter) {
        String user = inputSplitter[0];
        String postType = inputSplitter[2];
        String postContent = inputSplitter[3];
        boolean isUserInList = socialMedia.isUserInList(user);
        int indexOfUser = getIndexOfUser(socialMedia, user);

        if (isUserInList) {
            if (!socialMedia.getUsers().get(indexOfUser).isBlocked()) {
                UserPost post = new UserPost(user, postType, postContent);
                socialMedia.getUserPosts().add(post);
                System.out.println(Constants.POST_IS_ADDED_IN_LIST_AND_PERSONALPOSTSLIST);
                socialMedia.getNicknameTypeContentAndIdAboutPost();
                socialMedia.getUserByNickname(socialMedia, user).getPersonalPostsList().add(post);
            } else {
                System.out.println(Constants.USER_IS_BLOCKED);
            }
        } else {
            System.out.println(Constants.USER_UNKNOWN);
        }
    }

}
