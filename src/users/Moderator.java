package users;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Moderator extends Regular {

    public Moderator(String role, String nickname, int age) {
        super(role, nickname, age);
    }


}
