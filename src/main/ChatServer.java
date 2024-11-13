import java.util.*;

public class ChatServer {
    private final ArrayList<User> users;
    private final HashMap<User, ArrayList<User>> idToBlockedUsers;

    public ChatServer () {
        users = new ArrayList<>();
        idToBlockedUsers = new HashMap<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public HashMap<User, ArrayList<User>> getIdToBlockedUsers() {
        return idToBlockedUsers;
    }

    public void registerUser(User newUser) {
        users.add(newUser);
    }

    public void unregisterUser(User oldUser) {
        users.remove(oldUser);
    }

    public void sendMessage(Message message) {
        User sender = message.getSender();
        List<User> receivers = message.getReceivers();

        if (!users.contains(sender)) return;
        for (User receiver : receivers) {
            if (!users.contains(receiver)) return;
            if (idToBlockedUsers.getOrDefault(receiver, new ArrayList<>()).contains(sender)) {
                System.out.println("Prevented message from being sent:\n" +
                        receiver.getName() + " has blocked " + sender.getName()
                );
                return;
            }
        }

        sender.sendMessage(message);
        for(User receiver : receivers) {
            receiver.receiveMessage(message);
        }
    }

    public void blockUser(User blockingUser, User blockedUser) {
        if (idToBlockedUsers.containsKey(blockingUser)) {
            idToBlockedUsers.get(blockingUser).add(blockedUser);
        } else {
            idToBlockedUsers.put(blockingUser, new ArrayList<>(Collections.singletonList(blockedUser)));
        }
    }
}
