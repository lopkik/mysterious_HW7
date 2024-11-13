import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MessageMementoTest {

    @Test
    void setState() {
        MessageMemento messageMemento = new MessageMemento("messageContent", new Date());
        messageMemento.setState("newMessageContent", new Date());

        assertEquals("newMessageContent", messageMemento.getMessageContent());
    }
}