import nya.nekoneko.pixiv.Main;
import nya.nekoneko.pixiv.client.PixivClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;

import java.time.LocalDateTime;

@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(Main.class)
public class MyTest {
    @Test
    public void test1(){
        PixivClient client = new PixivClient(
                null,
                null,
                3600,
                LocalDateTime.now()
        );
        //插画
//        client.getIllustIdDetail(10000000);
        //动图
//        client.getIllustIdDetail(99615360);
        //漫画
        client.getIllustIdDetail(102932517);
    }
}
