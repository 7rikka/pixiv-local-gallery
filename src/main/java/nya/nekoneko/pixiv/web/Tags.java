package nya.nekoneko.pixiv.web;

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
public class Tags {
    private String authorId;
    private boolean isLocked;
    private List<Tags> tags;
    private boolean writable;
}