package com.egeo.components.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.egeo.utils.log.XLogger;

/**
 * Created by guofeng.qin on 2017/4/19 0019.
 */
public class HttpUtils {
	private static final XLogger logger = XLogger.getLogger(HttpUtils.class);
	private static HttpClient httpClient;

	public static HttpClient createTrustHttpsClient() {
		return createHttpsClient(null, null, null, null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public static synchronized HttpClient createHttpsClient(String keyStoreFile, String keyStorePass,
			String trustStoreFile, String trustStorePass) {
		if (httpClient == null) {
			logger.warn(">>>>>>>>>>>>进入httpclient<<<<<<<<<<<<");
			try {
				SSLContextBuilder builder = SSLContexts.custom();
				if (!StringUtil.isEmpty(keyStoreFile)) {
					URL fileUrl = HttpUtils.class.getResource(keyStoreFile);
					String path = fileUrl.getPath();
					InputStream ksis = new FileInputStream(new File(path));// 私钥证书
					KeyStore ks = KeyStore.getInstance("PKCS12");
					ks.load(ksis, keyStorePass.toCharArray());
					builder.loadKeyMaterial(ks, keyStorePass.toCharArray());
					ksis.close();
				}
				if (!StringUtil.isEmpty(trustStoreFile)) {
					InputStream tsis = new FileInputStream(new File(trustStoreFile));// 信任证书库
					KeyStore ts = KeyStore.getInstance("JKS");
					ts.load(tsis, trustStorePass.toCharArray());
					builder.loadTrustMaterial(ts, new TrustSelfSignedStrategy());
					tsis.close();
				} else {
					// 如果没有服务器证书，可以采用自定义 信任机制
					// 信任所有
					builder.loadTrustMaterial(null, (arg0, arg1) -> true);
				}

				SSLContext sslContext = builder.build();
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" },
						null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				Registry registry = RegistryBuilder.create().register("http", PlainConnectionSocketFactory.INSTANCE)
						.register("https", sslsf).build();
				// PoolingHttpClientConnectionManager poolingHttpClientConnectionManager=new
				// PoolingHttpClientConnectionManager(registry,null,null,null,20,TimeUnit.MINUTES);
				PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(
						registry);
				poolingHttpClientConnectionManager.setDefaultMaxPerRoute(500);
				poolingHttpClientConnectionManager.setMaxTotal(500);

				httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).build();

				return httpClient;
			} catch (Exception e) {
				logger.error("创建Https客户端失败！！", e);
				return null;
			}
		} else {
			return httpClient;
		}
	}

	public static String postFile(File file, String url) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		HttpResponse httpResponse = null;
		String result = null;
		try {
			httpClient = HttpUtils.createTrustHttpsClient();
			httpPost = new HttpPost(url);
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			multipartEntityBuilder.addPart("media", new FileBody(file));
			httpPost.setEntity(multipartEntityBuilder.build());
			if (httpClient != null && httpPost != null) {
				httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					logger.info("HttpStatus error：{}", httpResponse.toString());
				}
				result = EntityUtils.toString(httpResponse.getEntity(), org.apache.http.Consts.UTF_8);
				logger.info("http post file result：{}", result);
			}
		} catch (Exception e) {
			logger.error("post file error：{}", e);
		} finally {
			try {
				if (httpResponse != null) {
					httpResponse.getEntity().getContent().close();
				}
			} catch (Exception e) {
				logger.error("post file close http error：{}", e);
			}
		}
		return result;

	}

	public static String postJson(String json, String url) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		HttpResponse httpResponse = null;
		String result = null;
		try {
			httpClient = HttpUtils.createTrustHttpsClient();
			httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(json.toString(), ContentType.APPLICATION_JSON));
			if (httpClient != null && httpPost != null) {
				httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					logger.info("HttpStatus error：{}", httpResponse.toString());
				}
				result = EntityUtils.toString(httpResponse.getEntity(), org.apache.http.Consts.UTF_8);
				logger.info("http post result：{}", result);
			}
		} catch (Exception e) {
			logger.error("post json error：{}", e);
		} finally {
			try {
				if (httpResponse != null) {
					httpResponse.getEntity().getContent().close();
				}
			} catch (Exception e) {
				logger.error("post json close http error：{}", e);
			}
		}
		return result;

	}

	public static String httpGet(String url, Map<String, String> bobyParams, Charset charset) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String result = null;
		try {
			if (charset == null) {
				charset = Consts.UTF_8;
			}
			// 组织请求参数
			StringBuilder sb = new StringBuilder();
			if (bobyParams != null && bobyParams.size() > 0) {
				for (Map.Entry<String, String> entry : bobyParams.entrySet()) {
					if (entry.getValue() == null)
						continue;
					sb.append(entry.getKey()).append("=")
							.append(URLEncoder.encode(entry.getValue().toString(), charset.toString())).append("&");
				}
			}
			if (!bobyParams.isEmpty()) {
				sb.deleteCharAt(sb.length() - 1);
			}

			HttpGet httpGet = new HttpGet(url.concat("?").concat(sb.toString()));
			response = httpClient.execute(httpGet);
			// 获取响应码
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, charset.toString());
				logger.info("http get result：{}", result);
			}

		} catch (Exception e) {
			logger.error("http get error：{}", e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error("http get close error：{}", e);
			}
		}
		return result;

	}

	/**
	 * 获取指定url中的某个参数
	 * 
	 * @param url
	 * @param name
	 * @return
	 */
	public static String getParamByUrl(String url, String name) {
		url += "&";
		String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";

		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(url);
		if (m.find()) {
			//System.out.println(m.group(0));
			return m.group(0).split("=")[1].replace("&", "");
		} else {
			return null;
		}
	}

}
