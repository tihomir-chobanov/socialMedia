package command.impl;

import socialMedia.SocialMedia;

import java.io.IOException;

public interface Command {
    void execute(SocialMedia socialMedia, String[] inputSplitter) throws IOException;
}
