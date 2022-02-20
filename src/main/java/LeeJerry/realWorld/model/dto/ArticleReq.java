package LeeJerry.realWorld.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleReq {

    private Long id;
    private Long authorId;
    private String slug;
    private String title;
    private String description;
    private String body;
}
