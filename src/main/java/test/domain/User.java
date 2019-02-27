package test.domain;

/**
 * User: krisjin
 * Date: 2016/10/27
 */
public class User {
    private String username;
    private String nickname;
    private String email;

    public User(String username, String nickname, String email) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int hashCode() {
        return this.getUsername().hashCode() + this.getNickname().hashCode() + this.getEmail().hashCode();
    }
}
