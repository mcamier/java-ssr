import com.sun.mail.smtp.SMTPTransport;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailMain {

    private static final String SMTP_SERVER = "localhost";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static final String EMAIL_FROM = "From@gmail.com";
    private static final String EMAIL_TO = "email_1@yahoo.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";

    public static void main(String[] args) throws FileNotFoundException, TranscoderException {
        String content = "\n" +
                "<svg version=\"1.1\"\n" +
                "     baseProfile=\"full\"\n" +
                "     width=\"300\" height=\"200\"\n" +
                "     xmlns=\"http://www.w3.org/2000/svg\">" +
                "  <circle cx=\"50\" cy=\"50\" r=\"40\" stroke=\"green\" stroke-width=\"4\" fill=\"yellow\" />\n" +
                "</svg>";
        transformSVGToPng(content);
        //sendMail("localhost", 1025, "no content");
    }

    public static void sendMail(String host, Integer port, String content) {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "1025"); // default port 25

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
            // from
            msg.setFrom(new InternetAddress(EMAIL_FROM));
            // to
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));
            // cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));
            // subject
            msg.setSubject(EMAIL_SUBJECT);
            // content
            msg.setContent(content, "text/html; charset=utf-8");
            msg.setSentDate(new Date());

            // Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            // connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
            // send
            t.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Response: " + t.getLastServerResponse());
            t.close();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void transformSVGToPng(String svg) throws TranscoderException, FileNotFoundException {
        PNGTranscoder t = new PNGTranscoder();

        InputStream in = new ByteArrayInputStream(svg.getBytes(StandardCharsets.UTF_8));
        TranscoderInput tin = new TranscoderInput(in);

        //TranscoderOutput tout = new TranscoderOutput(new ByteArrayOutputStream());
        OutputStream ostream = new FileOutputStream("out.jpg");
        TranscoderOutput tout = new TranscoderOutput(ostream);

        t.transcode(tin, tout);


    }
}
