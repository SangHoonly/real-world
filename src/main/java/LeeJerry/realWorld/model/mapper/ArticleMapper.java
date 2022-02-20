package LeeJerry.realWorld.model.mapper;

import LeeJerry.realWorld.model.dto.ArticleRes;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    public List<ArticleRes> findArticles(Map<String, Object> params);
}
