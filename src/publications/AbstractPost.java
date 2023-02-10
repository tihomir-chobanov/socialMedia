package publications;

import lombok.Getter;
import lombok.Setter;
import users.AbstractUser;
@Getter
@Setter
public class AbstractPost {

    String content;
    int id;

    public AbstractPost(String content, int id) {
        this.content = content;
        this.id = id;
    }

}
