package nya.nekoneko.pixiv.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebTags {
    private String authorId;
    private boolean isLocked;
    private List<WebTag> tags;
    private boolean writable;
}