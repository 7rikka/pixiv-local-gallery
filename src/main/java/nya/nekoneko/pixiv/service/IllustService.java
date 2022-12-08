package nya.nekoneko.pixiv.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import nya.nekoneko.pixiv.mapper.IllustMapper;
import nya.nekoneko.pixiv.model.Illust;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

/**
 * @author Ho
 */
@Component
public class IllustService {
    @Db
    private IllustMapper illustMapper;

    /**
     * 保存插画信息
     *
     * @param illust
     */
    public void save(Illust illust) {
        LambdaQueryWrapper<Illust> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Illust::getId, illust.getId());
        if (!illustMapper.exists(wrapper)) {
            illustMapper.insert(illust);
        } else {
            illustMapper.updateById(illust);
        }
    }
}
