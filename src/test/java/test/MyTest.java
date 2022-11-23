package test;

import nya.nekoneko.pixiv.Main;
import nya.nekoneko.pixiv.client.PixivClient;
import nya.nekoneko.pixiv.mapper.IllustMapper;
import org.apache.ibatis.solon.annotation.Db;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(Main.class)
public class MyTest {
    @Test
    public void test1(){
        PixivClient client = new PixivClient("iOnxjjqUuM-7LCrfXWTshsr701uWtvmR0BRZlUgOsFA", "_DqciSKE18yE7OXcIu7dCnwccqTkB81VXV4RkG4Boco", 3600, LocalDateTime.now());
        client.refreshToken();
    }
    @Db
    private IllustMapper illustMapper;
    @Test
    public void s(){
        illustMapper.selectList(null);
    }
}
