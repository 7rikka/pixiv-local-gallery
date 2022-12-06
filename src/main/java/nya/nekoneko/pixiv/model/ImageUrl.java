package nya.nekoneko.pixiv.model;

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
public class ImageUrl {
    /**
     *
     */
    @ONodeAttr(name = "mini")
    private ImageUrlDetail mini;
    /**
     *
     */
    @ONodeAttr(name = "thumb")
    private ImageUrlDetail thumb;
    /**
     *
     */
    @ONodeAttr(name = "small")
    private ImageUrlDetail small;
    /**
     *
     */
    @ONodeAttr(name = "regular")
    private ImageUrlDetail regular;
    /**
     *
     */
    @ONodeAttr(name = "square_medium")
    private ImageUrlDetail squareMedium;
    /**
     *
     */
    @ONodeAttr(name = "medium")
    private ImageUrlDetail medium;
    /**
     *
     */
    @ONodeAttr(name = "large")
    private ImageUrlDetail large;
    /**
     *
     */
    @ONodeAttr(name = "original")
    private ImageUrlDetail original;
}
