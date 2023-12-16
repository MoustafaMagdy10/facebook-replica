package publications;
import java.io.*;
import java.time.Period;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class LoginRegisterClass {
    private static User [] users = User.exportUsers();
    private  String username;
    private  String gender;
    private  String email;
    private  String password;
    private int birth_day;
    private int birth_month;
    private int birth_year;

    private String phone_number;
    public static  User x;
    Scanner in =new Scanner(System.in);
    public void register() throws IOException {
        System.out.println("REGISTRATION\n-Please enter username: ");
        username = in.nextLine();
        while (true) {
            boolean ckF=checkfound(username);
            if (username.isEmpty())
            {
                System.out.println("the input is empty");
                System.out.println("Please enter username: ");
                username = in.nextLine();
                continue;
            } else if (username.length() < 3) {
                System.out.println("the username must be more than 3 characters");
                System.out.println("Please enter username: ");
                username = in.nextLine();
                continue;
            } else if (ckF) {
                System.out.println(" the username you enterd is arelady registerd ");
                System.out.println("Please enter another username ");
                username = in.nextLine();
                continue;
            }
            boolean x=false;
            for (char ch : username.toCharArray()) {
                if (Character.isDigit(ch)) {
                    x=false;
                } else {
                    x=true;
                }
            }
            if (x==false)
            {
                System.out.println("the username cant be all digits");
                System.out.println("Please enter username: ");
                username = in.nextLine();
                continue;
            } else if (x==true) {
                break;
            }
            break;
        }
        System.out.println("-Please enter your email: ");
        email = in.nextLine();
        while (true) {
            if (!email.contains("@")) {
                System.out.println(" the email must contains @");
                System.out.println("-Please enter your email: ");
                email = in.next();
            } else if (email.isEmpty()) {
                System.out.println(" the email can't be empty");
                System.out.println("-Please enter your email: ");
                email = in.next();
            } else if (email.startsWith("@")) {
                System.out.println(" the email must begin with the  username");
                System.out.println("-Please enter your email: ");
                email = in.next();
            }// try to find another way
            else {
                break;
            }
        }
        System.out.println("-Please enter password: ");
        password = in.nextLine();
        while (true) {
            if (password.isEmpty()) {
                System.out.println("the password mustn't be empty");
                System.out.println("-Please enter password: ");
                password = in.next();
            } else if (password.length() < 5) {
                System.out.println("the password must be more than 5 characters");
                System.out.println("-Please enter password: ");
                password = in.next();
            }
            else {
                break;
            }
        }
        System.out.println("-Please enter phone number: ");
        phone_number=in.nextLine();
        while(true)
        {
            if (phone_number.isEmpty())
            {
                System.out.println("the phone number cant be empty");
                System.out.println("-Please enter phone number: ");
                phone_number = in.nextLine();
            } else if (phone_number.length()!=11) {
                System.out.println("the phone must be 11 digits");
                System.out.println("-Please enter phone number: ");
                phone_number = in.nextLine();
            } else if (!phone_number.startsWith("0")) {
                System.out.println("the phone number in egypt must start with 0");
                System.out.println("-Please enter phone number: ");
                phone_number = in.nextLine();
            }else if((phone_number.charAt(1)!=1)&&(phone_number.charAt(2)==0||phone_number.charAt(2)==1||phone_number.charAt(2)==2||phone_number.charAt(2)==5)){
                System.out.println("the phone number providers in egypt either start with 010 or 011 or 012 or 015");
                System.out.println("-Please enter phone number: ");
                phone_number = in.nextLine();
            } else {break;}
        }
        System.out.println("-Please enter your gender: ");
        gender = in.nextLine();
        while (true){
            gender=gender.toLowerCase();
            if (gender.equals("male")||gender.equals("female"))
            {
                break;
            }
            else {
                System.out.println("the gender you entered is invaled");
                System.out.println("-Please enter your gender: ");
                gender = in.nextLine();
                continue;
            }
        }
        System.out.println(" enter your birth day")  ;
        birth_day=in.nextInt();
        System.out.println(" enter your birth month");
        birth_month=in.nextInt();
        System.out.println( "enter your birth year ");
        birth_year=in.nextInt();
        System.out.println("Register successful!");
        x =new User(username,gender,email,password,birth_day,birth_month,birth_year,phone_number);
        userinterface.start(x);
    }
    public static boolean checkfound(String name)
    {
        for (User x:users)
        {
            if (x.getUserName().toLowerCase().equals(name.toLowerCase()))
            {
                return true;
            }
        }
        return false;
    }
    public void Login ()  {
        while (true) {
            boolean validation = false;
            System.out.println("LOGIN\n-Please enter your username: ");
            String checkusername = in.nextLine();
            System.out.println("Password: ");
            String checkPassword = in.nextLine();
            for (User log: users)
            {
                if(log.getPassword().equals(checkPassword)&&log.getUserName().equals(checkusername))
                {
                    validation = true;
                    x=log;
                    break;
                }
            }
            if (validation) {
                System.out.println("Login successful!");
                userinterface.start(x);
                break;
            } else {
                System.out.println("Username or password are invalid\nPlease try again\n\n");
            }
        }
    }
}