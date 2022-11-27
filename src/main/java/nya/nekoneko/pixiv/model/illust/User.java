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
 * 用户信息
 *
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    /**
     * 用户名称
     */
    @TableField(value = "name")
    private String name;
    /**
     * 账号名
     */
    @TableField(value = "account")
    private String account;
    /**
     * 头像信息
     */
    @TableField(value = "medium_profile_image_urls")
    @ONodeAttr(name = "medium_profile_image_urls")
    private String mediumProfileImageUrls;
}