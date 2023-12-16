package chat;

public class Message {
    private String message;
    private int senderId;
    private static int messageIdGenerator=0;
    private int messageId;


    public Message(int senderId, String message) {
        this.senderId=senderId;
        this.message=message;
        messageId=messageIdGenerator++;
    }

    public String getContent(){
        return message;
    }

    public int getId() {
        return messageId;
    }


    public String toString() {
        return senderId+ ": "+message;
    }


    public int getSenderId(){return senderId;}


    public String getMessage(){return message;}
}
