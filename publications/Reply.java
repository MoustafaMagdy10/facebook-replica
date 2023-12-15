package publications;

public class Reply extends LikablePublication{
    private static int replyIdGenerator = 0;
    private final int replyId;

    public Reply(int publisherId, String content){
        super(publisherId,content);
        replyId = replyIdGenerator++;
    }

    public int getId() {
        return replyId;
    }
}
