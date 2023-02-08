package users;

public class Regular extends AbstractUser {

    boolean blocked;

    public Regular(String nickname, int age) {
        super(nickname, age);
    }


    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }


}
