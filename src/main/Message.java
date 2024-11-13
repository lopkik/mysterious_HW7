import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Message {
    private final User sender;
    private final User[] receivers;
    private Date timestamp;
    private String messageContent;
    private final MessageMemento messageMemento;

    public Message(User sender, User[] receivers, String messageContent) {
        Date now = new Date();

        this.sender = sender;
        this.receivers = receivers;
        this.timestamp = now;
        this.messageContent = messageContent;
        this.messageMemento = new MessageMemento(messageContent, now);
    }

    public User getSender() {
        return sender;
    }

    public List<User> getReceivers() {
        return Arrays.asList(receivers);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public MessageMemento getMessageMemento() {
        return messageMemento;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
        this.timestamp = new Date();
    }

    public void save() {
        messageMemento.setState(this.messageContent, this.timestamp);
    }

    public void undo() {
        this.messageContent = messageMemento.getMessageContent();
        this.timestamp = messageMemento.getTimestamp();
    }

    @Override
    public String toString() {
        return "[" + this.timestamp + "] " + this.messageContent;
    }
}
