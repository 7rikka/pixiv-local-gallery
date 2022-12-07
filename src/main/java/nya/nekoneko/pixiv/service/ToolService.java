package nya.nekoneko.pixiv.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import nya.nekoneko.pixiv.mapper.ToolMapper;
import nya.nekoneko.pixiv.model.Tool;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.aspect.annotation.Service;

/**
 * @author Ho
 */
@Service
public class ToolService {
    @Db
    private ToolMapper toolMapper;

    public void save(Tool tool) {
        LambdaQueryWrapper<Tool> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tool::getIllustId, tool.getIllustId());
        wrapper.eq(Tool::getName, tool.getName());
        if (!toolMapper.exists(wrapper)) {
            toolMapper.insert(tool);
        }
    }
}
