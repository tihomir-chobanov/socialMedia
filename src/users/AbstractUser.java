package users;

import socialMedia.SocialMedia;

public class AbstractUser {


    String nickname;
    int age;
    SocialMedia socialMedia;



    public AbstractUser(String nickname, int age) {
        this.nickname = nickname;
        this.age = age;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }


}
