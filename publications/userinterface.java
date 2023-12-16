package publications;
import chat.Chat;
import chat.Message;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;
public class userinterface {
   static Chat c=null ;
    static int checkprof = 0;
    static boolean checktag = true;
    static Scanner input = new Scanner(System.in);
    private static boolean exitLoop = false;

    static User[] users = User.exportUsers();
    static Post[] posts = User.exportPosts();
    static Chat[] chats = User.exportChats();
    public static void logorregmenu() throws IOException {
        LoginRegisterClass account = new LoginRegisterClass();
        System.out.println("Welcome to Facebook");
        System.out.println("-------------------------");
        int choice;
        System.out.println("Please enter your choice:\n1-Register\n2-Login\n");
        choice = input.nextInt();
        while (true) {
            if (choice == 1) {
                account.register();
                break;
            } else if (choice == 2) {
                account.Login();
                break;
            } else {
                System.out.println("Wrong choice!");
                System.out.println("Please enter your choice:\n1-Register\n2-Login");
                choice = input.nextInt();
            }
        }
    }

    public static void Display(Post x, User user) {
        System.out.println(user.getUserName());
        if (x.getTags().length != 0) {
            System.out.println("Tagged  : ");
            for (User tagged : x.getTags()) {
                System.out.print(" " + tagged.getUserName());
            }
        }
        System.out.println("\t" + x.getContent());
        System.out.println(x.getLikeNumber() + "\t " + x.getComments().length);
        System.out.println("likes" + "\t " + " Comments ");
        System.out.println("\t" + "------------------------------------");
        System.out.println("Press 0 to access Actions Menu ");
        System.out.println("Press 1 to like  ");
        System.out.println("Press 2 to remove like ");
        System.out.println("Press 3 to show likes  ");
        System.out.println("Press 4 to Comment ");
        System.out.println("Press 5 to Display Comments ");
        System.out.println("Press 6 to Display Next Post ");
        if (user.getId()!=LoginRegisterClass.x.getId())
        {
            System.out.println("Press 7 to access friends action menu");
        }
    }
    public static void DisplayOtherprofile(User user) {
        while (true) {

            if (LoginRegisterClass.x.getFriends(user.getUserName())==null)
            {
                checkprof=0;
            }
            else {
                for (User x : LoginRegisterClass.x.getFriends()) {
                    if (x.getId() == user.getId()) {
                        checkprof = 1;
                        break;
                    }
                }
            }
            if (user.getPosts().length == 0) {
                System.out.println("No Posts Available");
                otherprofileactions(checkprof, user);
            } else {
                for (Post xx : user.getPosts()) {
                    while (true) {
                        Display(xx, user);
                        int choice = input.nextInt();
                        input.nextLine();
                        if (choice == 0) {
                            userinterface.ActionMenu(LoginRegisterClass.x);
                            break;
                        } else if (choice == 1) {
                            xx.addLike(LoginRegisterClass.x.getId());
                            userinterface.Display(xx, user);
                        } else if (choice == 2) {
                            xx.removeLike(LoginRegisterClass.x.getId());
                            userinterface.Display(xx, user);
                        } else if (choice == 3) {
                            for (User liked : xx.getLikes()) {
                                System.out.println(liked.getUserName());
                            }
                        } else if (choice == 4) {
                            System.out.println(" enter the Comment");
                            String comment = input.nextLine();
                            xx.addComment(LoginRegisterClass.x.getId(), comment);
                            Comment[] comment1 = xx.getComments();
                            if (comment1.length == 0) {
                                System.out.println(" No Comments Available");
                                break;
                            }
                            commentmenu(xx, LoginRegisterClass.x);
                        } else if (choice == 5) {
                            Comment[] comment1 = xx.getComments();
                            if (comment1.length == 0) {
                                System.out.println(" No Comments Available");
                                break;
                            } else {
                                commentmenu(xx, LoginRegisterClass.x);
                            }
                        } else if (choice==6) {
                            break;
                        } else if (choice==7) {
                            otherprofileactions(checkprof, user);
                        } else {
                            System.out.println("Invalid input");
                        }
                    }
                }
            }
        }
    }

    public static void ActionMenu (User x){
        while (!exitLoop) {
            System.out.println("type 1 to add posts ");
            System.out.println(" type 2 to see all your friends");
            System.out.println(" type 3 to search for your friends");
            System.out.println(" type 4 chat with your friends");
            System.out.println(" type 5 to show your friend request");
            System.out.println(" type 6 to show your Posts");
            System.out.println(" type 7 to search for User");
            System.out.println(" type 8 to access your profile");
            System.out.println("type 9 to exit");
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 1) {
                System.out.println(" The content of the post : ");
                String content = input.nextLine();
                System.out.println(" type 1 for the post to be public \n type 2 for the post to be friends only ");
                int visuabilty_choice;
                visuabilty_choice = input.nextInt();
                input.nextLine();
                if (visuabilty_choice == 1) {
                    x.addPost(content, true);
                } else if (visuabilty_choice == 2) {
                    x.addPost(content, false);
                } else {
                    System.out.println(" invalid choice");
                }
                System.out.println("type 0 to submit  the post  ");
                System.out.println("type 1 to tag friends to the post  ");
                int c = input.nextInt();
                input.nextLine();
                if (c == 0) {
                    System.out.println(" successfully posted ");
                } else if (c == 1) {
                    checktag=true;
                    while (checktag == true) {
                        System.out.println("Enter the name of the user you want to tag : ");
                        String tagname = input.nextLine();
                        User p = LoginRegisterClass.x.getUserByName(tagname);
                        if (p == null) {
                            System.out.println(" Check  the name ");
                            continue;
                        }
                        for (int i = posts.length - 1; i > posts.length - 2; i--) {
                            posts[i].tagUser(p.getId());
                        }
                        System.out.println(" enter 0 if you want to submit the post ");
                        System.out.println(" enter 1 to tag more users ");
                        int in = input.nextInt();
                        input.nextLine();
                        if (in == 0) {
                            checktag = false;
                        } else if (in == 1) {
                            continue;
                        }
                    }
                } else {
                    System.out.println(" invalid choice");
                }
            } else if (choice == 2) {
                if (x.getFriends() == null) {
                    System.out.println("No Friends Available");
                }
                else {
                    for (User a : x.getFriends()) {
                        System.out.println(a.getUserName());
                    }
                    System.out.println("press 0 to return to menu \n press 1 to access profile of your friend ");
                    int q = input.nextInt();
                    input.nextLine();
                    if (q == 0) {
                        continue;
                    } else if (q == 1) {
                        while (true) {
                            System.out.println("Enter the name of the user :");
                            String name = input.nextLine();
                            if (LoginRegisterClass.x.getUserByName(name) == null) {
                                System.out.println("Check the name of the user");
                            } else {
                                DisplayOtherprofile(LoginRegisterClass.x.getUserByName(name));
                            }
                        }
                    }
                }
            } else if (choice == 3) {
                if (x.getFriends() == null) {
                    System.out.println("No Friends Available");
                }
                else {
                    while (true)
                    {
                        System.out.println("Input the name of your friend :");
                        String freind = input.nextLine();
                        if (LoginRegisterClass.x.getFriends(freind).length==0)
                        {
                            System.out.println("Check the name of your friend");
                        }
                        else
                        {
                            DisplayOtherprofile(x.getUserByName(freind));
                        }
                    }

                }
            } else if (choice == 4) {
                if (x.getFriends() == null) {
                    System.out.println(" No friends available");
                } else {
                    Chat[] chats1 = User.exportChats();
                    while (true) {
                        if (chats.length != 0) {
                            chats1 = new Chat[0]; // Reset chats1 to an empty array before populating it again
                            for (Chat c : chats) {
                                for (User x1 : c.getParticipants()) {
                                    if (x1.getId() == LoginRegisterClass.x.getId()) {
                                        chats1 = Arrays.copyOf(chats1, chats1.length + 1);
                                        chats1[chats1.length - 1] = c;
                                    }
                                }
                            }
                            if (chats1.length == 0) {
                                startnewchat(x);
                            } else {
                                for (Chat chat : chats1) {
                                    System.out.println(chat.getChatName());
                                }
                                System.out.println("Enter the chat name to access it:");
                                String cname = input.nextLine();
                                Chat c = null;
                                for (Chat chat : chats1) {
                                    if (cname.equals(chat.getChatName())) {
                                        c = chat;
                                        break;
                                    }
                                }
                                if (c == null) {
                                    System.out.println("Check the name of the chat.");
                                } else {
                                    Chatmenu(c, x);
                                    break;
                                }
                            }
                            System.out.println("Enter 1 to access back action menu.");
                            System.out.println("Enter 2 to access another chat.");
                            System.out.println("Enter 3 to start another chat.");
                            int choice777 = input.nextInt();
                            input.nextLine();
                            if (choice777 == 1) {
                                break;
                            } else if (choice777 == 2) {
                                continue;
                            } else if (choice777 == 3) {
                                startnewchat(x);
                            } else {
                                System.out.println("Invalid input.");
                            }
                        } else {
                            startnewchat(x);
                        }
                    }
                     }
                    } else if (choice == 5) {
                if(x.getFriendRequests()==null)
                {
                    System.out.println(" no request available");
                }
                else {
                    while (true) {
                        for (User z : x.getFriendRequests()) {
                            System.out.println(z.getUserName());
                        }
                        System.out.println("enter 1 to accept friend request");
                        System.out.println("enter 2 to remove friend request");
                        System.out.println("enter 0 to return to menu");
                        int c = input.nextInt();
                        input.nextLine();
                        if (c == 1) {
                            System.out.println(" Enter the name of the friend you want to accept his request");
                            String freind = input.nextLine();
                            if (x.acceptFriendRequest(freind))
                            {
                                System.out.println("request accepted");
                                break;
                            }
                            else {
                                System.out.println("Check the name of the user ");
                            }
                        } else if (c == 2) {
                            System.out.println(" Enter the name of the friend you want to remove his request");
                            String freind = input.nextLine();
                            if (x.rejectFriendRequest(freind))
                            {
                                System.out.println("request removed");
                                break;
                            }
                            else {
                                System.out.println("Check the name of the user ");
                            }
                        } else if (c == 0) {
                            break;
                        } else {
                            System.out.println("Invalid input");
                        }
                    }
                }
            } else if (choice == 6) {
                if (x.getPosts().length == 0) {
                    System.out.println("No posts available");
                }
            } else if (choice == 7) {
                System.out.println(" Enter the name of the user you want to search for him ");
                String name = input.nextLine();
                if (LoginRegisterClass.x.getUserByName(name) == null) {
                    System.out.println("No users available with this name");
                } else {
                    DisplayOtherprofile(LoginRegisterClass.x.getUserByName(name));
                }
            } else if (choice==8) {
                start(LoginRegisterClass.x);
            } else if (choice==9) {
                exitLoop = true;
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }
    }
  static   boolean checkreplay = false;
    public static void replaymenu (Comment comment, User user){
        checkreplay=false;
        while (checkreplay == false) {
            Reply[] zz = comment.getReplies();
            for (Reply reply : zz) {
                while (true) {
                    for (User author : users) {
                        if (author.getId() == reply.getPublisherId()) {
                            System.out.println(author.getUserName());
                        }
                    }
                    System.out.println(reply.getContent());
                    //System.out.println(reply.getLikes() + "Likes");
                    System.out.println("\n" + "press 1 to see next replay ");
                    System.out.println("press 2 to get back to the post  ");
                    System.out.println("\n" + "press 3 to like the replay ");
                    System.out.println("\n" + "press 4 to remove the like ");
                    System.out.println("\n" + "press 5 to see who liked ");
                    int x2 = input.nextInt();
                    input.nextLine();
                    if (x2 == 1)
                        break;
                    else if (x2 == 2) {
                        checkreplay = true;
                        break;
                    } else if (x2 == 3) {
                        reply.addLike(user.getId());
                    } else if (x2 == 4) {
                        reply.removeLike(user.getId());
                    } else if (x2 == 5) {
                        for (User e : reply.getLikes()) {
                            System.out.println(e.getUserName());
                        }
                    } else {
                        System.out.println("Invalid input");
                    }
                }
            }
        }
    }

    public static void commentmenu (Post post, User user){
        while (true) {
            Comment[] comment1 = post.getComments();
            if (comment1.length == 0) {
                System.out.println(" No Comments Available");
                break;
            }
            for (Comment z : comment1) {
                while (true) {
                    for (User author : users) {
                        if (author.getId() == z.getPublisherId()) {
                            System.out.println(author.getUserName());
                        }
                    }
                    System.out.println("\t" + z.getContent());
                    System.out.println("Likes :" + z.getLikes());
                    System.out.println("\n" + "press 0 to replay to the comment ");
                    System.out.println("\n" + "press 1 to see the replays to the comment ");
                    System.out.println("\n" + "press 2 to see the next comment ");
                    System.out.println("\n" + "press 3 to like the comment ");
                    System.out.println("\n" + "press 4 to remove the like ");
                    System.out.println("\n" + "press 5 to see who liked ");
                    int cc = input.nextInt();
                    input.nextLine();
                    if (cc == 0) {
                        System.out.println("Enter your replay :");
                        String re = input.nextLine();
                        z.addReply(user.getId(), re);
                        replaymenu(z, user);
                    } else if (cc == 1) {
                        if (z.getReplies().length == 0) {
                            System.out.println("No replies Available");
                            break;
                        } else {
                            replaymenu(z, user);
                        }
                    } else if (cc == 2) {
                        break;
                    } else if (cc == 3) {
                        z.addLike(user.getId());
                    } else if (cc == 4) {
                        z.removeLike(user.getId());
                    } else if (cc == 5) {
                        for (User e : z.getLikes()) {
                            System.out.println(e.getUserName());
                        }
                    } else {
                        System.out.println("invalid input");
                    }
                }
            }
        }
    }

    public static void start (User x){
        while (!exitLoop) {
            System.out.println("----------------------------------------------------------------------------");
             System.out.println("\t" + "\t" + "\t" + "\t" + "\t" + "Welcome   " + x.getUserName());
            Post[] p = x.getPosts();
            if (p.length == 0) {
                System.out.println("\t" + "No Posts Available");
                userinterface.ActionMenu(x);
            }
            for (Post xx : p) {
                while (true) {
                    Display(xx, x);
                    int choice = input.nextInt();
                    input.nextLine();
                    if (choice == 0) {
                        userinterface.ActionMenu(x);
                        break;
                    } else if (choice == 1) {
                        xx.addLike(x.getId());
                        userinterface.Display(xx, x);
                        continue;
                    } else if (choice == 2) {
                        xx.removeLike(x.getId());
                        userinterface.Display(xx, x);
                        continue;
                    } else if (choice == 3) {
                        for (User user : xx.getLikes()) {
                            System.out.println(user.getUserName());
                        }
                        userinterface.Display(xx, x);
                        continue;
                    } else if (choice == 4) {
                        System.out.println(" enter the Comment");
                        String comment = input.nextLine();
                        xx.addComment(x.getId(), comment);
                        commentmenu(xx, x);
                    } else if (choice == 5) {
                        Comment[] comment1 = xx.getComments();
                        if (comment1.length == 0) {
                            System.out.println(" No Comments Available");
                            break;
                        } else {
                            commentmenu(xx, x);
                        }
                    } else if (choice == 6) {
                        continue;
                    } else
                        System.out.println("wrong input");
                    System.out.println("\t" + "____________________________________");
                }
            }
        }
    }
    public static void Chatmenu(Chat c , User u)
    {
        while (true)
        {
            System.out.println(c.getChatName());
            if (c.getMessages().length==0)
            {
                System.out.println(" no messages available");

                System.out.println(" enter 0 to add new message ");
                System.out.println(" enter 1 to see participants ");
                System.out.println(" enter 2 add participants  ");
                System.out.println(" enter 3 to remove participants  ");
                System.out.println(" enter 4 change chat name  ");
                System.out.println(" enter 5  to exit chat  ");
                int choice = input.nextInt();
                input.nextLine();
                if (choice==0)
                {
                    System.out.println("Enter the content of the massage");
                    String msg = input.nextLine();
                    c.addMessage(u.getId(), msg);
                } else if (choice==1) {
                    for (User p: c.getParticipants())
                    {
                        System.out.println(p.getUserName());
                    }
                } else if (choice==2) {
                    System.out.println(" enter the name of the person you want to add ");
                    String profile =input.nextLine();
                    if (u.getUserByName(profile)== null)
                    {
                        System.out.println(" check the name of the user ");
                    }else
                    {
                        c.addParticipant(u.getUserByName(profile).getId());
                    }
                } else if (choice==3) {
                    System.out.println(" enter the name of the person you want to remove ");
                    String profile =input.nextLine();
                    if (u.getUserByName(profile)== null)
                    {
                        System.out.println(" check the name of the user ");
                    }else
                    {
                        if (u.getUserByName(profile).getId()==u.getId())
                        {
                            System.out.println("  are you sure you want to leave the chat \n 1 = yes \t 2= no ");
                            int choice2 =input.nextInt();
                            input.nextLine();
                            if (choice2==1)
                            {
                                c.removeParticipant(u.getId());
                                break;
                            }
                            else
                            {
                                continue;
                            }
                        }
                        else
                        {
                            c.removeParticipant(u.getUserByName(profile).getId());
                        }
                    }
                } else if (choice==4) {
                    System.out.println(" enter the chat name you want to change it to ");
                    String cname = input.nextLine();
                    c.setChatName(cname);
                } else if (choice==5) {
                    break;
                }
                else {
                    System.out.println("invalid input");
                }
            }
            else {
                for (Message m: c.getMessages())
                {
                    for (User user: users)
                    {
                        if (user.getId()==m.getSenderId())
                        {
                            System.out.println(user.getUserName() +" :"+m.getContent());
                        }
                    }
                }
                System.out.println(" enter 0 to add new message ");
                System.out.println(" enter 1 to see participants ");
                System.out.println(" enter 2 add participants  ");
                System.out.println(" enter 3 to remove participants  ");
                System.out.println(" enter 4 change chat name  ");
                System.out.println(" enter 5  to exit chat  ");
                int choice = input.nextInt();
                input.nextLine();
                if (choice==0)
                {
                    System.out.println("Enter the content of the massage");
                    String msg = input.nextLine();
                    c.addMessage(u.getId(), msg);
                } else if (choice==1) {
                    for (User p: c.getParticipants())
                    {
                        System.out.println(p.getUserName());
                    }
                } else if (choice==2) {
                    System.out.println(" enter the name of the person you want to add ");
                    String profile =input.nextLine();
                    if (u.getUserByName(profile)== null)
                    {
                        System.out.println(" check the name of the user ");
                    }else
                    {
                        c.addParticipant(u.getUserByName(profile).getId());
                    }
                } else if (choice==3) {
                    System.out.println(" enter the name of the person you want to remove ");
                    String profile =input.nextLine();
                    if (u.getUserByName(profile)== null)
                    {
                        System.out.println(" check the name of the user ");
                    }else
                    {
                        if (u.getUserByName(profile).getId()==u.getId())
                        {
                            System.out.println("  are you sure you want to leave the chat \n 1 = yes \t 2= no ");
                            int choice2 =input.nextInt();
                            input.nextLine();
                            if (choice2==1)
                            {
                                c.removeParticipant(u.getId());
                                break;
                            }
                            else
                            {
                                continue;
                            }
                        }
                        else
                        {
                            c.removeParticipant(u.getUserByName(profile).getId());
                        }
                    }
                } else if (choice==4) {
                    System.out.println(" enter the chat name you want to change it to ");
                    String cname = input.nextLine();
                    c.setChatName(cname);
                } else if (choice==5) {
                    break;
                }
                else {
                    System.out.println("invalid input");
                }

            }
        }

    }
   static TreeSet<Integer> ids = new TreeSet<>() ;

    public static void startnewchat(User x)
    {
        for (User freind:x.getFriends())
        {
            System.out.println( freind.getUserName());
        }
        System.out.println(" enter the name of the friend you want to start chat with :");
        String freind = input.nextLine();
        boolean check = false;
        for (User z : users) {
            if (z.getUserName().equals(freind)) {
                ids.add(z.getId());
                check = true;
            }
        }
        if (ids == null) {
            System.out.println(" you have to select friend(s) to chat with ");
        }
        if (!check) {
            System.out.println(" check the name of your friend");
        } else {
            System.out.println(" enter chat name ");
            String chatname =input.nextLine();
             x.startChat(chatname,ids);
             Chat []chat =User.exportChats();
            Chatmenu(chat[chat.length-1],LoginRegisterClass.x);
        }
    }
    public static void otherprofileactions ( int check, User user){
        while (true) {
            if (check == 0) {
                System.out.println(" press 0 to send friend request ");
            }
            System.out.println(" press 1 to get back to action menu ");
            System.out.println(" press 2 to show mutual friends  ");
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 0) {
                if (LoginRegisterClass.x.sendFriendRequest(user.getUserName())) {
                    System.out.println("friend request send");
                }
            } else if (choice == 1) {
                ActionMenu(LoginRegisterClass.x);
            } else if (choice == 2) {
                if ( LoginRegisterClass.x.showMutualFriends(LoginRegisterClass.x.getUserName(), user.getUserName())==null)
                {
                    System.out.println("No mutual friends ");
                }
                else
                {
                    for (User s : LoginRegisterClass.x.showMutualFriends(LoginRegisterClass.x.getUserName(), user.getUserName())) {
                        System.out.println(s.getUserName());
                    }
                }
            }
            System.out.println(" enter 0 to get back to the profile  ");
            System.out.println(" enter 1 to access Action menu  ");
            int choice33 = input.nextInt();
            input.nextLine();
            if (choice33 == 0) {
                break;
            } else if (choice33 == 1) {
                ActionMenu(LoginRegisterClass.x);
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
