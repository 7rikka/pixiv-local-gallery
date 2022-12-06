/**
  * Copyright 2022 bejson.com 
  */
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
public class AppMetaPages {
    /**
     *
     */
    @ONodeAttr(name = "image_urls")
    private AppImageUrl imageUrls;
}