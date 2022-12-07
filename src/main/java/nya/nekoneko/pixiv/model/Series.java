package nya.nekoneko.pixiv.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("series")
public class Series {
    /**
     *
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    /**
     *
     */
    private String title;
}