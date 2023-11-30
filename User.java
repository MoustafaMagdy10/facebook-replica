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

    public User(String userName, String gender, String email, String password, int birthDay, int birthMonth, int birthYear, String phoneNumber) {
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userId = ++userIdGenerator;
    }

    //getters
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
}
