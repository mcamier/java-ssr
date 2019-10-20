import com.github.dockerjava.api.model.HealthCheck;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BlockJUnit4ClassRunner.class)
public class MyTest {

    public static MailServerApi client;

    @ClassRule
    public static DockerComposeContainer smtpContainer = new DockerComposeContainer(
            new File("src/test/resources/containers.yml"))
                .withExposedService("mailhog", 1025);

    @BeforeClass
    public static void beforeAll() {
        client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(MailServerApi.class))
                .logLevel(Logger.Level.FULL)
                .target(MailServerApi.class, "http://localhost:8025/api/v2");
    }

    @Test
    public void test() {
        String host = smtpContainer.getServiceHost("mailhog", 1025);
        Integer port = smtpContainer.getServicePort("mailhog", 1025);;

        MailMain.sendMail(host, 1025);
        MailResponse messages = client.getMessages();

        assertThat(messages.count).isEqualTo(1);
    }
}
