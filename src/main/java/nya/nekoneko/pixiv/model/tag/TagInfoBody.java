package nya.nekoneko.pixiv.model.tag;

import org.noear.snack.ONode;

import java.util.List;

public class TagInfoBody {
    private String tag;
    private String word;
    private Pixpedia pixpedia;
    private Breadcrumbs breadcrumbs;
    private List<String> myFavoriteTags;
    private ONode tagTranslation;
}