package models;

public class Session {
    public int _id;
    public String hashed_session;
    public String salt;
    public int user_id;

    public Session() {}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getHashed_session() {
        return hashed_session;
    }

    public void setHashed_session(String hashed_session) {
        this.hashed_session = hashed_session;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}