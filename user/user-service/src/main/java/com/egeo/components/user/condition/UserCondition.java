package com.egeo.components.user.condition;

import java.util.Date;

import com.egeo.components.user.po.UserPO;

/**
 * 
 * @author xiaping
 * @date 2017-05-15 15:45:26
 */
public class UserCondition extends UserPO {
	private static final long serialVersionUID = 1L;
	private Long uxId;

        /**
         * 性别
         */
        private Integer sex;

        /**
         * 昵称
         */
        private String nickname;

        /**
         * 头像
         */
        private String headPicUrl;

        /**
         * 
         */
        private String name;
        /**
    	 * 用户注册时间
    	 */
    	private Date regtime;
    	/**
    	 * 是否失效 0否1是
    	 */
    	private Integer accountStatus;
    	/**
    	 * 用户状态 0:未注册 1:已注册
    	 */
    	private Integer status;
    	
        private String manageType;
        private String sysCode;
        private Long roleId;
        private Integer isAvailable;
        
        public String getSysCode() {
			return sysCode;
		}

		public void setSysCode(String sysCode) {
			this.sysCode = sysCode;
		}

		public Long getRoleId() {
			return roleId;
		}

		public void setRoleId(Long roleId) {
			this.roleId = roleId;
		}

		public Integer getIsAvailable() {
			return isAvailable;
		}

		public void setIsAvailable(Integer isAvailable) {
			this.isAvailable = isAvailable;
		}


		public String getManageType() {
			return manageType;
		}

		public void setManageType(String manageType) {
			this.manageType = manageType;
		}

		public Long getUxId() {
            return uxId;
        }

        public void setUxId(Long uxId) {
            this.uxId = uxId;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadPicUrl() {
            return headPicUrl;
        }

        public void setHeadPicUrl(String headPicUrl) {
            this.headPicUrl = headPicUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

		public Date getRegtime() {
			return regtime;
		}

		public void setRegtime(Date regtime) {
			this.regtime = regtime;
		}

		public Integer getAccountStatus() {
			return accountStatus;
		}

		public void setAccountStatus(Integer accountStatus) {
			this.accountStatus = accountStatus;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

}
	