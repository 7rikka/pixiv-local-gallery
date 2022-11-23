package nya.nekoneko.pixiv.task;

import lombok.extern.slf4j.Slf4j;
import nya.nekoneko.pixiv.client.PixivClient;
import nya.nekoneko.pixiv.mapper.IllustMapper;
import nya.nekoneko.pixiv.model.Illust;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.extend.schedule.IJob;

@Slf4j
@Component
public class MainTask implements IJob {
    @Inject
    private PixivClient client;
    @Db
    private IllustMapper illustMapper;

    @Override
    public int getInterval() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void exec() throws Throwable {
        for (int i = 10000000; i > 0; i--) {
            try {
                Illust illustIdDetail = client.getIllustIdDetail(i);
                log.info("{} - {}", i, illustIdDetail);
                if (null != illustIdDetail) {
                    illustMapper.insert(illustIdDetail);
                }

            } catch (Exception e) {
                log.error("错误：", e);
            }
        }
    }
}
