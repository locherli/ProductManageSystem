package Code;

public class User {
    private String userName;
    private int userID;
    private String email;
    private long hashcode_password;

    public User() {
    }

    public User(String userName, int userID, String email, long hashcode_password) {
        this.userName = userName;
        this.userID = userID;
        this.email = email;
        this.hashcode_password = hashcode_password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getHashcode_password() {
        return hashcode_password;
    }

    public void setHashcode_password(long hashcode_password) {
        this.hashcode_password = hashcode_password;
    }

}
