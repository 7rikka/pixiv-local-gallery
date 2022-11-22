package nya.nekoneko.pixiv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.annotation.ONodeAttr;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Illust {
    private long id;
    private String title;
    private String type;
    @ONodeAttr(name = "image_urls")
    private ImageUrl imageUrls;
    private String caption;
    private int restrict;
    private User user;
    private List<Tags> tags;
    private List<String> tools;
    @ONodeAttr(name = "create_date")
    private LocalDateTime createDate;
    @ONodeAttr(name = "page_count")
    private int pageCount;
    private int width;
    private int height;
    @ONodeAttr(name = "sanity_level")
    private int sanityLevel;
    @ONodeAttr(name = "x_restrict")
    private int xRestrict;
    private String series;
    @ONodeAttr(name = "meta_single_page")
    private MetaSinglePage metaSinglePage;
    @ONodeAttr(name = "meta_pages")
    private List<String> metaPages;
    @ONodeAttr(name = "total_view")
    private int totalView;
    @ONodeAttr(name = "total_bookmarks")
    private int totalBookmarks;
    @ONodeAttr(name = "is_bookmarked")
    private boolean isBookmarked;
    private boolean visible;
    @ONodeAttr(name = "is_muted")
    private boolean isMuted;
    @ONodeAttr(name = "total_comments")
    private int totalComments;
    @ONodeAttr(name = "illust_ai_type")
    private int illustAiType;
    @ONodeAttr(name = "illust_book_style")
    private int illustBookStyle;
    @ONodeAttr(name = "comment_access_control")
    private int commentAccessControl;
}