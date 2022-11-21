package nya.nekoneko.pixiv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    private Image_urls image_urls;
    private String caption;
    private int restrict;
    private User user;
    private List<Tags> tags;
    private List<String> tools;
    private Date create_date;
    private int page_count;
    private int width;
    private int height;
    private int sanity_level;
    private int x_restrict;
    private String series;
    private Meta_single_page meta_single_page;
    private List<String> meta_pages;
    private int total_view;
    private int total_bookmarks;
    private boolean is_bookmarked;
    private boolean visible;
    private boolean is_muted;
    private int total_comments;
    private int illust_ai_type;
    private int illust_book_style;
    private int comment_access_control;
}