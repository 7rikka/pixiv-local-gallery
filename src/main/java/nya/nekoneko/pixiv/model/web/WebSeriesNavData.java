package nya.nekoneko.pixiv.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSeriesNavData {
    private String seriesType;
    private Integer seriesId;
    private String title;
    private Integer order;
    private Boolean isWatched;
    private Boolean isNotifying;
//    private Prev prev;
//    private Next next;
}