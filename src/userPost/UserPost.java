package userPost;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPost {
    private String nickname;
    private String content;
    private int id;

    private String postType;

    public UserPost(String nickname, String postType, String content) {
        this.nickname = nickname;
        this.content = content;
        this.postType = postType;
        this.id = PostIdGenerator.generateId();
    }

}
