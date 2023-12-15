package chat;

public class Message {
    private String message;
    private int senderId;
    private static int messageIdGenerator=0;


    public Message(int senderId, String message) {
        this.senderId=senderId;
        this.message=message;
        messageIdGenerator++;
    }

    public int getSenderId() {
        return senderId;
    }


    public String toString() {
        return senderId+ ": "+message;
    }
}
