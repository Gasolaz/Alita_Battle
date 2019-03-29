package models;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Message {

    private long id;
    private long char_id;
    private Date msg_time;
    private String msg_text;

    public Message() {}

    public Message(long id, long char_id, Date msg_time, String msg_text) {
        this.id = id;
        this.char_id = char_id;
        this.msg_text = msg_text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChar_id() {
        return char_id;
    }

    public void setChar_id(long char_id) {
        this.char_id = char_id;
    }

    public String getMsg_text() {
        return msg_text;
    }

    public void setMsg_text(String msg_text) {
        this.msg_text = msg_text;
    }
}
