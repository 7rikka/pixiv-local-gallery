package nya.nekoneko.pixiv.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import nya.nekoneko.pixiv.mapper.TagRelationMapper;
import nya.nekoneko.pixiv.model.TagRelation;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.aspect.annotation.Service;

/**
 * @author Ho
 */
@Service
public class TagRelationService {
    @Db
    private TagRelationMapper tagRelationMapper;

    public void save(TagRelation tagRelation) {
        LambdaQueryWrapper<TagRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TagRelation::getIllustId, tagRelation.getIllustId());
        wrapper.eq(TagRelation::getTagId, tagRelation.getTagId());
        if (!tagRelationMapper.exists(wrapper)) {
            tagRelationMapper.insert(tagRelation);
        }
    }
}
