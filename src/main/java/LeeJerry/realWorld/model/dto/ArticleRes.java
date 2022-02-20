package LeeJerry.realWorld.model.dto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRes {

    private String slug;
    private String title;
    private String description;
    private String body;
    private Boolean favorited;
    private Integer favoritesCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<String> tagList;
    private ProfileRes author;
}
