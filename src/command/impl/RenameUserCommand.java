package command.impl;

import socialMedia.SocialMedia;
import util.UserUtil;


public class RenameUserCommand implements Command{
    @Override
    public void execute(SocialMedia socialMedia, String[] inputSplitter)  {
        UserUtil.changeNickname(socialMedia, inputSplitter);
    }
}
