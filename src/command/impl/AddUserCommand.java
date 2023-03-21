package command.impl;

import socialMedia.SocialMedia;
import util.UserUtil;


public class AddUserCommand implements Command{
    @Override
    public void execute(SocialMedia socialMedia, String[] inputSplitter)  {
        UserUtil.addUser(socialMedia, inputSplitter, "add_user");
    }
}
