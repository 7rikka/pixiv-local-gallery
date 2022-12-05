package nya.nekoneko.pixiv.model.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nya.nekoneko.pixiv.model.ImageUrl;
import org.noear.snack.annotation.ONodeAttr;

import java.util.List;

/**
 * @author takan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppIllust {
    /**
     *
     */
    @ONodeAttr(name = "id")
    private Integer id;
    /**
     *
     */
    @ONodeAttr(name = "title")
    private String title;
    /**
     *
     */
    @ONodeAttr(name = "type")
    private String type;
    /**
     *
     */
    @ONodeAttr(name = "image_urls")
    private ImageUrl imageUrls;
    /**
     *
     */
    @ONodeAttr(name = "caption")
    private String caption;
    /**
     *
     */
    @ONodeAttr(name = "restrict")
    private Integer restrict;
    /**
     *
     */
    @ONodeAttr(name = "user")
    private AppUser user;
    /**
     *
     */
    @ONodeAttr(name = "tags")
    private List<AppTag> tags;
    /**
     *
     */
    @ONodeAttr(name = "tools")
    private List<String> tools;
    /**
     *
     */
    @ONodeAttr(name = "create_date")
    private String createDate;
    /**
     *
     */
    @ONodeAttr(name = "page_count")
    private Integer pageCount;
    /**
     *
     */
    @ONodeAttr(name = "width")
    private Integer width;
    /**
     *
     */
    @ONodeAttr(name = "height")
    private Integer height;
    /**
     *
     */
    @ONodeAttr(name = "sanity_level")
    private Integer sanityLevel;
    /**
     *
     */
    @ONodeAttr(name = "x_restrict")
    private Integer xRestrict;
    /**
     *
     */
    @ONodeAttr(name = "series")
    private String series;
    /**
     *
     */
    @ONodeAttr(name = "meta_single_page")
    private AppMetaSinglePage metaSinglePage;
    /**
     *
     */
    @ONodeAttr(name = "meta_pages")
    private List<String> metaPages;
    /**
     *
     */
    @ONodeAttr(name = "total_view")
    private Integer totalView;
    /**
     *
     */
    @ONodeAttr(name = "total_bookmarks")
    private Integer totalBookmarks;
    /**
     *
     */
    @ONodeAttr(name = "is_bookmarked")
    private Boolean isBookmarked;
    /**
     *
     */
    @ONodeAttr(name = "visible")
    private Boolean visible;
    /**
     *
     */
    @ONodeAttr(name = "is_muted")
    private Boolean isMuted;
    /**
     *
     */
    @ONodeAttr(name = "total_comments")
    private Integer totalComments;
    /**
     *
     */
    @ONodeAttr(name = "illust_ai_type")
    private Integer illustAiType;
    /**
     *
     */
    @ONodeAttr(name = "illust_book_style")
    private Integer illustBookStyle;
    /**
     *
     */
    @ONodeAttr(name = "comment_access_control")
    private Integer commentAccessControl;
}