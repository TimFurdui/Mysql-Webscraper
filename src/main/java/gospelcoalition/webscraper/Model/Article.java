package gospelcoalition.webscraper.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Comparator;

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
    private Date date;

    protected Article() {
    }

    public Article(String articleTitle, String articleUrl, Date date) {
        this.title = articleTitle;
        this.url = articleUrl;
        this.date = date;
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

//    public static Comparator<Article> ArticleTitleComparator = new Comparator<Article>() {
//
//        @Override
//        public int compare(Article o1, Article o2) {
//            String articleOneTitle = o1.title.toUpperCase();
//            String articleTwoTitle = o2.title.toUpperCase();
//
//            return articleOneTitle.compareTo(articleTwoTitle);
//        }
//    };

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Article article = (Article) obj;
        return (article.title.equals(this.title) && article.url.equals(this.url));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " Title: " + title + " Url: " + url;
    }
}
