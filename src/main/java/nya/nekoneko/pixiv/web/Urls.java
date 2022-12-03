package nya.nekoneko.pixiv.web;

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
public class Urls {
    private String mini;
    private String thumb;
    private String small;
    private String regular;
    private String original;
}