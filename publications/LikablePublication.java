package publications;

import java.util.NoSuchElementException;
import java.util.TreeSet;
//this is an abstract class that has data and methods
// common to all types of publication (posts,comments and replies)
public abstract class LikablePublication {
    private final int publisherId;
    private final String content;
    private TreeSet<Integer> likes = new TreeSet<Integer>();


    public LikablePublication(int publisherId, String content){
        this.publisherId = publisherId;
        this.content = content;
    }

    //takes user ID for user who did like and places it in likes TreeSet
    public void addLike(int likerId){
        likes.add(likerId);
    }

    public void removeLike(int likerId){
        if(likes.contains(likerId)){
            likes.remove(likerId);
        }else{
            throw new NoSuchElementException("this like does not exist");
        }
    }

    public int getLikeNumber(){
        return likes.size();
    }

    //returns array of Users who liked the publication
    public User[] getLikes(){
        User[] likers = new User[likes.size()];
        int cnt = 0;
        for(int liker : likes){
            likers[cnt++] = User.userStore.get(liker);
        }

        for(int i=0; i<likers.length/2; i++){
            User tmp = likers[i];
            likers[i] = likers[likers.length - i - 1];
            likers[likers.length - i - 1] = tmp;
        }

        return likers;
    }

    public int getPublisherId(){
        return publisherId;
    }

    public String getContent(){
        return content;
    }




}

