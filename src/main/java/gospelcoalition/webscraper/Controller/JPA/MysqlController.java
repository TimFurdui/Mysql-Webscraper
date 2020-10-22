package gospelcoalition.webscraper.Controller.JPA;

import gospelcoalition.webscraper.Controller.GospelCoalitionDocument;
import gospelcoalition.webscraper.Model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Timothy Furdui
 * Date: 1/31/2020
 * Time: 11:07 AM
 */

@Controller
@RequestMapping(path = "/demo")
public class MysqlController {

    @Autowired
    private ArticleService articleService;
//    @Autowired
//    private ArticlesHashService articlesHashService;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewArticle(@RequestParam String title, @RequestParam String url) {
        Article testArticle = new Article(title, url);
        articleService.add(testArticle);
        return "SAVED";
    }

    @PostMapping(path = "/addAll")
    public @ResponseBody
    String addAllArticles() {
        GospelCoalitionDocument gospelCoalitionDocument = new GospelCoalitionDocument();
        gospelCoalitionDocument.initializeGospelCoalitionDocument();
        //TODO store .hashCodeOfArticles in DB
//        if (articlesHashService.isHashSame(gospelCoalitionDocument.getHashCodeOfArticles())) {
//            return "No new Articles";
//        }
//        articlesHashService.addHashToDb(gospelCoalitionDocument.getHashCodeOfArticles());
        articleService.addToDbIfNotExist(gospelCoalitionDocument.getArticlesList());
        return "Saved Articles.";
    }
}
