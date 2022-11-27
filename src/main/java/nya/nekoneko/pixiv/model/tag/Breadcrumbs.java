package nya.nekoneko.pixiv.model.tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Breadcrumbs {
    private List<String> successor;
    private List<String> current;
}