package nya.nekoneko.pixiv.model.illust;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("image_url")
public class ImageUrl {
    /**
     * 插画id
     */
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    /**
     *
     */
    @TableField(value = "`index`")
    private Integer index;
    /**
     *
     */
    @TableField("square_medium")
    @ONodeAttr(name = "square_medium")
    private String squareMedium;
    /**
     *
     */
    @TableField("medium")
    private String medium;
    /**
     *
     */
    @TableField("large")
    private String large;
    /**
     *
     */
    @TableField("original")
    private String original;
}