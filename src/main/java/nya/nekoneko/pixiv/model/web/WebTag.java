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
public class WebTag {
    private String tag;
    private boolean locked;
    private boolean deletable;
    private String userId;
    private String userName;
}
