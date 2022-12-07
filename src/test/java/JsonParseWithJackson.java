import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonParseWithJackson {

    @Test
    void jsonParseWithJackson() throws Exception {
        ObjectMapper om = new ObjectMapper();
        File file = new File("src/test/resources/someone.json");
        JsonDescription jd = om.readValue(file, JsonDescription.class);
        assertThat(jd.firstName).isEqualTo("Sammy");
        assertThat(jd.lastName).isEqualTo("Shark");
        assertThat(jd.gender).isEqualTo("male");
        assertThat(jd.age).isEqualTo(20);
        assertThat(jd.socialmedia.get(0).web).isEqualTo("facebook");
        assertThat(jd.socialmedia.get(0).nickname).isEqualTo("SammyShark");
    }
}
