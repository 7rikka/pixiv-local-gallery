package nya.nekoneko.pixiv.model.illust;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.annotation.ONodeAttr;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaSinglePage {
    @ONodeAttr(name = "original_image_url")
    private String originalImageUrl;
}