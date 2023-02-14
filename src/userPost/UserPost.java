package userPost;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPost {
    private String nickname;
    private String content;
    private int id;

    public UserPost(String nickname, String content) {
        this.nickname = nickname;
        this.content = content;
        this.id = PostIdGenerator.generateId();
    }

}
