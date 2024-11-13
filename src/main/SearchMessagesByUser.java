import java.util.ArrayList;
import java.util.Iterator;

public class SearchMessagesByUser implements Iterator<Message> {
    private final ArrayList<Message> messages;
    private final User userToSearchWith;
    private int messagesIndex;

    public SearchMessagesByUser(ArrayList<Message> messages, User userToSearchWith) {
        this.messages = messages;
        this.userToSearchWith = userToSearchWith;
        this.messagesIndex = 0;
    }

    @Override
    public boolean hasNext() {
        Message message;
        while (messagesIndex < messages.size()) {
            message = messages.get(messagesIndex);
            if (message.getSender() == userToSearchWith || message.getReceivers().contains(userToSearchWith)) {
                return true;
            } else {
                messagesIndex++;
            }
        }
        return false;
    }

    @Override
    public Message next() {
        if (hasNext()) {
            return messages.get(messagesIndex++);
        }
        return null;
    }
}
