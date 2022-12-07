import java.util.List;

public class JsonDescription {
    public String firstName;
    public String lastName;
    public String gender;
    public int age;
    public List<SocialMedia> socialmedia;

    public static class SocialMedia {
        public String web;
        public String nickname;
    }
}


