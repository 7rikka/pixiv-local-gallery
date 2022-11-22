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
    private Integer id;
    private String title;
    private String type;
    @ONodeAttr(name = "image_urls")
    private ImageUrl imageUrls;
    private String caption;
    private Integer restrict;
    private User user;
    private List<Tags> tags;
    private List<String> tools;
    @ONodeAttr(name = "create_date")
    private LocalDateTime createDate;
    @ONodeAttr(name = "page_count")
    private Integer pageCount;
    private Integer width;
    private Integer height;
    @ONodeAttr(name = "sanity_level")
    private Integer sanityLevel;
    @ONodeAttr(name = "x_restrict")
    private Integer xRestrict;
    private Series series;
    @ONodeAttr(name = "meta_single_page")
    private MetaSinglePage metaSinglePage;
    @ONodeAttr(name = "meta_pages")
    private List<MetaPage> metaPages;
    @ONodeAttr(name = "total_view")
    private Integer totalView;
    @ONodeAttr(name = "total_bookmarks")
    private Integer totalBookmarks;
    @ONodeAttr(name = "is_bookmarked")
    private Boolean isBookmarked;
    private Boolean visible;
    @ONodeAttr(name = "is_muted")
    private Boolean isMuted;
    @ONodeAttr(name = "total_comments")
    private Integer totalComments;
    @ONodeAttr(name = "illust_ai_type")
    private Integer illustAiType;
    @ONodeAttr(name = "illust_book_style")
    private Integer illustBookStyle;
    @ONodeAttr(name = "comment_access_control")
    private Integer commentAccessControl;
}