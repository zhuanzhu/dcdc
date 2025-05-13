package com.egeo.components.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by guofeng.qin on 2017/4/5 0005.
 */
public class UUIDUtil {
    public static String randomUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 16uuid
     * @return
     */
    public static String getOrderIdByUUId() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = dateFormat.format(new Date());
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {
            hashCodeV = - hashCodeV;
        }
        String uuid=machineId + String.format("%014d", hashCodeV);
        return timestamp.concat(uuid);
    }
}
