package publications;

import java.util.TreeSet;

public class Reply extends LikablePublication{
    private static int replyIdGenerator = 0;
    private final int replyId;

    public Reply(int publisherId, String content){
        super(publisherId,content);
        replyId = replyIdGenerator++;
    }

    Reply(int publisherId, String content, TreeSet<Integer> likes){
        this(publisherId, content);
        for(int like : likes){
            this.addLike(like);
        }
    }

    public int getId() {
        return replyId;
    }
}
