package publications;
import java.util.ArrayList;
import java.util.TreeSet;

public class User {
    private String userName;
    private static int userIdGenerator = 0;
    private final int userId;
    private String gender;
    private String email;
    private String password;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private String phoneNumber;

    private static ArrayList<User> userStore = new ArrayList<User>();
    private static ArrayList<Post> postStore = new ArrayList<Post>();
    private TreeSet<Integer> posts = new TreeSet<>();
    private TreeSet<Integer> friends = new TreeSet<>();
    private TreeSet<Integer> pending = new TreeSet<>();

    public User(String userName, String gender, String email, String password, int birthDay, int birthMonth,
            int birthYear, String phoneNumber) {
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.phoneNumber = phoneNumber;
        this.userId = userIdGenerator++;
        userStore.add(this);
    }

    User(String userName, String gender, String email, String password, int birthDay, int birthMonth, int birthYear, String phoneNumber, TreeSet<Integer> posts){
        this(userName, gender, email, password, birthDay, birthMonth, birthYear, phoneNumber);
        this.posts = posts;
    }

    //getters
    public int getId(){
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // setters

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // functionality


    // used to add post in program runtime
    public void addPost(String content, boolean isPublic) {
        Post post = new Post(userId, content, isPublic);
        posts.add(post.getId());
        postStore.add(post);
    }

    // Friends
    // private void notifyObserver(User friend) {
    //     friend.update(this.userName + " has sent you a friend request");
    // }

    // private void update(String message) {
    //     System.out.println(message);
    // }


    //returns User object with name matching the nama given. if no match found returns null
    public User getUserByName(String name) {
        User user= null;
        for (User it : userStore) {
            if (name.toUpperCase().equals(it.getUserName().toUpperCase())) {
                user = it;
            }
        }
        if (user != null) {
            return user;
        }else{
            return null;
        }
    }

    public boolean sendFriendRequest(String friendName) {
        User friend = getUserByName(friendName);
        if (friend != null) {
            friend.pending.add(userId);
            // this.notifyObserver(friend);
            return true;
        }
        return false;
    }

    //accepts friend request of user with the given name is a request exists.
    //return false if user does not exist or is not in pending
    public boolean acceptFriendRequest(String friendName) {
        User friend = getUserByName(friendName);
        if(friend != null){
            boolean isPending = pending.contains(friend.getId());
            if(isPending){
                pending.remove(friend.userId);
                friends.add(friend.userId);
                friend.friends.add(userId);
                return true;
            }
        }
        return false;
    }

    //returns true if rejecting the request was successful
    //returns false if user does not exist or did not send a friend request
    public boolean rejectFriendRequest(String friendName) {
        User friend = getUserByName(friendName);
        if(friend != null){
            boolean isPending = pending.contains(friend.getId());
            if (isPending) {
                pending.remove(friend.getId());
                return true;
            }
        }
        return false;
    }

    //returns array of User objects that have sent friend requests
    //returns null if no friend requests are present
    public User[] getFriendRequests() {
        if (pending.isEmpty()) {
            return null;
        }
        User[] arr = new User[pending.size()];
        int i = 0;
        for (int it : pending) {
            arr[i++] = getUserById(it);
        }
        return arr;
    }

    //get friends of user on which the method is called
    //return null if user has no friends
    public User[] getFriends() {
        if (friends.isEmpty()) {
            return null;
        }
        int i = 0;
        User arr[] = new User[friends.size()];
        for (int it : friends) {
            arr[i++] = getUserById(it);
        }
        return arr;
    }

    //overloaded function getFriends that gets the friends of a user using his name
    //returns null if specified user has no friends
    public User[] getFriends(String name) {
        User arr[] = getUserByName(name).getFriends();
        return arr;
    }

    //gets array of User object that are mutual friends between two user specified by name
    //return null if one of the users do not exist or if there are no mutuals between the users
    public User[] showMutualFriends(String friendName, String friendName2) {

        User user1 = getUserByName(friendName);
        User user2 = getUserByName(friendName2);

        if (user1 == null || user2 == null) {
            return null;
        }

        int cnt = 0;
        for (int it : user1.friends) {
            for (int it2 : user2.friends) {
                if (it == it2) {
                    cnt++;
                }
            }
        }

        if (cnt == 0) {
            return null;
        }

        User[] arr = new User[cnt];
        cnt = 0;

        for (int it : user1.friends) {
            for (int it2 : user2.friends) {
                if (it == it2) {
                    arr[cnt++] = getUserById(it);
                }
            }
        }

        return arr;

    }

    //for loading from file when starting program
    public static void loadPost(int publisherId,String content, boolean isPublic, TreeSet<Integer>likes,TreeSet<Integer> tags,TreeSet<Integer> comments){
        Post post = new Post(publisherId ,content, isPublic, likes, tags, comments);
        postStore.add(post);
    }

    // for returning all the posts int the system for storing them when ending the
    // program
    public static Post[] exportPosts() {
        Post[] arr = new Post[postStore.size()];
        int cnt = 0;
        for (Post post : postStore) {
            arr[cnt++] = post;
        }

        return arr;
    }

    // return array of all users in the system
    public static User[] exportUsers() {
        User[] userArr = new User[userStore.size()];
        int cnt = 0;
        for (User user : userStore) {
            userArr[cnt++] = user;
        }
        return userArr;
    }


    public static void loadUser(String userName, String gender, String email, String password, int birthDay, int birthMonth, int birthYear, String phoneNumber, TreeSet<Integer> posts){
       User user = new User(userName,gender,email,password,birthDay,birthMonth,birthYear,phoneNumber, posts);
//       userStore.add(user);
    }

    //returns the User of the specified id
    public static User getUserById(int userId){
        return userStore.get(userId);
    }

    // returns array of all posts created by the user
    public Post[] getPosts() {
        // creates array of Post objects with the oldest Post being first in array
        Post[] postArr = new Post[posts.size()];
        int cnt = 0;
        for (int post : posts) {
            postArr[cnt++] = postStore.get(post);
        }
        // reverses the array so that the latest reply is the first element
        for (int i = 0; i < postArr.length / 2; i++) {
            Post tmp = postArr[i];
            postArr[i] = postArr[postArr.length - i - 1];
            postArr[postArr.length - i - 1] = tmp;
        }

        return postArr;

    }
}
