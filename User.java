public class User {
    private  String username;
    private  String gender;
    private  String email;
    private  String password;
    private  String birth_date;
    private int birth_day;
    private int birth_month;
    private int birth_year;
    private String phone_number;

    public User(String username, String gender, String email, String password, int birth_day, int birth_month, int birth_year, String phone_number) {
        this.username = username;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.birth_date = birth_day + "/" + birth_month + "/" + birth_year;
        this.phone_number = phone_number;
    }

    public String getUsername() {
        return username;
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

    public String getBirth_date() {
        return birth_date;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
