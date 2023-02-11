package users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Administrator extends Moderator {

    public Administrator(String role, String nickname, int age) {
        super(role, nickname, age);
    }


}
