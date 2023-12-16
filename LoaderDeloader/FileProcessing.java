package LoaderDeloader;

import chat.Chat;
import chat.Message;
import publications.Comment;
import publications.Post;
import publications.Reply;
import publications.User;

import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;


public class FileProcessing
{
 public static void loadingData() throws IOException
 {
     //Loading data of users
     File userFile =new File("LoaderDeloader/UserData.txt");

     Scanner userReader = new Scanner(userFile);

     String data;

     String userName;
     String gender;
     String email;
     String password;
     int birthDay;
     int birthMonth;
     int birthYear;
     String phoneNumber;
     TreeSet<Integer> posts = new TreeSet<>();
     TreeSet<Integer> friends = new TreeSet<>();
     TreeSet<Integer> pending = new TreeSet<>();
     TreeSet<Integer> chats = new TreeSet<>();


         while (userReader.hasNext()) {
             userName = userReader.nextLine();
             gender = userReader.nextLine();
             email = userReader.nextLine();
             password = userReader.nextLine();
             birthDay = Integer.parseInt(userReader.nextLine());
             birthMonth = Integer.parseInt(userReader.nextLine());
             birthYear = Integer.parseInt(userReader.nextLine());
             phoneNumber = userReader.nextLine();

             String post = userReader.nextLine();
             Scanner postScanner = new Scanner(post);

             while (postScanner.hasNextInt()) {
                 posts.add(postScanner.nextInt());

             }

             String friend = userReader.nextLine();
             Scanner friendScanner = new Scanner(friend);

             while (friendScanner.hasNextInt()) {
                 friends.add(friendScanner.nextInt());
             }

             String pendings = userReader.nextLine();
             Scanner pendingsScanner = new Scanner(pendings);

             while (pendingsScanner.hasNextInt()) {
                 pending.add(pendingsScanner.nextInt());
             }

             String chat = userReader.nextLine();
             Scanner chatScanner = new Scanner(chat);

             while (chatScanner.hasNextInt()) {
                 chats.add(chatScanner.nextInt());
             }

             User.loadUser(userName, gender, email, password, birthDay, birthMonth, birthYear, phoneNumber, posts, friends, pending, chats);
         }
     /////////////////////////////////
     //Loading data of posts

     File postFile = new File("LoaderDeloader/PostData.txt");

     Scanner postReader = new Scanner(postFile);
     int publisherId;
     String content;
     boolean isPublic;
     TreeSet<Integer>likes = new TreeSet<>();
     TreeSet<Integer> tags = new TreeSet<>();
     TreeSet<Integer> comments = new TreeSet<>();

     while (postReader.hasNext())
     {
         publisherId = Integer.parseInt(postReader.nextLine());
         content = postReader.nextLine();
         isPublic = Boolean.parseBoolean(postReader.nextLine());

         String like = postReader.nextLine();
         Scanner likeScanner = new Scanner(like);

         while (likeScanner.hasNextInt())
         {
             likes.add(likeScanner.nextInt());
         }

         String tag = postReader.nextLine();
         Scanner tagScanner = new Scanner(tag);

         while (tagScanner.hasNextInt())
         {
             tags.add(tagScanner.nextInt());
         }

         String comment = postReader.nextLine();
         Scanner commentScanner = new Scanner(comment);

         while (commentScanner.hasNextInt())
         {
             comments.add(commentScanner.nextInt());
         }

         User.loadPost(publisherId,content,isPublic,likes,tags,comments);
     }
     /////////////////////////////////
     //Loading data of comments
     File commentFile = new File("LoaderDeloader/CommentData.txt");

     Scanner commentReader = new Scanner(commentFile);
     int commentPublisherId;
     String commentContent;
     TreeSet<Integer> commentLikes = new TreeSet<>();
     TreeSet<Integer> replies = new TreeSet<>();

     while (commentReader.hasNext())
     {
         commentPublisherId = Integer.parseInt(commentReader.nextLine());
         commentContent = commentReader.nextLine();

         String commentLike = commentReader.nextLine();
         Scanner commentScanner = new Scanner(commentLike);

         while (commentScanner.hasNextInt())
         {
             commentLikes.add(commentScanner.nextInt());
         }

         String reply = commentReader.nextLine();
         Scanner replyScanner = new Scanner(reply);

         while (replyScanner.hasNextInt())
         {
            replies.add(replyScanner.nextInt());
         }

         Post.loadComment(commentPublisherId,commentContent,commentLikes,replies);
     }
     /////////////////////////////////
     //Loading data of replies

     File replyFile = new File("LoaderDeloader/ReplyData.txt");

     Scanner replyReader = new Scanner(replyFile);
     int replyPublisherId;
     String replyContent;
     TreeSet<Integer> replyLikes = new TreeSet<>();

     while (replyReader.hasNext())
     {
         replyPublisherId = Integer.parseInt(replyReader.nextLine());
         replyContent = replyReader.nextLine();

         String replyLike = replyReader.nextLine();
         Scanner replyLikeScanner = new Scanner(replyLike);

         while (replyLikeScanner.hasNextInt())
         {
             replyLikes.add(replyLikeScanner.nextInt());
         }

         Comment.loadReply(replyPublisherId,replyContent,replyLikes);
     }
     /////////////////////////////////
     //Loading data of chats

     File chatFile = new File("LoaderDeloader/ChatsData.txt");

     Scanner chatReader = new Scanner(chatFile);
     String chatName;
     TreeSet<Integer> participants = new TreeSet<>();
     TreeSet<Integer> messages = new TreeSet<>();

     while (chatReader.hasNext())
     {
         chatName = chatReader.nextLine();

         String participant = chatReader.nextLine();
         Scanner participantScanner = new Scanner(participant);

         while (participantScanner.hasNextInt())
         {
             participants.add(participantScanner.nextInt());
         }

         String message = chatReader.nextLine();
         Scanner messageScanner = new Scanner(message);

         while (messageScanner.hasNextInt())
         {
             messages.add(messageScanner.nextInt());
         }

         User.loadChat(chatName,participants,messages);
     }
     /////////////////////////////////
     //Loading data of messages

     File messageFile = new File("LoaderDeloader/MessagesData.txt");

     Scanner messageReader = new Scanner(messageFile);
     int senderId;
     String message;

     while (messageReader.hasNext())
     {
         senderId = Integer.parseInt(messageReader.nextLine());
         message = messageReader.nextLine();

         Chat.loadMessage(senderId,message);
     }

 }


 public static void exportData() throws IOException
 {
     //Export data of user

     BufferedWriter userWriter = new BufferedWriter(new FileWriter("LoaderDeloader/UserData.txt"));
     User[] userArray = User.exportUsers();

     for(User userData : userArray) {
         userWriter.write(userData.getUserName());
         userWriter.newLine();
         userWriter.write(userData.getGender());
         userWriter.newLine();
         userWriter.write(userData.getEmail());
         userWriter.newLine();
         userWriter.write(userData.getPassword());
         userWriter.newLine();
         userWriter.write(String.valueOf(userData.getBirthDay()));
         userWriter.newLine();
         userWriter.write(String.valueOf(userData.getBirthMonth()));
         userWriter.newLine();
         userWriter.write(String.valueOf(userData.getBirthYear()));
         userWriter.newLine();
         userWriter.write(userData.getPhoneNumber());
         userWriter.newLine();

         Post[] userPosts = userData.getPosts();

         if (userPosts != null) {
             for (Post userPost : userPosts) {
                 userWriter.write(String.valueOf(userPost.getId())+" ");
             }
         }
         userWriter.newLine();
         //
         User[] userFriends = userData.getFriends();

         if (userFriends != null) {
             for (User userFriend : userFriends) {
                 userWriter.write(String.valueOf(userFriend.getId())+" ");
             }
         }
         userWriter.newLine();
         //
         User[] userPendings = userData.getFriendRequests();

         if (userPendings != null) {
             for (User userPending : userPendings) {
                 userWriter.write(String.valueOf(userPending.getId())+" ");
             }
         }
         userWriter.newLine();
         //
         Chat[] userChats = userData.getChats();

         if (userChats != null) {
             for (Chat userChat : userChats) {
                 userWriter.write(String.valueOf(userChat.getChatId())+" ");
             }
         }

         userWriter.newLine();
     }
     userWriter.close();
     ///////////////////////////
     //Export data of posts

     BufferedWriter postWriter = new BufferedWriter(new FileWriter("LoaderDeloader/PostData.txt"));
     Post[] postArray = User.exportPosts();

     for (Post postData : postArray)
     {
         postWriter.write(String.valueOf(postData.getPublisherId()));
         postWriter.newLine();
         postWriter.write(postData.getContent());
         postWriter.newLine();
         postWriter.write(String.valueOf(postData.isPublic()));
         postWriter.newLine();

         User[] postLikers = postData.getLikes();

         if(postLikers != null) {
             for (User postLiker : postLikers) {
                 postWriter.write(String.valueOf(postLiker.getId())+" ");
             }
         }
         postWriter.newLine();
         //
         User[] postTags = postData.getTags();

         if(postTags != null) {
             for (User postTag : postTags) {
                 postWriter.write(String.valueOf(postTag.getId())+" ");
             }
         }
         postWriter.newLine();
         //
         Comment[] postComments = postData.getComments();

         if(postComments != null) {
             for (Comment postComment : postComments) {
                 postWriter.write(String.valueOf(postComment.getId())+" ");
             }
         }
         postWriter.newLine();
     }
     postWriter.close();
     ///////////////////////////
     //Export data of comments

     BufferedWriter commentWriter = new BufferedWriter(new FileWriter("LoaderDeloader/CommentData.txt"));
     Comment[] commentArray = Post.exportComments();

     for (Comment commentData : commentArray)
     {
         commentWriter.write(String.valueOf(commentData.getPublisherId()));
         commentWriter.newLine();
         commentWriter.write(commentData.getContent());
         commentWriter.newLine();

         User[] commentLikes = commentData.getLikes();

         if(commentLikes != null) {
             for (User commentsLike : commentLikes) {
                 commentWriter.write(String.valueOf(commentsLike.getId())+" ");
             }
         }
         commentWriter.newLine();
         //
         Reply[] commentReplies = commentData.getReplies();

         if(commentReplies != null) {
             for (Reply commentReply : commentReplies) {
                 commentWriter.write(String.valueOf(commentReply.getId())+" ");
             }
         }
         commentWriter.newLine();
     }
     commentWriter.close();
     ///////////////////////////
     //Export data of replies

     BufferedWriter replyWriter = new BufferedWriter(new FileWriter("LoaderDeloader/ReplyData.txt"));
     Reply[] replyArray = Comment.exportReply();

     for (Reply replyData : replyArray)
     {
         replyWriter.write(String.valueOf(replyData.getPublisherId()));
         replyWriter.newLine();
         replyWriter.write(replyData.getContent());
         replyWriter.newLine();

         User[] replyLikers = replyData.getLikes();

         if(replyLikers != null) {
             for (User replyLiker : replyLikers) {
                 replyWriter.write(String.valueOf(replyLiker.getId())+" ");
             }
         }
         replyWriter.newLine();
     }
     replyWriter.close();
     ///////////////////////////
     //Export data of chats

     BufferedWriter chatWriter = new BufferedWriter(new FileWriter("LoaderDeloader/ChatsData.txt"));
     Chat[] chatArray = User.exportChats();

     for (Chat chatData : chatArray)
     {
         chatWriter.write(chatData.getChatName());
         chatWriter.newLine();

         User[] chatParticipants = chatData.getParticipants();

         if(chatParticipants != null) {
             for (User chatParticipant : chatParticipants) {
                 chatWriter.write(String.valueOf(chatParticipant.getId())+" ");
             }
         }
         chatWriter.newLine();
         //
         Message[] chatMessages = chatData.getMessages();

         if(chatMessages != null) {
             for (Message chatMessage : chatMessages) {
                 chatWriter.write(String.valueOf(chatMessage.getId())+" ");
             }
         }
         chatWriter.newLine();
     }
     chatWriter.close();
     ///////////////////////////
     //Export data of messages

     BufferedWriter messageWriter = new BufferedWriter(new FileWriter("LoaderDeloader/MessagesData.txt"));
     Message[] messageArray = Chat.exportMessages();

     for (Message messageData : messageArray)
     {
         messageWriter.write(String.valueOf(messageData.getSenderId()));
         messageWriter.newLine();
         messageWriter.write(messageData.getMessage());
         messageWriter.newLine();
     }
 }

}
