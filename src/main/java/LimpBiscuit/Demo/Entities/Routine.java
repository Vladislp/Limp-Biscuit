package LimpBiscuit.Demo.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String text;
//    @NotNull
    private String color;
//    @NotNull
    private boolean done;
//    @NotNull
    private Date date;
//    @NotNull
    private Date doneDate;
    @NotNull
    @ManyToOne
    private User user;

    public Routine(String title, String text, String color, Date date, Date doneDate) {
        this.title = title;
        this.text = text;
        this.color = color;
        this.done = false;
        this.date = date;
        this.doneDate = doneDate;
    }

    public Routine(String title, String text) {
        this.title = title;
        this.text = text;
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
