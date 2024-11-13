import java.util.ArrayList;
import java.util.Iterator;

public class ChatHistory implements IterableByUser{
    private final ArrayList<Message> messages;

    public ChatHistory() {
        this.messages = new ArrayList<>();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Message getLastMessageSentBy(User user) {
        for (Message message : messages.reversed()) {
            if (message.getSender() == user) {
                return message;
            }
        }
        return null;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new SearchMessagesByUser(this.messages, userToSearchWith);
    }
}
