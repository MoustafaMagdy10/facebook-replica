package publications;

import publications.Post;


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

    static ArrayList<User> userStore = new ArrayList<User>();
    private static ArrayList<Post> postStore = new ArrayList<Post>();
    private TreeSet<Integer> posts = new TreeSet<>();

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

    //functionality

    public void addPost(String content, boolean isPublic){
        Post post = new Post(userId ,content, isPublic);
        posts.add(post.getId());
        postStore.add(post);
    }

    public static User[] getUsers(){
        User[] userArr = new User[userStore.size()];
        int cnt = 0;
        for(User user : userStore){
            userArr[cnt++] = user;
        }
        return userArr;
    }

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
