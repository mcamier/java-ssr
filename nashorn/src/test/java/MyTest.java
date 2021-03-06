import com.github.dockerjava.api.model.HealthCheck;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mozilla.javascript.Context;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(BlockJUnit4ClassRunner.class)
public class MyTest {

    //public static MailServerApi client;

    /*@ClassRule
    public static DockerComposeContainer smtpContainer = new DockerComposeContainer(
            new File("src/test/resources/containers.yml"))
                .withExposedService("mailhog", 1025);*/

    /*@BeforeClass
    public static void beforeAll() {
        client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(MailServerApi.class))
                .logLevel(Logger.Level.FULL)
                .target(MailServerApi.class, "http://localhost:8025/api/v2");
    }*/

    @Test
    @Ignore
    public void test() {
        //String host = smtpContainer.getServiceHost("mailhog", 1025);
        //Integer port = smtpContainer.getServicePort("mailhog", 1025);;

        String content = "<div class=\"App\" data-reactroot=\"\"><header class=\"App-header\"><img src=\"/static/media/logo.25bf045c.svg\" class=\"App-logo\" alt=\"logo\"/><p>Edit <code>src/App.js</code> and save to reload.</p><a class=\"App-link\" href=\"https://reactjs.org\" target=\"_blank\" rel=\"noopener noreferrer\">Learn React</a></header><svg width=\"100\" height=\"100\"><circle cx=\"50\" cy=\"50\" r=\"40\" stroke=\"green\" stroke-width=\"4\" fill=\"yellow\"></circle></svg></div>\n";
        //MailMain.sendMail(host, 1025, content);
        //MailResponse messages = client.getMessages();

        //assertThat(messages.count).isEqualTo(1);
    }

    @Test
    public void rhino_should_work() {
        String expected = "<div class=\"App\" data-reactroot=\"\"><header class=\"App-header\"><p>Edit <code>src/App.js</code> and save to reload. MERDE <!-- -->555</p><a class=\"App-link\" href=\"https://reactjs.org\" target=\"_blank\" rel=\"noopener noreferrer\">Learn React</a></header><svg width=\"100\" height=\"100\"><circle cx=\"50\" cy=\"50\" r=\"40\" stroke=\"green\" stroke-width=\"4\" fill=\"yellow\"></circle></svg></div>";
        Report report = new Report("project name", "Campaign name");
        RhinoMain rhino = new RhinoMain();

        String result = rhino.evaluate(report);

        assertNotNull(result);
        assertEquals(expected, result);

        Context.exit();
    }
}