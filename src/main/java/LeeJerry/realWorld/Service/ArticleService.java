package LeeJerry.realWorld.Service;

import LeeJerry.realWorld.model.dto.ArticleRes;
import LeeJerry.realWorld.model.mapper.ArticleMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public List<ArticleRes> findArticles(Map<String, Object> params) {
        return articleMapper.findArticles(params);
    }
}
