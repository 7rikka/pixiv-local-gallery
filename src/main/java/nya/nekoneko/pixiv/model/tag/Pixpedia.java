package nya.nekoneko.pixiv.model.tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.annotation.ONodeAttr;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pixpedia {
    @ONodeAttr(name = "abstract")
    private String description;
    private String image;
    private String id;
    private String yomigana;
    private List<String> siblingsTags;
}