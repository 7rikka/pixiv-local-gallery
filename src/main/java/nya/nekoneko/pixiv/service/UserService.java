package nya.nekoneko.pixiv.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import nya.nekoneko.pixiv.mapper.UserMapper;
import nya.nekoneko.pixiv.model.User;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

/**
 * @author Ho
 */
@Component
public class UserService {
    @Db
    private UserMapper userMapper;

    public void save(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, user.getId());
        if (!userMapper.exists(wrapper)) {
            userMapper.insert(user);
        } else {
            userMapper.updateById(user);
        }
    }
}
