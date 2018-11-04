package LimpBiscuit.Demo.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToOne
    private RequestOperatingSystem operatingSystem;

    private String type;

    private String url;

    @OneToOne
    private RequestBrowser browser;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RequestOperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(RequestOperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestBrowser getBrowser() {
        return browser;
    }

    public void setBrowser(RequestBrowser browser) {
        this.browser = browser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
