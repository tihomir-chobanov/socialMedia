package command.impl;

import socialMedia.SocialMedia;
import util.UserUtil;


public class AddModeratorCommand implements Command{
    @Override
    public void execute(SocialMedia socialMedia, String[] inputSplitter)  {
        UserUtil.addUser(socialMedia, inputSplitter, "add_moderator");
    }
}
