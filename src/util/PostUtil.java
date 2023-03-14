package util;

import socialMedia.SocialMedia;
import userPost.UserPost;
import users.AbstractUser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static util.UserUtil.getIndexOfUser;


public class PostUtil {

    public static void addPost(SocialMedia socialMedia, String[] inputSplitter) {
        String user = inputSplitter[0];
        String postType = inputSplitter[2];
        String postContent = inputSplitter[3];
        boolean isUserInList = socialMedia.isUserInList(user);
        int indexOfUser = getIndexOfUser(socialMedia, user);

        if (isUserInList) {
            if (!socialMedia.getUsers().get(indexOfUser).isBlocked()) {
                UserPost post = new UserPost(user, postType, postContent);
                setContentIfPostIsText(inputSplitter, post);
                setDescriptionIfPostIsUrl(inputSplitter, post);
                socialMedia.getUserPosts().add(post);
                System.out.println("Post " + post.getId() + " created.");
                PostUtil.printNicknameTypeContentAndIdAboutPost(socialMedia);
                UserUtil.getUserByNickname(socialMedia, user).getPersonalPostsList().add(post);
            } else {
                System.out.println("Post not created - user blocked!");
            }
        } else {
            System.out.println(Constants.USER_UNKNOWN);
        }
    }

    private static void setDescriptionIfPostIsUrl(String[] inputSplitter, UserPost post) {
        if (post.getPostType().equals("url")) {
            boolean foundHttp = false;
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : inputSplitter) {
                if (foundHttp) {
                    stringBuilder.append(str).append(" ");
                } else if (str.startsWith("http")) {
                    foundHttp = true;
                }
            }
            String postDescrForUrlPosts = stringBuilder.toString().trim();
            post.setDescription(postDescrForUrlPosts);
        }
    }

    private static void setContentIfPostIsText(String[] inputSplitter, UserPost post) {
        if (post.getPostType().equals("text")) {
            String inputSplitterToString = String.join(" ", inputSplitter);
            String postContentForTextPosts = inputSplitterToString.substring(inputSplitterToString.indexOf("text") + 5);
            post.setContent(postContentForTextPosts);
        }
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
        try {
            String fileName = socialMedia.getUserPosts().get(i).getNickname() + "_" + postId;
            File myObj = new File("C:\\Users\\Tihomir Chobanov\\OneDrive - Foundation 0700\\Desktop\\" + fileName + ".html");
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

    public static void viewAllPostsByUser(SocialMedia socialMedia, String[] inputSplitter) {
        String actor = inputSplitter[0];
        String searchedUser = inputSplitter[2];
        boolean isActorInList = socialMedia.isUserInList(actor);
        boolean isSearchedUserInList = socialMedia.isUserInList(searchedUser);

        if (isActorInList && isSearchedUserInList ) {
            try {
                String fileName = searchedUser + "'s_posts";
                File myObj = new File("C:\\Users\\Tihomir Chobanov\\OneDrive - Foundation 0700\\Desktop\\" + fileName + ".html");
                FileWriter fw = new FileWriter(myObj, false);
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

    public static void printNicknameTypeContentAndIdAboutPost(SocialMedia socialMedia) {
        for (UserPost userPost : socialMedia.getUserPosts()) {
            System.out.println(userPost.getNickname() + " " + userPost.getPostType() + " " + userPost.getContent() + " " + userPost.getId());
        }
    }


}
