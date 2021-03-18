import com.project1.database.Config;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigTest {

    @Test
    void TestHelloWorld() {
        Config javaHelloWorld = new Config();
        assertEquals("Hello World", javaHelloWorld.getHello());
    }

}
