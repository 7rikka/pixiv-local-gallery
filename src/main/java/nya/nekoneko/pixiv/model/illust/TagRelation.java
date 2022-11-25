package nya.nekoneko.pixiv.model.illust;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 插画-标签 关系记录
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tag_relation")
public class TagRelation {
    /**
     * 记录id 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 插画id
     */
    @TableField(value = "illust_id")
    private Integer illust_id;
    /**
     * 标签id
     */
    @TableField(value = "tag_id")
    private Integer tag_id;
}
