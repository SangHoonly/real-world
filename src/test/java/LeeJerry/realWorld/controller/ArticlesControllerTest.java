package LeeJerry.realWorld.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import LeeJerry.realWorld.Service.ArticleService;
import LeeJerry.realWorld.model.dto.ArticleRes;
import LeeJerry.realWorld.model.dto.ProfileRes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ArticlesController.class)
class ArticlesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    private final Map<String, Object> params = new HashMap<>();
    private final List<ArticleRes> articleResList = new ArrayList<>();

    @BeforeEach
    void init() {
        params.clear();
        articleResList.clear();

        params.put("limit", 20);
        params.put("offset", 0);

        ArticleRes garen = getArticle(getTagList("spring"), getAuthor("garen"), true);
        ArticleRes darius = getArticle(getTagList("mysql"), getAuthor("darius"), false);
        ArticleRes teemo = getArticle(getTagList("spring"), getAuthor("teemo"), false);
        ArticleRes nasus = getArticle(getTagList("nodejs"), getAuthor("nasus"), false);

        articleResList.add(garen);
        articleResList.add(darius);
        articleResList.add(teemo);
        articleResList.add(nasus);


        when(articleService.findArticles(params)).thenReturn(articleResList);
        when(articleService.findArticles(null)).thenReturn(articleResList);

    }

    @Test
    @DisplayName("/api/articles 로 GET 요청 시 아티클들을 가져온다")
    void findArticles_default_test() throws Exception {

        // when & then
        mockMvc.perform(
            get("/api/articles"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.articlesCount", is(4)));
    }

    @Test
    @DisplayName("/api/articles 에 쿼리 파리미터로 태그를 넣을 시, 해당 태그가 포함된 아티클을 가져온다.")
    void findArticles_filtered_by_tag_name() throws Exception {
        // given
        params.put("tag", "spring");

        when(articleService.findArticles(params))
            .thenReturn(Arrays.asList(articleResList.get(0), articleResList.get(2)));

        // when & then
        mockMvc.perform(
            get("/api/articles?tag=spring"))
            .andExpect(jsonPath("$.articlesCount", is(2)))
            .andExpect(jsonPath("$.articles[0].tagList[0]").value("spring"));
    }

    @Test
    @DisplayName("/api/articles 에 쿼리 파리미터로 author 를 넣을 시, 해당 태그가 포함된 아티클을 가져온다.")
    void findArticles_filtered_by_author() throws Exception {
        // given
        params.put("author", "garen");

        when(articleService.findArticles(params)).thenReturn(List.of(articleResList.get(0)));

        // when & then
        mockMvc.perform(
                get("/api/articles?author=garen"))
            .andExpect(jsonPath("$.articlesCount", is(1)))
            .andExpect(jsonPath("$.articles[0].author.username").value("garen"));
    }

    @Test
    @DisplayName("/api/articles 에 쿼리 파리미터로 favorited 를 넣을 시, 해당 태그가 포함된 아티클을 가져온다.")
    void findArticles_filtered_by_favorited() throws Exception {
        // given
        params.put("favorited", "garen");

        when(articleService.findArticles(params)).thenReturn(List.of(articleResList.get(0)));

        // when & then
        mockMvc.perform(
                get("/api/articles?favorited=garen"))
            .andExpect(jsonPath("$.articlesCount", is(1)))
            .andExpect(jsonPath("$.articles[0].favorited").value(true));
    }

    @Test
    @DisplayName("/api/articles 에 쿼리 파리미터로 limit 를 넣을 시, limit 만큼 아티클을 가져온다.")
    void findArticles_filtered_by_limit() throws Exception {
        // given
        params.put("limit", 2);

        when(articleService.findArticles(params)).thenReturn(Arrays.asList(articleResList.get(0), articleResList.get(1)));

        // when & then
        mockMvc.perform(
                get("/api/articles?limit=2"))
            .andExpect(jsonPath("$.articlesCount", is(2)));
    }

    @Test
    @DisplayName("/api/articles 에 쿼리 파리미터로 offset 를 넣을 시, offset 에서 limit 만큼 아티클을 가져온다.")
    void findArticles_filtered_by_offset() throws Exception {
        // given
        params.put("limit", 2);
        params.put("offset", 2);

        when(articleService.findArticles(params)).thenReturn(Arrays.asList(articleResList.get(2), articleResList.get(3)));

        // when & then
        mockMvc.perform(
                get("/api/articles?limit=2&offset=1"))
            .andExpect(jsonPath("$.articlesCount", is(2)))
            .andExpect(jsonPath("$.articles[0].author.username").value("teemo"));
    }


    private ArticleRes getArticle(List<String> tagList, ProfileRes author, boolean favorited) {
        return ArticleRes.builder()
            .slug("how-to-train-your-dragon")
            .title("How to train your dragon")
            .description("Ever wonder How?")
            .body("It takes a time")
            .tagList(tagList)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .favorited(favorited).favoritesCount(0).author(author).build();
    }


    private ProfileRes getAuthor(String name) {
        return ProfileRes.builder()
            .username(name).bio("I work at state farm")
            .image("https://i.stack.imgur.com/xHWG8.jpg")
            .following(false)
            .build();
    }

    private List<String> getTagList(String tagName1) {
        List<String> tagList = new ArrayList<>();
        tagList.add(tagName1);
        tagList.add("dragons");
        return tagList;
    }

}
