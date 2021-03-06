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
    ArticlesRes findArticles(@RequestParam(required = false, defaultValue = "20") int limit,
                            @RequestParam(required = false, defaultValue = "0") int offset,
                            @RequestParam(required = false) Map<String, Object> params) {

        params.put("limit", limit);
        params.put("offset", offset * limit);

        return new ArticlesRes(articleService.findArticles(params));
    }
}
