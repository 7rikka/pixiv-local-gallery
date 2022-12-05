package nya.nekoneko.pixiv.model.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.annotation.ONodeAttr;

/**
 * @author takan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    /**
     *
     */
    @ONodeAttr(name = "id")
    private Integer id;
    /**
     *
     */
    @ONodeAttr(name = "name")
    private String name;
    /**
     *
     */
    @ONodeAttr(name = "account")
    private String account;
    /**
     *
     */
    @ONodeAttr(name = "profile_image_urls")
    private AppProfileImageUrls profileImageUrls;
    /**
     *
     */
    @ONodeAttr(name = "is_followed")
    private Boolean isFollowed;
}