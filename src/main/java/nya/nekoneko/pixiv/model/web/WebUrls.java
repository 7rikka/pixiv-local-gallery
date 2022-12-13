package nya.nekoneko.pixiv.model.web;

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
public class WebUrls {
    private String mini;
    private String thumb;
    @ONodeAttr(name = "thumb_mini")
    private String thumbMini;
    private String small;
    private String regular;
    private String original;
}