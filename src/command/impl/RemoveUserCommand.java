package command.impl;

import socialMedia.SocialMedia;
import util.UserUtil;


public class RemoveUserCommand implements Command{
    @Override
    public void execute(SocialMedia socialMedia, String[] inputSplitter)  {
        UserUtil.removeUser(socialMedia, inputSplitter);
    }
}
