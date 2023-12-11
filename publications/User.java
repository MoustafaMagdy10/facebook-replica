package publications;
import java.util.ArrayList;
import java.util.TreeSet;

public class User {
    private  String userName;
    private static int userIdGenerator = 0;
    private final int userId;
    private  String gender;
    private  String email;
    private  String password;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private String phoneNumber;

    private Boolean showMyFriends=true;

    private static ArrayList<User> userStore = new ArrayList<User>();
    private static ArrayList<Post> postStore = new ArrayList<Post>();
    private TreeSet<Integer> posts = new TreeSet<>();
    private ArrayList<User> friends = new ArrayList<>();
    private ArrayList<User> pending = new ArrayList<>();


    public User(String userName, String gender, String email, String password, int birthDay, int birthMonth, int birthYear, String phoneNumber) {
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
    public Boolean getShowMyFriends() {
        return showMyFriends;
    }

    //setters

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

    public void setShowMyFriends(Boolean showMyFriends) {
        this.showMyFriends = showMyFriends;
    }


    //functionality

    //used to add post in program runtime
    public void addPost(String content, boolean isPublic){
        Post post = new Post(userId ,content, isPublic);
        posts.add(post.getId());
        postStore.add(post);
    }

    //Friends
    private void notifyObserver(User friend) {
       friend.update(this.userName + " has sent you a friend request");
    }
    private void update(String message) {
        System.out.println(message);
    }
    private User searchUser(String friendName ,Boolean inPending ){
        User friend = null;
       if(!inPending){

           for (User it: userStore){
               if (friendName.toUpperCase().equals(it.userName.toUpperCase())){
                   friend = it;
               }
           }

       }else{
           for (User it: this.pending){
               if (friendName.toUpperCase().equals(it.getUserName().toUpperCase())){
                   friend = it;
               }
           }
       }
        if(friend != null){
            return friend;
        }
        return null;
    }
    public void sendFriendRequest(String friendName){
        User friend = searchUser(friendName , false);
        if(friend != null){
            friend.pending.add(this);
            this.notifyObserver(friend);
        }
        else
            System.out.println("No one such name exist");
    }
    public void acceptFriendRequest(String friendName){


        User friend = searchUser(friendName , true);;
        if(friend != null){
            this.pending.remove(friend);
            this.friends.add(friend);
            friend.friends.add(this);
        }
        else{
            System.out.println("No one with such a name exists");
        }

    }
    public void rejectFriendRequest(String friendName){
        User friend = searchUser(friendName , true);;
        if(friend != null){
            this.pending.remove(friend);
        }
        else{
            System.out.println("No one with such a name exists");
        }
    }
    public void showFriendRequests(){
        if(this.pending.isEmpty()){
            System.out.println("No friend request found");
            return;
        }
        for (User it : this.pending  ){
            System.out.println(it.userName);
        }
    }
    public void showFriends(){
        for (User it : this.friends  ){
            System.out.println(it.userName);
        }
    }
    public void showFriends(String name ){
        User friend = searchUser(name ,false );
        if(friend == null)
            return;
        if(friend.friends.isEmpty()){
            System.out.println("No friends found");
            return;
        }
        if(friend.getShowMyFriends()) {
            for (User it : friend.friends) {
                System.out.println(it.userName);
            }
        }
        else{
            System.out.println("You can't see " + friend.userName + "'s friends");
        }
    }
    public void showMutualFriends(String friendName){
        User friend = searchUser(friendName , false);
        if(friend == null){
            System.out.println("No such a friend exists");
            return;
        }
        for (User it : this.friends){
            for(User it2 : friend.friends){
                if(it.userName.equals(it2.userName)){
                    System.out.println(it.userName);
                }
            }
        }

    }

    //for loading from file when starting program
    public static void loadPost(int publisherId,String content, boolean isPublic){
        Post post = new Post(publisherId ,content, isPublic);
        postStore.add(post);
    }

    //for returning all the posts int the system for storing them when ending the program
    public static Post[] exportPosts(){
        Post[] arr = new Post[postStore.size()];
        int cnt = 0;
        for(Post post : postStore){
            arr[cnt++] = post;
        }

        return arr;
    }

    //return array of all users in the system
    public static User[] exportUsers(){
        User[] userArr = new User[userStore.size()];
        int cnt = 0;
        for(User user : userStore){
            userArr[cnt++] = user;
        }
        return userArr;
    }

    public static void loadUser(String userName, String gender, String email, String password, int birthDay, int birthMonth, int birthYear, String phoneNumber){
       User user = new User(userName,gender,email,password,birthDay,birthMonth,birthYear,phoneNumber);
       userStore.add(user);
    }

    //returns the User of the specified
    public static User getUserById(int userId){
        return userStore.get(userId);
    }

    //returns array of all posts created by the user
    public Post[] getPosts(){
        //creates array of Post objects with the oldest Post being first in array
        Post[] postArr = new Post[posts.size()];
        int cnt=0;
        for(int post : posts){
            postArr[cnt++] = postStore.get(post);
        }
        //reverses the array so that the latest reply is the first element
        for(int i=0; i<postArr.length/2; i++){
            Post tmp = postArr[i];
            postArr[i] = postArr[postArr.length - i - 1];
            postArr[postArr.length - i - 1] = tmp;
        }

        return postArr;

    }
}
