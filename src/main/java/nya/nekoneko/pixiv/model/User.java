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
public class User {
    private Integer id;
    private String name;
    private String account;
    @ONodeAttr(name = "profile_image_urls")
    private ProfileImageUrl profileImageUrls;
    @ONodeAttr(name = "is_followed")
    private Boolean isFollowed;
}