package nya.nekoneko.pixiv.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.ONode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebBody {
    private Integer illustId;
    private String illustTitle;
    private String illustComment;
    private Integer id;
    private String title;
    private String description;
    private Integer illustType;
    private LocalDateTime createDate;
    private LocalDateTime uploadDate;
    private Integer restrict;
    private Integer xRestrict;
    private Integer sl;
    private WebUrls urls;
    private WebTags tags;
    private String alt;
    private List<String> storableTags;
    private Integer userId;
    private String userName;
    private String userAccount;
    private ONode userIllusts;
    private Boolean likeData;
    private Integer width;
    private Integer height;
    private Integer pageCount;
    private Integer bookmarkCount;
    private Integer likeCount;
    private Integer commentCount;
    private Integer responseCount;
    private Integer viewCount;
    private String bookStyle;
    private Boolean isHowto;
    private Boolean isOriginal;
    private List<String> imageResponseOutData;
    private List<String> imageResponseData;
    private Integer imageResponseCount;
    private String pollData;
    private WebSeriesNavData seriesNavData;
    private String descriptionBoothId;
    private String descriptionYoutubeId;
    private String comicPromotion;
    private String fanboxPromotion;
    private List<String> contestBanners;
    private Boolean isBookmarkable;
    private String bookmarkData;
    private String contestData;
    //    private ZoneConfig zoneConfig;
//    private ExtraData extraData;
//    private TitleCaptionTranslation titleCaptionTranslation;
    private Boolean isUnlisted;
    private String request;
    private Integer commentOff;
    private Integer aiType;
//    private NoLoginData noLoginData;
}