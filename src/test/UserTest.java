import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User messageUser;
    Message message;

    @BeforeEach
    void setUp() {
        messageUser = new User("messageUser");
        message = new Message(messageUser, new User[]{messageUser}, "messageContent");
    }

    @Test
    void sendMessage() {
        User sendMessageUser = new User("sendMessageUser");
        sendMessageUser.sendMessage(message);

        assertEquals(1, sendMessageUser.getChatHistory().getMessages().size());
    }

    @Test
    void receiveMessage() {
        User receiveMessageUser = new User("receiveMessenger");
        receiveMessageUser.receiveMessage(message);

        assertEquals(1, receiveMessageUser.getChatHistory().getMessages().size());
    }

    @Test
    void undoLastMessage() {
        message.setMessageContent("newMessageContent");
        messageUser.sendMessage(message);
        messageUser.undoLastMessage();

        assertEquals(1, messageUser.getChatHistory().getMessages().size());
        assertEquals("messageContent", messageUser.getChatHistory().getLastMessageSentBy(messageUser).getMessageContent());
    }

    @Test
    void iterator() {
        Message message2 = new Message(messageUser, new User[]{messageUser}, "second message");

        User secondUser = new User("secondUser");
        User thirdUser = new User("thirdUser");
        Message message3 = new Message(secondUser, new User[]{thirdUser}, "third party message");

        messageUser.sendMessage(message);
        messageUser.sendMessage(message2);
        messageUser.sendMessage(message3);

        int messageIndex = 0;
        Message[] messageUserMessages = {message, message2};
        for (Iterator<Message> it = messageUser.iterator(messageUser); it.hasNext(); ) {
            Message message = it.next();
            assertEquals(messageUserMessages[messageIndex++].getMessageContent(), message.getMessageContent());
        }
        for (Iterator<Message> it = messageUser.iterator(secondUser); it.hasNext(); ) {
            Message message = it.next();
            assertEquals(message3.getMessageContent(), message.getMessageContent());
        }
    }
}