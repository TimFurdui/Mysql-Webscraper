package gospelcoalition.webscraper.Controller.JPA;

import gospelcoalition.webscraper.Model.Article;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by IntelliJ IDEA.
 * User: Peter Timothy Furdui
 * Date: 1/30/2020
 * Time: 2:02 PM
 */
public interface ArticleRepository extends CrudRepository<Article, Integer> {

    boolean existsArticleByTitle(String title);

}
