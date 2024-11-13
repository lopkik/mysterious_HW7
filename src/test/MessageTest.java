import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    User user = new User("name");

    @Test
    void setMessageContent() {
        Message message = new Message(user, new User[]{user}, "messageContent");
        Date oldTimestamp = message.getTimestamp();
        String newMessageContent = "newMessageContent";
        try {
            Thread.sleep(2000);
        } catch (Exception ignored) {}
        message.setMessageContent(newMessageContent);

        assertEquals(newMessageContent, message.getMessageContent());
        assertNotEquals(oldTimestamp, message.getTimestamp());
    }

    @Test
    void save() {
        Message message = new Message(user, new User[]{user}, "messageContent");
        assertEquals(message.getMessageContent(), message.getMessageMemento().getMessageContent());

        String newMessageContent = "newMessageContent";
        message.setMessageContent(newMessageContent);
        assertNotEquals(message.getMessageContent(), message.getMessageMemento().getMessageContent());

        message.save();
        assertEquals(message.getMessageContent(), message.getMessageMemento().getMessageContent());
    }

    @Test
    void undo() {
        String oldMessageContent = "messageContent";
        Message message = new Message(user, new User[]{user}, oldMessageContent);
        assertEquals(message.getMessageContent(), message.getMessageMemento().getMessageContent());

        String newMessageContent = "newMessageContent";
        message.setMessageContent(newMessageContent);
        message.undo();
        assertEquals(oldMessageContent, message.getMessageContent());
    }

    @Test
    void testToString() {
        Message message = new Message(user, new User[]{user}, "messageContent");
        assertEquals("[" + message.getTimestamp() + "] " + message.getMessageContent(), message.toString());
    }
}