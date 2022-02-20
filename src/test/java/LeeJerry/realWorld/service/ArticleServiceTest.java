package LeeJerry.realWorld.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import LeeJerry.realWorld.Service.ArticleService;
import LeeJerry.realWorld.model.dto.ArticleRes;
import LeeJerry.realWorld.model.mapper.ArticleMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    ArticleService articleService;

    @Mock
    ArticleMapper articleMapper;


    @Test
    @DisplayName("findArticles default 테스트")
    void findArticles_default_test() throws Exception {
        // given
        Long userId = null;

        List<ArticleRes> articleResList = new ArrayList<>();

        articleResList.add(new ArticleRes());
        articleResList.add(new ArticleRes());
        articleResList.add(new ArticleRes());

        when(articleMapper.findArticles(null)).thenReturn(articleResList);

        // when
        List<ArticleRes> result = articleService.findArticles(null);

        // then
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("findArticles 필터 테스트")
    void findArticles_with_tags() throws Exception {
        // given
        Long userId = null;
        String tagName = "spring";


        List<ArticleRes> articleResTotalList = new ArrayList<>();
        List<ArticleRes> articleResWithTagList = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();

        articleResTotalList.add(new ArticleRes());
        articleResTotalList.add(new ArticleRes());

        articleResWithTagList.add(new ArticleRes());

        params.put("tag", "tag1");

        when(articleMapper.findArticles(null)).thenReturn(articleResTotalList);
        when(articleMapper.findArticles(params)).thenReturn(articleResWithTagList);
        // when
        List<ArticleRes> totalResult = articleService.findArticles(null);
        List<ArticleRes> taggedResult = articleService.findArticles(params);
        // then
        assertThat(totalResult.size()).isEqualTo(2);
        assertThat(taggedResult.size()).isEqualTo(1);

    }
}