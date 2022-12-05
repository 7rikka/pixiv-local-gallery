package nya.nekoneko.pixiv.model.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nya.nekoneko.pixiv.model.ImageUrl;
import org.noear.snack.annotation.ONodeAttr;

import java.util.List;

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
    private ImageUrl image_urls;
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
    private String create_date;
    /**
     *
     */
    @ONodeAttr(name = "page_count")
    private Integer page_count;
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
    private Integer sanity_level;
    /**
     *
     */
    @ONodeAttr(name = "x_restrict")
    private Integer x_restrict;
    /**
     *
     */
    @ONodeAttr(name = "series")
    private String series;
    /**
     *
     */
    @ONodeAttr(name = "meta_single_page")
    private AppMetaSinglePage meta_single_page;
    /**
     *
     */
    @ONodeAttr(name = "meta_pages")
    private List<String> meta_pages;
    /**
     *
     */
    @ONodeAttr(name = "total_view")
    private Integer total_view;
    /**
     *
     */
    @ONodeAttr(name = "total_bookmarks")
    private Integer total_bookmarks;
    /**
     *
     */
    @ONodeAttr(name = "is_bookmarked")
    private Boolean is_bookmarked;
    /**
     *
     */
    @ONodeAttr(name = "visible")
    private Boolean visible;
    /**
     *
     */
    @ONodeAttr(name = "is_muted")
    private Boolean is_muted;
    /**
     *
     */
    @ONodeAttr(name = "total_comments")
    private Integer total_comments;
    /**
     *
     */
    @ONodeAttr(name = "illust_ai_type")
    private Integer illust_ai_type;
    /**
     *
     */
    @ONodeAttr(name = "illust_book_style")
    private Integer illust_book_style;
    /**
     *
     */
    @ONodeAttr(name = "comment_access_control")
    private Integer comment_access_control;
}