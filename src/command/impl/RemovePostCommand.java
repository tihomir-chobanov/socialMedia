package command.impl;

import socialMedia.SocialMedia;
import util.PostUtil;


public class RemovePostCommand implements Command {
    @Override
    public void execute(SocialMedia socialMedia, String[] inputSplitter)  {
        PostUtil.removePost(socialMedia, inputSplitter);
    }
}
