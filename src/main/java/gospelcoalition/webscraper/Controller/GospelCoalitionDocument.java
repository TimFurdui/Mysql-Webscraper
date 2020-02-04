package gospelcoalition.webscraper.Controller;

import gospelcoalition.webscraper.Model.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Timothy Furdui
 * Date: 1/30/2020
 * Time: 1:08 PM
 */
public class GospelCoalitionDocument {
    private static GospelCoalitionDocument gospelCoalitionDocument = null;
    private static Document doc;
    private Set<Article> articlesList;
    private final String PATH = System.getProperty("user.home") + "\\Desktop";
    private final String fileName = "TemporaryName.txt";

    public static GospelCoalitionDocument getInstance() {
        if (gospelCoalitionDocument == null) {
            return new GospelCoalitionDocument();
        }
        return gospelCoalitionDocument;
    }

    private void setGospelCoalitionDocument() throws IOException {
        doc = Jsoup.connect("https://www.thegospelcoalition.org/").get();
    }

    private void addArticles() {
        Elements articles = doc.select(".article_title a");

        //Set the size of the ArrayList to the size of articles found
        articlesList = new HashSet<>(articles.size());
        //Loop through all articles in List
        for (Element article : articles) {
            String articleTitle = article.text();
            String url = article.absUrl("href");

            //Add Article to articleList
            articlesList.add(new Article(articleTitle, url));
        }
    }

    //If you want to serialize the articles to desktop then uncomment this method
//    private void serializeArticlesList() {
//        try {
//            FileOutputStream file = new FileOutputStream(PATH + "\\" + fileName);
//            ObjectOutputStream out = new ObjectOutputStream(file);
//
//            //Serialize object
//            out.writeObject(articlesList);
//
//            //Close streams
//            out.close();
//            file.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void initializeGospelCoalitionDocument() {
        try {
            setGospelCoalitionDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addArticles();
//        serializeArticlesList();
    }

    public Set<Article> getArticlesList() {
        return articlesList;
    }

}
