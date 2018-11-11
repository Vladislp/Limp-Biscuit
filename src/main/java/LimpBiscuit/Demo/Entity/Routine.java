package LimpBiscuit.Demo.Entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private String email;
    @NotNull
    private String title;
    @NotNull
    private String text;
    @NotNull
    private String color;
    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean done;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    private User user;

    public Routine(String email, String title, String text, String color, Date date) {
        this.email = email;
        this.title = title;
        this.text = text;
        this.done = false;
        this.color = color;
//        this.user = user;
        this.date = date;
    }

    public Routine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Routine{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", color='" + color + '\'' +
                ", done=" + done +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
