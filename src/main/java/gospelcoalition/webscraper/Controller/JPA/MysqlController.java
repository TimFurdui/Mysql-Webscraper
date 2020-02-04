package gospelcoalition.webscraper.Controller.JPA;

import gospelcoalition.webscraper.Controller.GospelCoalitionDocument;
import gospelcoalition.webscraper.Model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewArticle(@RequestParam String title, @RequestParam String url) {
        Article testArticle = new Article(title, url);
        articleService.add(testArticle);
        return "SAVED";
    }

    @PostMapping(path = "/AddAllArticles")
    public @ResponseBody
    String addAllArticles() {
        GospelCoalitionDocument gospelCoalitionDocument = new GospelCoalitionDocument();
        gospelCoalitionDocument.initializeGospelCoalitionDocument();
        articleService.addToDbIfNotExist(gospelCoalitionDocument.getArticlesList());
        return "Saved Articles.";
    }

}
