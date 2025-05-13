package com.egeo.components.user.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushBatchUniMsgRequest;
import com.baidu.yun.push.model.PushBatchUniMsgResponse;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.baidu.yun.push.model.QueryMsgStatusRequest;
import com.baidu.yun.push.model.QueryMsgStatusResponse;
import com.egeo.utils.SpringContextTool;

import net.sf.json.JSONObject;

/**
 * 新版本消息推送
 * @author Elsa
 *
 */
public class PushMessageNew {
	public static Logger logger = Logger.getLogger(PushMessageNew.class);
	private static BaiduPushClient AndroidPushClient = null;
	private static BaiduPushClient IOSPushClient = null;
    /**
     * 推送初始化
     * @return
     */
	static{
        // 1. get apiKey and secretKey from developer console
        PushKeyPair Androidpair = null;
        // ios 预生产 APP KEY:asdfsdf SECRET KEY:sdafsfasdf
        // ios 生产 APP KEY:ddd SECRET KEY:aaaa
        Androidpair = new PushKeyPair(SpringContextTool.getProperty("Android.API.KEY"), SpringContextTool.getProperty("Android.SECRET.KEY"));

        // 2. build a BaidupushClient object to access released interfaces
        AndroidPushClient = new BaiduPushClient(Androidpair,"api.tuisong.baidu.com");

        // 3. register a YunLogHandler to get detail interacting information
        AndroidPushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        
        // 1. get apiKey and secretKey from developer console
        PushKeyPair IOSpair = null;
        // ios 预生产 APP KEY:asdfsdf SECRET KEY:sdafsfasdf
        // ios 生产 APP KEY:ddd SECRET KEY:aaaa
        IOSpair = new PushKeyPair(SpringContextTool.getProperty("IOS.APP.KEY"), SpringContextTool.getProperty("IOS.SECRET.KEY"));
       
        // 2. build a BaidupushClient object to access released interfaces
        IOSPushClient = new BaiduPushClient(IOSpair,"api.tuisong.baidu.com");

        // 3. register a YunLogHandler to get detail interacting information
        IOSPushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
    }
    /**
     * Android 推送消息给批量设备（批量单播）IOS 不支持
     * @param title  通知标题
     * @param description 通知文本内容
     * @param channelIds  
     * @param deviceType
     * @return
     * @throws IOException 
     */
    public static Map<String, Object> androidPushBatchUniMsg(String title,String description,String[] channelIds,int deviceType) throws IOException{
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        try {
            // 4.specify request arguments
            //创建 Android 通知
            JSONObject notification = new JSONObject();
            notification.put("title", title);
            notification.put("description",description);
            notification.put("notification_builder_id", 0);
            notification.put("notification_basic_style", 4);
            notification.put("open_type", 1);
            notification.put("url", "http://push.baidu.com"); 

            JSONObject jsonCustormCont = new JSONObject();
            jsonCustormCont.put("key", "value"); //自定义内容，key-value
            notification.put("custom_content", jsonCustormCont);

            PushBatchUniMsgRequest request = new PushBatchUniMsgRequest()
                    .addChannelIds(channelIds)
                    .addMsgExpires(new Integer(3600))
                    .addMessageType(1)
                    .addMessage(notification.toString())
                    .addDeviceType(deviceType)
                    .addTopicId("BaiduPush");// 设置类别主题
            // 5. http request
            PushBatchUniMsgResponse response = AndroidPushClient
                    .pushBatchUniMsg(request);
            // Http请求结果解析打印
            System.out.println(String.format("msgId: %s, sendTime: %d",
                    response.getMsgId(), response.getSendTime()));

            logger.debug(String.format("msgId: %s, sendTime: %d",response.getMsgId(), response.getSendTime()));

            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());

        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
                    throw e;
                } catch (PushClientException e1) {
                    e1.printStackTrace();
                }
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
                    throw e;
                } catch (PushServerException e1) {
                    e1.printStackTrace();
                }
            } else {
            	logger.error(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
    }

    /**
     * 推送消息给所有设备，广播推送。
     * @param title
     * @param description
     * @param sendTime
     * @param expireTime
     * @param messageType
     * @param openType
     * @param url
     * @param deviceType
     * @return
     * @throws PushClientException
     * @throws PushServerException
     */
    public static Map<String, Object> pushMsgToAll(String title,String description,long sendTime,int expireTime,int messageType,int openType,
            String url,int deviceType,String alert,Object ...obj) throws PushClientException, PushServerException{
        BaiduPushClient pushClient = null;
        Map<String, Object> jsonMap = new HashMap<String,Object>();

        JSONObject message = new JSONObject();


        if(deviceType == 3){ // Android 设备
        	pushClient = AndroidPushClient;
            message.put("title", title);
            message.put("description",description);
            message.put("notification_builder_id", 0);
            message.put("notification_basic_style", 4);
            message.put("open_type", openType); //默认 1
            message.put("url", url);

            JSONObject jsonCustormCont = new JSONObject();

            if(StringUtils.isNotBlank(obj.toString())){
                for (int i = 0; i < obj.length; i++) {
                    jsonCustormCont.put("key"+ (i+1), obj[i]); //自定义内容，key-value
                }
            }

            message.put("custom_content", jsonCustormCont);

        }else if(deviceType == 4){ //iOS 设备
        	pushClient = IOSPushClient;
        	JSONObject jsonAPS = new JSONObject();
            jsonAPS.put("alert", alert);
            jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
            jsonAPS.put("badge", "1");
            message.put("aps", jsonAPS);

            if(StringUtils.isNotBlank(obj.toString())){
                for (int j = 0; j < obj.length; j++) {
                    message.put("key" + (j+1), obj[j]);
                }
            }
        }

        try {
            PushMsgToAllRequest request = new PushMsgToAllRequest()
                    .addMsgExpires(new Integer(expireTime)) //默认 new Integer(3600)
                    .addMessageType(messageType) // 0:透传消息  1：通知   默认为0 注：IOS只有通知.
                    .addMessage(message.toString())//添加透传消息
                    .addDepolyStatus(Integer.valueOf(SpringContextTool.getProperty("deploy.status"))) // 仅IOS应用推送时使用，默认值为null，取值如下：1：开发状态 2：生产状态
                    .addSendTime(sendTime) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送   System.currentTimeMillis() / 1000 + 120
                    .addDeviceType(deviceType);

            // 5. http request
            PushMsgToAllResponse response = pushClient.pushMsgToAll(request);

            // Http请求返回值解析
            logger.debug(String.format("msgId: %s, sendTime: %d",response.getMsgId(), response.getSendTime()));

            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());
            jsonMap.put("timerId", response.getTimerId()); //推送定时消息时，返回该字段，标识定时任务。

        } catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                throw e;
            } else {
            	logger.error("推送消息给所有设备出现异常 " + e.getMessage());
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
            	logger.error(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
    }

    /**
     * 向单个设备推送消息。
     * @param channelId
     * @param expireTime
     * @param messageType
     * @param title
     * @param description
     * @param openType
     * @param url
     * @param deviceType
     * @param obj
     * @return
     * @throws PushClientException
     * @throws PushServerException
     */
    public static Map<String, Object> pushMsgToSingleDevice(String channelId,int expireTime,int messageType,
            String title,String description,int openType,String url,
            int deviceType,String alert, Object ...obj) throws PushClientException, PushServerException{

        BaiduPushClient pushClient = null;
        Map<String, Object> jsonMap = new HashMap<String,Object>();

        JSONObject message = new JSONObject();


        if(deviceType == 3){ // Android 设备
        	pushClient = AndroidPushClient;
            message.put("title", title);
            message.put("description",description);
            message.put("notification_builder_id", 0);
            message.put("notification_basic_style", 4);
            // 默认 1 点击通知后的行为(1：打开Url; 2：自定义行为；)。 
			// open_type =0，只回调onNotificationClicked方法，不做其他操作； open_type = 1，url != null：打开网页； 
            // open_type = 2，pkg_content = null：直接打开应用； open_type = 2，pkg_content != null：自定义动作打开应用。
            message.put("open_type", openType); 
            message.put("url", url); // 需要打开的Url地址，open_type为1时才有效

            JSONObject jsonCustormCont = new JSONObject();

            if(StringUtils.isNotBlank(obj.toString())){
                for (int i = 0; i < obj.length; i++) {
                    jsonCustormCont.put("key"+ (i+1), obj[i]); //自定义内容，key-value
                }
            }

            message.put("custom_content", jsonCustormCont);

        }else if(deviceType == 4){ //iOS 设备
        	pushClient = IOSPushClient;
            JSONObject jsonAPS = new JSONObject();
            jsonAPS.put("alert", alert);
            jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
            jsonAPS.put("badge", "1");
            message.put("aps", jsonAPS);

            if(StringUtils.isNotBlank(obj.toString())){
                for (int j = 0; j < obj.length; j++) {
                    message.put("key" + (j+1), obj[j]);
                }
            }
        }


        try {
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
                    .addChannelId(channelId)
                    .addMsgExpires(expireTime) // message有效时间
                    .addMessageType(messageType)// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
                    .addDeployStatus(Integer.valueOf(SpringContextTool.getProperty("deploy.status"))) // 仅IOS应用推送时使用，默认值为null，取值如下：1：开发状态 2：生产状态
                    .addMessage(message.toString())
                    .addDeviceType(deviceType);// deviceType => 3:android, 4:ios


            // 5. http request
            PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);

            logger.debug("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime());

            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());

        }catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                throw e;
            } else {
            	logger.error("推送消息给所有设备出现异常 " + e.getMessage());
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
            	logger.error(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
    }


    /**
     * 查询消息推送状态，包括成功、失败、待发送、发送中4种状态。
     * @return
     * @throws PushClientException 
     * @throws PushServerException 
     */
    public static List<?> QueryMsgStatus(String[] msgIds,Integer deviceType) throws PushClientException, PushServerException{

        BaiduPushClient pushClient = null;
        if(deviceType == 3){
        	pushClient = AndroidPushClient;
        }else if(deviceType == 4){
        	pushClient = IOSPushClient;
        }
        //List<MsgSendInfo> sendInfo = null;

        QueryMsgStatusResponse response = null;
        try {
            // 4. specify request arguments
            QueryMsgStatusRequest request = new QueryMsgStatusRequest()
                    .addMsgIds(msgIds)
                    .addDeviceType(deviceType);

            // 5. http request
            response = pushClient.queryMsgStatus(request);

            // Http请求结果解析打印
            logger.debug("totalNum: " + response.getTotalNum() + "\n"
                    + "result:");
            /*
            将通知消息保存到日志中
            if( null != response){
                List<?> list = response.getMsgSendInfos();
                for (int i = 0; i < list.size(); i++) {
                    Object object = list.get(i);
                    if(object instanceof MsgSendInfo){
                        MsgSendInfo msgSendInfo = (MsgSendInfo) object;
                        StringBuilder strBuilder = new StringBuilder();
                        strBuilder.append("List[" + i + "]: {" + "msgId = "
                                + msgSendInfo.getMsgId() + ",status = "
                                + msgSendInfo.getMsgStatus() + ",sendTime = "
                                + msgSendInfo.getSendTime() + ",success = "
                                + msgSendInfo.getSuccessCount());
                        strBuilder.append("}\n");
                        Logger.debug(strBuilder.toString());
                    }
                }
            }

            */
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return response.getMsgSendInfos();
    }
}