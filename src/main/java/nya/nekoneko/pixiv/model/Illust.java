package nya.nekoneko.pixiv.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.annotation.ONodeAttr;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 插画信息
 *
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("illust")
public class Illust {
    /**
     * 插画id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ONodeAttr(name = "id")
    private Integer id;
    /**
     * 插画标题
     */
    @ONodeAttr(name = "title")
    private String title;
    /**
     * 类型
     * 0：illust
     * 1：manga
     * 2：ugoira
     */
    @ONodeAttr(name = "type")
    private Integer type;
    /**
     * 插画介绍
     */
    @ONodeAttr(name = "description")
    private String description;
    /**
     * 图片信息
     */
    @TableField(exist = false)
    @ONodeAttr(name = "urls")
    private List<ImageUrl> urls;
    /**
     * 浏览限制
     * 0：正常
     * 1：限制
     * 2：限制
     */
    @TableField(value = "`restrict`")
    private Integer restrict;
    /**
     * 浏览限制
     * 0：全年龄
     * 1：R-18
     * 2：R-18G
     */
    @ONodeAttr(name = "x_restrict")
    private Integer xRestrict;
    /**
     * 作者id
     */
    @ONodeAttr(ignore = true)
    @TableField(value = "user_id")
    private Integer userId;
    /**
     * 用户信息
     */
    @TableField(exist = false)
    private User user;
    /**
     * 标签列表
     */
    @TableField(exist = false)
    private List<Tag> tags;
    /**
     * 使用工具列表
     */
    @TableField(exist = false)
    private List<String> tools;
    /**
     * 投稿时间
     */
    @ONodeAttr(name = "create_date")
    private LocalDateTime createDate;
    /**
     * 上传时间
     */
    @ONodeAttr(name = "upload_date")
    private LocalDateTime uploadDate;

    /**
     * 图片总数
     */
    @ONodeAttr(name = "page_count")
    private Integer pageCount;
    /**
     * 第一张图的宽度
     */
    private Integer width;
    /**
     * 第一张图的高度
     */
    private Integer height;
    /**
     * SAN值等级
     */
    @ONodeAttr(name = "sanity_level")
    private Integer sanityLevel;
    /**
     * 系列id
     */
    @ONodeAttr(name = "series_id")
    private Integer seriesId;
    /**
     * 系列
     */
    @TableField(exist = false)
    private Series series;
    /**
     * 总浏览数
     */
    @ONodeAttr(name = "view_count")
    private Integer viewCount;
    /**
     * 总收藏数
     */
    @ONodeAttr(name = "bookmark_count")
    private Integer bookmarkCount;
    /**
     * 是否可见
     * 0：不可见
     * 1：可见（正常）
     */
    @ONodeAttr(name = "visible")
    private Boolean visible;
    /**
     * 总评论数
     */
    @ONodeAttr(name = "comment_count")
    private Integer commentCount;
    /**
     * 总点赞数
     */
    @ONodeAttr(name = "like_count")
    private Integer likeCount;
    /**
     * 是否为AI生成作品
     * 0：否
     * 1：是
     */
    @ONodeAttr(name = "ai_type")
    private Integer aiType;
    /**
     * 0：
     * 1：
     */
    @ONodeAttr(name = "illust_book_style")
    private Integer illustBookStyle;
    /**
     * 作者是否关闭了评论区
     * 0：正常
     * 1：作者关闭评论区
     */
    @ONodeAttr(name = "comment_off")
    private Integer commentOff;
    /**
     * 当前记录新增时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ONodeAttr(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 当前记录更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ONodeAttr(name = "update_time")
    private LocalDateTime updateTime;
    /**
     * App插画总体状态
     * 0：正常
     * -1：无效
     * -2: 同作者关联
     * -3：失效
     */
    @ONodeAttr(name = "app_state")
    private Integer appState;
    /**
     * Web插画总体状态
     * 0：正常
     * -1：无效
     * -2: 同作者关联
     * -3：失效
     */
    @ONodeAttr(name = "web_state")
    private Integer webState;
    /**
     * App Api 返回 Json
     */
    @ONodeAttr(name = "app_raw")
    private String appRaw;

    /**
     * Web Api 返回 Json
     */
    @ONodeAttr(name = "web_raw")
    private String webRaw;
}