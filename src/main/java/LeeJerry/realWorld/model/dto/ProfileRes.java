package LeeJerry.realWorld.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRes {
    private String username;
    private String bio;
    private String image;
    private Boolean following;
}
