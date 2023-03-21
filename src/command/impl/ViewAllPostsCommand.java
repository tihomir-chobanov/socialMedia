package command.impl;

import socialMedia.SocialMedia;
import util.PostUtil;

import java.io.IOException;

public class ViewAllPostsCommand implements Command{

    @Override
    public void execute(SocialMedia socialMedia, String[] inputSplitter) throws IOException {
        PostUtil.viewAllPostsByUser(socialMedia, inputSplitter);
    }
}
