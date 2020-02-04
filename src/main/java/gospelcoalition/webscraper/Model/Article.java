package gospelcoalition.webscraper.Model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Timothy Furdui
 * Date: 1/29/2020
 * Time: 2:00 PM
 */

@Entity(name = "articles")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String url;

    protected Article() {
    }

    public Article(String articleTitle, String articleUrl) {
        this.title = articleTitle;
        this.url = articleUrl;
    }


    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }



    @Override
    public String toString() {
        return "ID: " + this.id + " Title: " + title + " Url: " + url;
    }
}
