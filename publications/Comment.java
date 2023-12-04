package publications;
import java.util.TreeSet;

public class Comment extends LikablePublication {
    private static int commentIdGenerator = 0;
    private final int commentId;

    TreeSet<Integer> replies = new TreeSet<Integer>(); // carries the Ids of the replies that were made on the comment

    public Comment(int publisherId, String content){
        super(publisherId,content);
        commentId = commentIdGenerator++;
    }

    //creates a Reply object, places the ID of the reply in the replies TreeSet and then
    //places the object in the array for storing replies
    public void addReply(int publisherId, String content){
        Reply reply = new Reply(publisherId, content);
        replies.add(reply.getId());
        PublicationData.replyStore.add(reply);
    }

    public int getId(){
        return commentId;
    }

    //returns an array of the replies made on the comment
    //returns an array of Reply objects
    public Reply[] getReplies(){
        //creates array of Reply objects with the oldest Reply being first in array
        Reply[] replyArr = new Reply[replies.size()];
        int cnt=0;
        for(int reply : replies){
           replyArr[cnt++] = PublicationData.replyStore.get(reply);
        }
        //reverses the array so that the latest reply is the first element
        for(int i=0; i<replyArr.length/2; i++){
            Reply tmp = replyArr[i];
            replyArr[i] = replyArr[replyArr.length - i - 1];
            replyArr[replyArr.length - i - 1] = tmp;
        }

        return replyArr;

    }



}
