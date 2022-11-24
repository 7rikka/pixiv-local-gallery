package nya.nekoneko.pixiv.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tool")
public class Tool {
    /**
     * 记录id 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     *
     */
    @TableField(value = "illust_id")
    private Integer illustId;
    /**
     *
     */
    @TableField(value = "name")
    private String name;
}
