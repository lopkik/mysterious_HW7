import java.util.Date;

public class MessageMemento {
    private String messageContent;
    private Date timestamp;

    public MessageMemento (String messageContent, Date timestamp) {
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setState(String messageContent, Date timestamp) {
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }
}
