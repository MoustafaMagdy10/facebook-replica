package publications;


//this class has only the function addUser. Its purpose is to allow the frontend to add users
//without manually accessing the arrays of data because this will not be possible outside the package
//when you want to add a user in the main class, create an instance of this class and use the method to add users
public class UserManager {


    public void addUser(String userName, String gender, String email, String password, int birthDay, int birthMonth, int birthYear, String phoneNumber){
       User user = new User( userName,  gender,  email,  password,  birthDay,  birthMonth,  birthYear,  phoneNumber);
       PublicationData.userStore.add(user);
    }
}
