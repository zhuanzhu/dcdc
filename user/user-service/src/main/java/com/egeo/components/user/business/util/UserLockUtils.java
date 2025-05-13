package com.egeo.components.user.business.util;

import com.egeo.components.user.common.Constants;
import com.egeo.components.user.common.DateUtils;
import com.egeo.utils.cache.JedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Service
public class UserLockUtils {


    @Resource
    private JedisUtil cache;

    private static final int seconds=30*60*1000;

    private static final int maxTimes=5;

    /**
     * 获取锁定信息
     * @param uName
     * @return
     */
    public String lockMsg(String uName){
        String errorMsg="";
        LockInfo lockInfo=(LockInfo)cache.get(getCacheKey(uName));
        if (Objects.nonNull(lockInfo) && lockInfo.getErrorTimes()>=maxTimes){
            long lockTime=lockInfo.lockTime;
            long remainTime=lockTime+seconds-new Date().getTime();
            if (remainTime>0){
                errorMsg= DateUtils.formatTime(remainTime);
            }
        }
        return errorMsg;
    }

    /**
     * 记录用户密码错误信息
     * @param uName
     */
    public void lock(String uName){
        String lockKey=getCacheKey(uName);
        LockInfo lockInfo=(LockInfo)cache.get(lockKey);
        if (Objects.isNull(lockInfo)){
            lockInfo=new LockInfo();
            lockInfo.setuName(uName);
            lockInfo.setErrorTimes(1);
        }else {
            lockInfo.setErrorTimes(lockInfo.getErrorTimes()+1);
            if (lockInfo.getErrorTimes()>=maxTimes){
                lockInfo.setLockTime(new Date().getTime());
            }
        }
        cache.set(lockKey,seconds,lockInfo);
    }

    /**
     * 解除锁定信息
     * @param uName
     */
    public void unLock(String uName){
        String lockKey=getCacheKey(uName);
        LockInfo lockInfo=(LockInfo)cache.get(lockKey);
        if (Objects.nonNull(lockInfo)){
            cache.del(lockKey);
        }
    }

    private String getCacheKey(String uName){
        return Constants.CacheKey.LOGIN_ERROR_TIMES_CACHE_KEY+uName;
    }

    private static class LockInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        //锁定时间
        private long lockTime;
        //密码错误次数
        private int errorTimes;
        //登录用户名
        private String uName;

        public long getLockTime() {
            return lockTime;
        }

        public void setLockTime(long lockTime) {
            this.lockTime = lockTime;
        }

        public String getuName() {
            return uName;
        }

        public void setuName(String uName) {
            this.uName = uName;
        }

        public int getErrorTimes() {
            return errorTimes;
        }

        public void setErrorTimes(int errorTimes) {
            this.errorTimes = errorTimes;
        }
    }
}
