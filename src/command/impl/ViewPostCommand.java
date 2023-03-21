package command.impl;

import socialMedia.SocialMedia;
import util.PostUtil;

import java.io.IOException;

public class ViewPostCommand implements Command{
    @Override
    public void execute(SocialMedia socialMedia, String[] inputSplitter) throws IOException {
        PostUtil.viewPost(socialMedia, inputSplitter);
    }
}
