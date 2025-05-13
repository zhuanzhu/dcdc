package com.egeo.components.utils;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


public class HttpService
{
	// 日志
	private static final Logger log = Logger.getLogger(HttpService.class);

	private static  CloseableHttpClient httpclient = null;

    public static  CloseableHttpClient getHttpClient(){
    	if(httpclient == null){
    		httpclient = HttpClients.createDefault();
    	}
    	return httpclient;
    }

        /**
         * 向指定URL发送GET方法的请求
         *
         * @param url
         *            发送请求的URL
         * @param param
         *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
         * @return URL 所代表远程资源的响应结果
         */
        public static String sendGet(String url, String param) {
            String result = "";
            BufferedReader in = null;
            try {
            	 String urlNameString =null;
            	if(param!=null){
            		  urlNameString = url + "?" + param;
            	}else{
            		urlNameString = url ;
            	}
                URL realUrl = new URL(urlNameString);
                // 打开和URL之间的连接
                URLConnection connection = realUrl.openConnection();
                // 设置通用的请求属性
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 建立实际的连接
                connection.connect();
                // 获取所有响应头字段
                Map<String, List<String>> map = connection.getHeaderFields();
                // 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + "--->" + map.get(key));
                }
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送GET请求出现异常！" + e);
                e.printStackTrace();
            }
            // 使用finally块来关闭输入流
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return result;
        }

        /**
         * 向指定 URL 发送POST方法的请求
         *
         * @param url
         *            发送请求的 URL
         * @param param
         *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
         * @return 所代表远程资源的响应结果
         */
        public static String sendPost(String url, String param) {
            PrintWriter out = null;
            BufferedReader in = null;
            String result = "";
            try {
                URL realUrl = new URL(url);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 设置Content-Type为application/x-www-form-urlencoded
                //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送 POST 请求出现异常！"+e);
                e.printStackTrace();
            }
            //使用finally块来关闭输出流、输入流
            finally{
                try{
                    if(out!=null){
                        out.close();
                    }
                    if(in!=null){
                        in.close();
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            return result;
        }


        /**
         * 发送 get请求
         */
        public static String sendGetUrl(String url, String param) {
            CloseableHttpClient httpclient = getHttpClient();
            String result ="";
            try {
            	String urlNameString =null;
            	if(param!=null){
            		  urlNameString = url + "?" + param;
            	}else{
            		urlNameString = url ;
            	}
                // 创建httpget.
                HttpGet httpget = new HttpGet(urlNameString);
                // 执行get请求.
                CloseableHttpResponse response = httpclient.execute(httpget);
                try {
                    // 获取响应实体
                    HttpEntity entity = response.getEntity();
                    log.info("--------------------------------------");
                    // 打印响应状态
                    log.info("请求响应状态:"+response.getStatusLine());
                    if (entity != null) {
                        // 打印响应内容长度
                    	log.info("响应内容长度 :" + entity.getContentLength());
                        result = EntityUtils.toString(entity);
                    }
                    log.info("------------------------------------");

                } finally {
                    response.close();
                }
            } catch (ClientProtocolException e) {
            	 log.error("发送GET请求出现异常！" + e);
                e.printStackTrace();
            } catch (ParseException e) {
            	 log.error("发送GET请求出现转换异常！" + e);
                e.printStackTrace();
            } catch (IOException e) {
            	 log.error("发送GET请求出现IO流异常！" + e);
                e.printStackTrace();
            }
            return result;
        }

}
