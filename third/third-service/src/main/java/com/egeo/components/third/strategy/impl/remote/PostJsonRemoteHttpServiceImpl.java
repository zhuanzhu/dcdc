package com.egeo.components.third.strategy.impl.remote;

import com.egeo.components.third.common.ChannelServiceMethodEnum;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.third.strategy.RemoteHttpService;
import com.egeo.components.utils.HttpClientSingleton;
import com.egeo.components.utils.HttpUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.http.HttpClientUtil;
import com.egeo.utils.log.XLogger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("postJsonRemoteHttpServiceImpl")
public class PostJsonRemoteHttpServiceImpl implements RemoteHttpService {
    private final String CHARSET_UTF_8="UTF-8";
    public XLogger logger = XLogger.getLogger(this.getClass().getName());

    @Override
    public String getChannelServiceMethod() {
        return ChannelServiceMethodEnum.POST_JSON.getChannelServiceMethod();
    }

    @Override
    public String send(ChannelServiceConfigDTO dto, Object paramObject) {
        String paramString = null;
        if(Objects.nonNull(paramObject)){
            paramString = (String)paramObject;
        }
        logger.info("http 请求url:{},请求参数{}",dto.getChannelServiceUrl(),paramString);
        //String result =HttpClientUtil.doPostByJson(dto.getChannelServiceUrl(), CHARSET_UTF_8,paramString);
        //String result =doPostByJson(dto.getChannelServiceUrl(), CHARSET_UTF_8,paramString);
        String result = null;
        try {
            result = HttpClientSingleton.doPostByJson(dto.getChannelServiceUrl(), CHARSET_UTF_8,paramString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("http请求结果:{}",result);
        return result;
    }

    public static String doPostByJson(String url, String charset, String content) {
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        try {
            StringEntity se = new StringEntity(content, charset);
            se.setContentType("application/json; charset=" + charset);
            post.setEntity(se);
            HttpResponse response = client.execute(post);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static String doPostAutoCloseByJson(String url, String charset, String content) {
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        try {
            StringEntity se = new StringEntity(content, charset);
            se.setContentType("application/json; charset=" + charset);
            post.setEntity(se);

            try (CloseableHttpResponse response = client.execute(post)) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    try {
                        result = EntityUtils.toString(resEntity, charset);
                    } catch (IOException e) {
                        // 处理读取响应体时发生的异常
                        e.printStackTrace();
                        // 可以选择抛出异常或进行其他错误处理
                        // throw new RuntimeException("Failed to read response entity", e);
                    }
                }
            } catch (IOException e) {
                // 处理执行POST请求时发生的异常（不包括读取响应体）
                e.printStackTrace();
                // 可以选择抛出异常或进行其他错误处理
                // throw new RuntimeException("Failed to execute POST request", e);
            }
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                // 处理关闭客户端时发生的异常
                e.printStackTrace();
                // 在这里通常不建议再次抛出异常，因为我们已经处于finally块中
                // 如果需要，可以记录日志或使用其他错误报告机制
            }
        }

        return result;
    }
}
