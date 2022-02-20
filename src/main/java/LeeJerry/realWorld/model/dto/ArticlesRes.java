package LeeJerry.realWorld.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesRes {

    private List<ArticleRes> articles;
    private int articlesCount;

    public ArticlesRes(List<ArticleRes> articles) {
        this.articles = articles;
        this.articlesCount = articles.size();
    }
}
