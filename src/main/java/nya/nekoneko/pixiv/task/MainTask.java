package nya.nekoneko.pixiv.task;

import lombok.extern.slf4j.Slf4j;
import nya.nekoneko.pixiv.client.PixivClient;
import nya.nekoneko.pixiv.mapper.IllustMapper;
import nya.nekoneko.pixiv.model.Illust;
import nya.nekoneko.pixiv.util.Call;
import nya.nekoneko.pixiv.util.PixivRequestFactory;
import okhttp3.Request;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.snack.ONode;
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
    public void exec() {
        Call.setProxyProvider(() -> {
            Request request = PixivRequestFactory
                    .getPixivRequest()
                    .url("http://localhost:5010/get?type=http")
                    .get()
                    .buildRequest();
            String s = Call.doCallGetString(request);
            return ONode.loadStr(s).get("proxy").getString();
            //
        });
        for (int i = 10000000; i > 0; i--) {
            try {
                Illust illustIdDetail = client.getIllustIdDetail(i);
                log.info("{} - {}", i, illustIdDetail);
//                if (null != illustIdDetail) {
                illustMapper.insert(illustIdDetail);
//                }

            } catch (Exception e) {
                log.error("错误：", e);
            }
            try {
                Thread.sleep(5 * 1000);
            } catch (Exception e) {
                log.error("错误：", e);
            }

        }
    }
}
