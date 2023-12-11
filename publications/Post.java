package publications;
import java.util.ArrayList;
import java.util.TreeSet;

public class Post extends LikablePublication {
    private static int postIdGenerator = 0;
    private final int postId;

    private boolean isPublic;

    private static ArrayList<Comment> commentStore = new ArrayList<Comment>();
    private TreeSet<Integer> tags = new TreeSet<Integer>();

    private TreeSet<Integer> comments = new TreeSet<Integer>();

    public Post(int publisherId, String content, boolean isPublic){
        super(publisherId,content);
        this.isPublic = isPublic;
        postId = postIdGenerator++;
    }

    public void addComment(int publisherId, String content){
        Comment comment = new Comment(publisherId, content);
        comments.add(comment.getId());
        commentStore.add(comment);
    }

    public static void loadComment(int publisherId, String content){
        Comment comment = new Comment(publisherId, content);
        commentStore.add(comment);
    }


    public static Comment[] exportComments(){
        Comment[] arr = new Comment[commentStore.size()];
        int cnt = 0;
        for(Comment comment : commentStore){
            arr[cnt++] = comment;
        }

        return arr;
    }

    //returns array of all comments created on post
    public Comment[] getComments(){
        //creates array of Comment objects with the oldest Comment being first in array
        Comment[] commentArr = new Comment[comments.size()];
        int cnt=0;
        for(int comment : comments){
            commentArr[cnt++] = commentStore.get(comment);
        }
        //reverses the array so that the latest reply is the first element
        for(int i=0; i<commentArr.length/2; i++){
            Comment tmp = commentArr[i];
            commentArr[i] = commentArr[commentArr.length - i - 1];
            commentArr[commentArr.length - i - 1] = tmp;
        }

        return commentArr;
    }

    public void tagUser(int userId){
        tags.add(userId);
    }

    //returns array of all users who were tagged in the post
    public User[] getTags(){
        //creates array of publications.User objects with the oldest publications.User being first in array
        User[] userArr = new User[tags.size()];
        int cnt=0;
        for(int user : tags){
            userArr[cnt++] = User.getUserById(user);
        }
        //reverses the array so that the latest reply is the first element
        for(int i=0; i<userArr.length/2; i++){
            User tmp = userArr[i];
            userArr[i] = userArr[userArr.length - i - 1];
            userArr[userArr.length - i - 1] = tmp;
        }

        return userArr;

    }

    public int getId(){
        return postId;
    }

    public boolean isPublic(){
        return isPublic;
    }

}
