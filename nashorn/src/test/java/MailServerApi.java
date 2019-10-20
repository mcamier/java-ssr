import feign.RequestLine;

public interface MailServerApi {

    @RequestLine("GET /messages")
    MailResponse getMessages();

}
