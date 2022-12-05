package nya.nekoneko.pixiv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageUrl {
    private ImageUrlDetail mini;
    private ImageUrlDetail thumb;
    private ImageUrlDetail small;
    private ImageUrlDetail regular;
    private ImageUrlDetail square_medium;
    private ImageUrlDetail medium;
    private ImageUrlDetail large;
    private ImageUrlDetail original;
}
