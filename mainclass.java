
import chat.Chat;
import publications.*;
import java.io.IOException;
import java.util.TreeSet;
import LoaderDeloader.FileProcessing;


import javax.imageio.IIOException;
import java.io.IOException;

public class mainclass {
    public static void main(String[] args) throws IOException { 
        FileProcessing.loadingData();
        User user1 = new User("mostafa", "male","m@gmail.com", "pass",20,1,22,"12");
       User user2 = new User("gamal", "male","m@gmail.com", "pass",20,1,22,"12");
        User[] users = User.exportUsers();
        user1.addPost("this is the first post by user 1", true); //0
        user2.addPost("this is first post by user 2", true); //1
        user1.addPost("this is second post by user 1", true); //2
        Post[] user1Posts = user1.getPosts();
       Post user1TestPost = user1Posts[0];
        user1TestPost.addComment(user2.getId(), "thats a nice post");
        user1TestPost.addComment(user2.getId(), "meshtashaaaaaat");

        user1.sendFriendRequest(user2.getUserName());
        user2.acceptFriendRequest(user1.getUserName());
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(0);
        treeSet.add(1);
        user1.startChat("test",treeSet);
        Chat[]chat =User.exportChats();
         Chat x= chat[chat.length-1];
x.addMessage(user1.getId(), "test test");
        //   FileProcessing.loadingData();
        userinterface.logorregmenu();
       // FileProcessing.exportData();


        Comment[] user1TestPostComments = user1TestPost.getComments();

        for(Comment comment : user1TestPostComments){
            System.out.println(comment.getContent() + " publisher ID:" + comment.getPublisherId());
        }

        user1TestPostComments[0].addLike(user1.getId());
//        user1TestPostComments[0].removeLike(user1.getId());

        User[] likers = user1TestPostComments[0].getLikes();
        for (User liker : likers){
            System.out.println(liker.getUserName());
        }
        User user10 = new User("ali","m","@","23213",1,11,1111,"4523523525");

        FileProcessing.exportData();
    }
}