import java.time.LocalDateTime;
import java.util.List;

public class MailResponse {
    public Integer count;
    public Integer total;
    public Integer start;
    public List<Item> items;

    private class Item {
        public Content Content;

    }
    private class Content {
        public String Body;
        public Integer Size;
    }

}
