import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ChatHistoryTest {
    User user = new User("user");
    User receiver = new User("receiver");
    Message message = new Message(user, new User[]{receiver}, "messageContent");
    Message message2 = new Message(user, new User[]{user}, "second message");

    @Test
    void addMessage() {
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.addMessage(message);

        assertEquals(1, chatHistory.getMessages().size());
        assertEquals("messageContent", chatHistory.getMessages().getFirst().getMessageContent());
    }

    @Test
    void getLastMessageSentBy() {
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.addMessage(message);
        chatHistory.addMessage(message2);
        Message lastMessage = chatHistory.getLastMessageSentBy(user);

        assertEquals("second message", lastMessage.getMessageContent());
    }

    @Test
    void iterator() {
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.addMessage(message);
        chatHistory.addMessage(message2);

        Message[] userMessages = {message, message2};
        int userMessagesIndex = 0;
        for (Iterator<Message> it = chatHistory.iterator(user); it.hasNext(); ) {
            Message message = it.next();
            assertEquals(userMessages[userMessagesIndex++].getMessageContent(), message.getMessageContent());
        }
        for (Iterator<Message> it = chatHistory.iterator(receiver); it.hasNext(); ) {
            Message iteratorMessage = it.next();
            assertEquals(message.getMessageContent(), iteratorMessage.getMessageContent());
        }
    }
}