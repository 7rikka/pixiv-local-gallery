package nya.nekoneko.pixiv.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import nya.nekoneko.pixiv.mapper.ImageUrlDetailMapper;
import nya.nekoneko.pixiv.model.ImageUrlDetail;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

/**
 * @author Ho
 */
@Component
public class ImageUrlDetailService {
    @Db
    private ImageUrlDetailMapper imageUrlDetailMapper;

    /**
     * @param imageUrlDetail
     */
    public void save(ImageUrlDetail imageUrlDetail) {
        if (null == imageUrlDetail) {
            return;
        }
        LambdaQueryWrapper<ImageUrlDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ImageUrlDetail::getIllustId, imageUrlDetail.getIllustId());
        wrapper.eq(ImageUrlDetail::getIndex, imageUrlDetail.getIndex());
        wrapper.eq(ImageUrlDetail::getType, imageUrlDetail.getType());
        if (!imageUrlDetailMapper.exists(wrapper)) {
            imageUrlDetailMapper.insert(imageUrlDetail);
        } else {
            imageUrlDetailMapper.updateById(imageUrlDetail);
        }
    }
}
