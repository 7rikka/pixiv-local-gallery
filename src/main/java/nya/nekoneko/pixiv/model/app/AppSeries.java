package nya.nekoneko.pixiv.model.app;

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
public class AppSeries {
    /**
     *
     */
    @ONodeAttr(name = "id")
    private Integer id;
    /**
     *
     */
    @ONodeAttr(name = "title")
    private String title;
}