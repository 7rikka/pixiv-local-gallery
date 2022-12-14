package nya.nekoneko.pixiv.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSubBody {
    private Integer id;
    private String title;
    private Integer illustType;
    private Integer xRestrict;
    private Integer restrict;
    private Integer sl;
    private String url;
    private String description;
    private List<String> tags;
    private Integer userId;
    private String userName;
    private Integer width;
    private Integer height;
    private Integer pageCount;
    private Boolean isBookmarkable;
    private String bookmarkData;
    private String alt;
    //    private TitleCaptionTranslation titleCaptionTranslation;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Boolean isUnlisted;
    private Boolean isMasked;
    private String profileImageUrl;
    private Integer aiType;
}