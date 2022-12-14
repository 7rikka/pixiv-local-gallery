package nya.nekoneko.pixiv.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import nya.nekoneko.pixiv.mapper.TagMapper;
import nya.nekoneko.pixiv.model.Tag;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

/**
 * @author Ho
 */
@Component
public class TagService {
    @Db
    private TagMapper tagMapper;

    public void save(Tag tag) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getName, tag.getName());
        if (!tagMapper.exists(wrapper)) {
            tagMapper.insert(tag);
        } else {
            tagMapper.updateById(tag);
        }
    }

    public Tag get(String name) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getName, name);
        return tagMapper.selectOne(wrapper);
    }
}
