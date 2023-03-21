package command.impl;

import socialMedia.SocialMedia;
import util.PostUtil;


public class PostCommand implements Command {
    @Override
    public void execute(SocialMedia socialMedia, String[] inputSplitter) {
        PostUtil.addPost(socialMedia, inputSplitter);
    }
}
