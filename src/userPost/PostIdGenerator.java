package userPost;

public class PostIdGenerator {
    static int id = 1;

    public static int generateId() {
        return id++;
    }
}
