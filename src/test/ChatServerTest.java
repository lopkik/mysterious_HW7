import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChatServerTest {
    User user;
    User user2;

    @BeforeEach
    void setup() {
        user = new User("user");
        user2 = new User("user2");
    }

    @Test
    void registerUser() {
        ChatServer chatServer = new ChatServer();
        chatServer.registerUser(user);

        assertEquals(1, chatServer.getUsers().size());
        assertEquals("user", chatServer.getUsers().getFirst().getName());
    }

    @Test
    void unregisterUser() {
        ChatServer chatServer = new ChatServer();
        chatServer.registerUser(user);
        chatServer.registerUser(user2);
        chatServer.unregisterUser(user);

        assertEquals(1, chatServer.getUsers().size());
        assertEquals("user2", chatServer.getUsers().getFirst().getName());
    }

    @Test
    void sendMessage() {
        ChatServer chatServer = new ChatServer();
        chatServer.registerUser(user);
        chatServer.registerUser(user2);
        Message message = new Message(user, new User[]{user2}, "messageContent");
        chatServer.sendMessage(message);

        assertEquals(1, user.getChatHistory().getMessages().size());
        assertEquals("messageContent", user.getChatHistory().getMessages().getFirst().getMessageContent());
        assertEquals(1, user2.getChatHistory().getMessages().size());
        assertEquals("messageContent", user2.getChatHistory().getMessages().getFirst().getMessageContent());
    }

    @Test
    void blockUser() {
        ChatServer chatServer = new ChatServer();
        chatServer.registerUser(user);
        chatServer.registerUser(user2);
        chatServer.blockUser(user, user2);
        Message message = new Message(user2, new User[]{user}, "messageContent");
        chatServer.sendMessage(message);

        assertEquals(0, user.getChatHistory().getMessages().size());
        assertEquals(0, user2.getChatHistory().getMessages().size());
    }
}