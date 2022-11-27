package nya.nekoneko.pixiv.model.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagTranslationItem {
    private String en;
    private String ko;
    private String zh;
    private String zh_tw;
    private String romaji;
}