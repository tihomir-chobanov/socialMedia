package command.impl;

import socialMedia.SocialMedia;
import util.UserUtil;


public class UnblockUserCommand implements Command{
    @Override
    public void execute(SocialMedia socialMedia, String[] inputSplitter)  {
        UserUtil.blockOrUnblockUser(socialMedia, inputSplitter, false);
    }
}
