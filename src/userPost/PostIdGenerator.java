package userPost;

public class PostIdGenerator {
    private static int id = 1;

    public static int generateId() {
        return id++;
    }
}
