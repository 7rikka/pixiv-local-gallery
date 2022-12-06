package nya.nekoneko.pixiv.model.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nya.nekoneko.pixiv.model.ImageUrlDetail;
import org.noear.snack.annotation.ONodeAttr;

/**
 * @author takan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppImageUrl {
    /**
     *
     */
    @ONodeAttr(name = "square_medium")
    private String squareMedium;
    /**
     *
     */
    @ONodeAttr(name = "medium")
    private String medium;
    /**
     *
     */
    @ONodeAttr(name = "large")
    private String large;
}
