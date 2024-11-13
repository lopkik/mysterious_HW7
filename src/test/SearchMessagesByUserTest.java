import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SearchMessagesByUserTest {
    User user;
    User user2;
    Message message;
    Message message2;

    @BeforeEach
    void setup() {
        user = new User("user");
        user2 = new User("user2");
        message = new Message(user, new User[]{user}, "messageContent");
        message2 = new Message(user2, new User[]{user2}, "messageContent2");
    }

    @Test
    void hasNext() {
        SearchMessagesByUser iter = new SearchMessagesByUser(
                new ArrayList<>(Arrays.asList(new Message[]{message})),
                user
        );
        SearchMessagesByUser iter2 = new SearchMessagesByUser(
                new ArrayList<>(Arrays.asList(new Message[]{message})),
                user2
        );

        assertTrue(iter.hasNext());
        assertFalse(iter2.hasNext());
    }

    @Test
    void next() {
        SearchMessagesByUser iter = new SearchMessagesByUser(
                new ArrayList<>(Arrays.asList(new Message[]{message})),
                user
        );
        Message iterMessage = iter.next();

        assertEquals("messageContent", iterMessage.getMessageContent());
    }
}