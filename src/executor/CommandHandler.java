package executor;

import command.impl.*;
import socialMedia.SocialMedia;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private final Map<String, Command> commands;

    public CommandHandler() {
        this.commands = new HashMap<>();
        this.commands.put("remove_user", new RemoveUserCommand());
        this.commands.put("rename", new RenameUserCommand());
        this.commands.put("post", new PostCommand());
        this.commands.put("remove_post", new RemovePostCommand());
        this.commands.put("view_post", new ViewPostCommand());
        this.commands.put("view_all_posts", new ViewAllPostsCommand());
    }

    public void handle(String value, SocialMedia socialMedia, String[] inputSplitter) throws IOException {
        commands.get(value).execute(socialMedia, inputSplitter);
    }
}
