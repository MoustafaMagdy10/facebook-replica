package chat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import publications.User;

public class Chat {
    private String chatName;
    private static int chatIdGenerator = 0;
    private ArrayList<Message> messages;
    private ArrayList<Integer> participantsId;

    public Chat(String chatName, ArrayList<Integer> participants) {
        this.chatName = chatName;
        this.messages= new ArrayList<>();
       this.participantsId = participants;
       chatIdGenerator++;
    }

    // Getters and setters for chatName

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    // Getters for messages and participants

    public Message[] getMessages() {
        Message [] messages1 = new Message[messages.size()];
        int cnt = 0;
        for (Message message : messages){
            messages1[cnt++]= (message);
        }
        return messages1;
    }

    public User[] getParticipants() {
    //return array/arraylist of User objects
        User [] participants = new User[participantsId.size()];
        int cnt = 0;
        for (int participant : participantsId){
            participants[cnt++]= User.getUserById(participant);
        }
        return participants;
    }

    // Method to add message to the chat

    public void addMessage(int senderId,String content) {
        Message newMessage = new Message(senderId,content);       //        create new Messege Object
//        check senderID and add created Meesege object inside array list
        for (int i=0 ; i< getParticipants().length;i++){
            if (senderId== getParticipants()[i].getId()){
                this.messages.add(newMessage);
                break;
            }
        }
    }

    // Method to add participant to the chat

    public void addParticipant(int userId) {
        participantsId.add(userId);
    }

    // Method to remove participant from the chat

    public void removeParticipant(User user) {
        participantsId.remove(user);
    }

    // Method to save chat data to a file


    public void saveChatToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName,true))) {
            writer.println("Chat Name: " + chatName);
            writer.println("Messages:");
            for (Message message : messages) {
                writer.print(message.toString());
                writer.println();
            }
            writer.println("Participants:");
            for (int participantId : participantsId) {
                writer.println(participantId);
            }
            writer.println(" ");
            System.out.println("Chat data saved to file: " + fileName);
        } catch (Exception error) {
            System.out.println("Error saving chat data");
        }
    }
//  Method to load chat data from a file

    public void loadChatFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Chat Name: ")) {
                    this.chatName = line.substring("Chat Name: ".length());
//                    System.out.println(line);
                } else if (line.equals("Messages:")) {
                    while ((line = reader.readLine()) != null && !line.equals("Participants:")) {
//                    System.out.println(line);
                    }
                }
            }
            System.out.println("Chat data loaded from file: " + fileName);
        } catch (Exception error) {
            System.out.println("Error loading chat data");
        }
    }
}
