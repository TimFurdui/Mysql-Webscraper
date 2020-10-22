package gospelcoalition.webscraper.Controller.JPA;

import gospelcoalition.webscraper.Model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Timothy Furdui
 * Date: 1/30/2020
 * Time: 2:30 PM
 */

@Component
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    //Check every article in list against the database to ensure its not duplicated.
    public void addToDbIfNotExist(List<Article> articleList) {
        for (Article article : articleList) {
            if (!articleRepository.existsArticleByTitle(article.getTitle())) {
                articleRepository.save(article);
            }
        }
    }


    public void add(Article article){
        articleRepository.save(article);
    }

}
