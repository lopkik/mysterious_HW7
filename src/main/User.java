import java.util.Iterator;

public class User implements IterableByUser{
    private final String name;
    private final ChatHistory chatHistory;

    public User(String name) {
        this.name = name;
        this.chatHistory = new ChatHistory();
    }

    public String getName() {
        return name;
    }

    public ChatHistory getChatHistory() {
        return chatHistory;
    }

    public void sendMessage(Message message) {
        this.chatHistory.addMessage(message);
        System.out.println(name + " Sent: " + message);
    }

    public void receiveMessage(Message message) {
        this.chatHistory.addMessage(message);
        System.out.println(name + " Received: " + message);
    }

    public void undoLastMessage() {
        Message lastMessage = this.chatHistory.getLastMessageSentBy(this);
        lastMessage.undo();
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return this.chatHistory.iterator(userToSearchWith);
    }
}
