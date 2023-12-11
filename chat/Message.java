package chat;

public class Message {
    private String message;
    private int senderId;

    public Message(int senderId, String message) {
        this.senderId=senderId;
        this.message=message;
    }


    public String toString() {
        return senderId+ ": "+message;
    }
}
