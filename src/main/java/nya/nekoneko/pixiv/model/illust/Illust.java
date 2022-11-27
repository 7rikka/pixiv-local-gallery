package nya.nekoneko.pixiv.model.illust;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    private String title;
    private String type;
    @TableField(exist = false)
    @ONodeAttr(name = "image_urls")
    private ImageUrl imageUrls;
    private String caption;
    @TableField(value = "`restrict`")
    private Integer restrict;
    @TableField(value = "user_id")
    private Integer userId;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private List<Tag> tags;
    @TableField(exist = false)
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
    @TableField(exist = false)
    private Series series;
    @TableField(exist = false)
    @ONodeAttr(name = "meta_single_page")
    private MetaSinglePage metaSinglePage;
    @TableField(exist = false)
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
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ONodeAttr(name = "create_time")
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ONodeAttr(name = "update_time")
    private LocalDateTime updateTime;
    private int state;
    private String raw;
}