package nya.nekoneko.pixiv.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每张图片的详细信息
 *
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("image_url_detail")
public class ImageUrlDetail {
    /**
     * 记录id 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 插画id
     */
    @TableField(value = "illust_id")
    private Integer illustId;
    /**
     * 图片序号，从0开始
     */
    @TableField(value = "`index`")
    private Integer index;
    /**
     * 类型
     * 0：mini
     * 1：thumb
     * 2：small
     * 3：regular
     * 4：square_medium
     * 5：medium
     * 6：large
     * 7：original
     */
    @TableField(value = "type")
    private Integer type;
    /**
     * 图片大小
     * 单位：字节
     */
    @TableField(value = "size")
    private Long size;
    /**
     * 图片高度
     */
    @TableField(value = "height")
    private Integer height;
    /**
     * 图片宽度
     */
    @TableField(value = "width")
    private Integer width;
    /**
     * sha1值
     */
    @TableField(value = "sha1")
    private String sha1;
    /**
     * 状态
     * 0：未下载
     * 1：待下载
     * 2：已下载
     * 3：已删除
     */
    @TableField(value = "state")
    private Integer state;
}