package nya.nekoneko.pixiv.model.tag;
import org.noear.snack.annotation.ONodeAttr;

import java.util.List;

public class Pixpedia {
    @ONodeAttr(name = "abstract")
    private String description;
    private String image;
    private String id;
    private String yomigana;
    private List<String> siblingsTags;
}