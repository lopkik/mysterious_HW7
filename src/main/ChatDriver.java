import java.util.Iterator;

public class ChatDriver {
    public static void main (String[] args) {
        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");

        ChatServer chatServer = new ChatServer();
        chatServer.registerUser(alice);
        chatServer.registerUser(bob);
        chatServer.registerUser(charlie);

        System.out.println("Users can send messages to one or more other users through the chat server");
        Message message1 = new Message(alice, new User[]{bob, charlie}, "Hello");
        chatServer.sendMessage(message1);
        System.out.println();
        Message message2 = new Message(alice, new User[]{bob}, "Hey Bob, how are you?");
        chatServer.sendMessage(message2);
        System.out.println();
        Message message3 = new Message(bob, new User[]{alice}, "Not much, how about you?");
        chatServer.sendMessage(message3);
        System.out.println();
        Message message4 = new Message(alice, new User[]{charlie}, "Same here");
        message4.setMessageContent("Ignore that");
        message4.save();
        message4.setMessageContent("Sorry");
        chatServer.sendMessage(message4);
        System.out.println();

        System.out.println("Users can undo the last message they sent using the Memento design pattern");
        System.out.println("Alice's messages before undo:");
        for (Iterator<Message> it = alice.iterator(alice); it.hasNext(); ) {
            Message message = it.next();
            System.out.println(message);
        }
        alice.undoLastMessage();
        System.out.println("Alice's messages after undo:");
        for (Iterator<Message> it = alice.iterator(alice); it.hasNext(); ) {
            Message message = it.next();
            System.out.println(message);
        }
        System.out.println();

        System.out.println("Users can block messages from specific users using the Mediator design pattern");
        chatServer.blockUser(alice, charlie);
        Message message5 = new Message(charlie, new User[]{alice}, "Same about what?");
        chatServer.sendMessage(message5);
        System.out.println();

        System.out.println("Users can receive messages from other users and view the chat history for a specific user");
        Message message6 = new Message(alice, new User[]{bob}, "Same here");
        chatServer.sendMessage(message6);
        System.out.println();

        System.out.println("Bob's messages with Alice");
        for (Iterator<Message> it = bob.iterator(alice); it.hasNext(); ) {
            Message message = it.next();
            System.out.println(message);
        }
        System.out.println("Bob's messages with Charlie");
        for (Iterator<Message> it = bob.iterator(charlie); it.hasNext(); ) {
            Message message = it.next();
            System.out.println(message);
        }
    }
}
