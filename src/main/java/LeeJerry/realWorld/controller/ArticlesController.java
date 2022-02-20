package LeeJerry.realWorld.controller;


import LeeJerry.realWorld.Service.ArticleService;
import LeeJerry.realWorld.model.dto.ArticlesRes;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {

    final ArticleService articleService;

    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping
    ArticlesRes findArticles(@RequestParam(required = false) Map<String, Object> params) {
        if (!params.containsKey("limit"))
            params.put("limit", 20);

        if (!params.containsKey("offset"))
            params.put("offset", 0);

        return new ArticlesRes(articleService.findArticles(params));
    }
}
