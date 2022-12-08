package nya.nekoneko.pixiv.config;

import nya.nekoneko.pixiv.client.PixivClient;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;

@Configuration
public class PixivConfig {
    @Bean
    public PixivClient pixivClient() {
        return new PixivClient(
                "null");
    }
}
