package nya.nekoneko.pixiv.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebAjaxUrl {
    /**
     *
     */
    private WebUrls urls;
    /**
     * 原图宽度
     */
    private int width;
    /**
     * 原图高度
     */
    private int height;
}
