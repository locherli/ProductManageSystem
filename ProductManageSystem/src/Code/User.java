package Code;

public class User {
    private String userName;
    private int userID;
    private String email;
    private long hashcode_password;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + userID;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (int) (hashcode_password ^ (hashcode_password >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (userID != other.userID)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (hashcode_password != other.hashcode_password)
            return false;
        return true;
    }

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
