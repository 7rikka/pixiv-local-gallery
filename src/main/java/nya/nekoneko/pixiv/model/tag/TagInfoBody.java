package nya.nekoneko.pixiv.model.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.ONode;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagInfoBody {
    private String tag;
    private String word;
    private Pixpedia pixpedia;
    private Breadcrumbs breadcrumbs;
    private List<String> myFavoriteTags;
    private ONode tagTranslation;
}