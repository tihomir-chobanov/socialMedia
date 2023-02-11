package users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Regular extends AbstractUser {

    boolean blocked;

    public Regular(String role, String nickname, int age) {
        super(role, nickname, age);
    }


}
