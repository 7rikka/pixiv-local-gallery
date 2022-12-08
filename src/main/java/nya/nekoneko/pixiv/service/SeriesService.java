package nya.nekoneko.pixiv.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import nya.nekoneko.pixiv.mapper.SeriesMapper;
import nya.nekoneko.pixiv.model.Series;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.aspect.annotation.Service;

/**
 * @author Ho
 */
@Service
public class SeriesService {
    @Db
    private SeriesMapper seriesMapper;

    /**
     * @param series
     */
    public void save(Series series) {
        if (null == series) {
            return;
        }
        LambdaQueryWrapper<Series> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Series::getId, series.getId());
        if (!seriesMapper.exists(wrapper)) {
            seriesMapper.insert(series);
        } else {
            seriesMapper.updateById(series);
        }
    }
}
