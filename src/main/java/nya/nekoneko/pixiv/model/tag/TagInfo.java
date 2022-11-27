package nya.nekoneko.pixiv.model.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagInfo {
    private Boolean error;
    private TagInfoBody body;
}