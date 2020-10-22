package gospelcoalition.webscraper;

import gospelcoalition.webscraper.Controller.GospelCoalitionDocument;
import gospelcoalition.webscraper.Controller.JPA.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WebscraperApplicationTests {

    GospelCoalitionDocument gospelCoalitionDocument = new GospelCoalitionDocument();

    @Test
    void contextLoads() {
    }

    @Autowired
    ArticleService articleService;

    @Test
    public void ifConnectionEstablished_ThenVerifyArticlesNotNull(){
        gospelCoalitionDocument.initializeGospelCoalitionDocument();
        assertThat(gospelCoalitionDocument.getArticlesList()).isNotNull();
    }
}
