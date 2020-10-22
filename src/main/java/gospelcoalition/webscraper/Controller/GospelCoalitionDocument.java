package gospelcoalition.webscraper.Controller;

import gospelcoalition.webscraper.Model.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private List<Article> articlesList = new ArrayList<>();
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
        //TODO MAKE SURE YOU SELECT ALL ARTICLES
        //TODO update the DOC.select with multiple queries to get more articles
        Elements articles = doc.select(".article_link");

        //Set the size of the articleSet to the size of articles found
        //Using articleSet to ensure there are no duplicates
        Set<Article> articleSet = new HashSet<Article>(articles.size());

        //Loop through all elements named article to create hash and save article to list
        for (Element article : articles) {

            String articleUrl = article.absUrl("href");
            String articleTitle = article.text();
            //If cant get date then use other constructor to add an article without date field
            try {
                //Connect to current article specified with articleUrl

                Document document2 = Jsoup.connect(articleUrl).get();
                Element dateElement = document2.selectFirst("time");
                if (dateElement == null) {
                    articleSet.add(new Article(articleTitle, articleUrl));
                } else {
                    Date date = java.sql.Date.valueOf(LocalDate.parse(dateElement.text(), DateTimeFormatter.ofPattern("MMMM d, uuuu")));
                    articleSet.add(new Article(articleTitle, articleUrl, date));
//                    System.out.println(date.toString() + " " + articleTitle);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //add all from articleSet to articleList to ensure that there are no duplicates\
        articlesList.addAll(articleSet);
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
            addArticles();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        serializeArticlesList();
    }

    @Override
    public int hashCode() {
        return articlesList.hashCode();
    }

    public List<Article> getArticlesList() {
        return articlesList;
    }

}
