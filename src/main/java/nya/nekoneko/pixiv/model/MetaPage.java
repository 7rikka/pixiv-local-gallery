package nya.nekoneko.pixiv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.snack.annotation.ONodeAttr;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaPage {
    @ONodeAttr(name = "image_urls")
    private ImageUrl imageUrls;
}
