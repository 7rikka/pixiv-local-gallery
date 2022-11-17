package nya.nekoneko.pixiv.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;

public class Call {
    private static final OkHttpClient client = new OkHttpClient().newBuilder()
            .readTimeout(Duration.ofSeconds(100))
            .connectTimeout(Duration.ofSeconds(100))
            .callTimeout(Duration.ofSeconds(100))
            .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)))
            .build();
    private static final int SUCCESS = 200;
//    static {
//        client.dispatcher().setMaxRequestsPerHost(16);
//        client.dispatcher().setMaxRequests(16);
//    }

    public static Response doCallGetResponse(Request request) {
        try {
            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static String doCallGetString(Request request) {
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}