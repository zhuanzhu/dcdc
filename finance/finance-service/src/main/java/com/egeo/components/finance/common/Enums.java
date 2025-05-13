package com.egeo.components.finance.common;

public interface Enums {

	/**
	 * @author 企业主体枚举
	 *
	 */
	public interface EnterpriseEntity{

		/**
		 * @author 审核状态
		 *
		 */
		public static enum AuditStatus{

			auditting(0,"审核中"),
			pass(1,"审核通过"),
			unpass(2,"审核拒绝"),
			invalid(-1,"状态异常");
			/** 定义枚举类型自己的属性 **/
			private final Integer code;
			private final String name;

			private AuditStatus(Integer code,String name) {
				this.code = code;
				this.name = name;
			}

		    public Integer code() {
		        return code;
		    }  
		    public boolean equals(AuditStatus other) {
		    	if(code.equals(other.code())) {
		    		return true;
		    	}
		        return false;
		    } 
		    public boolean equals(Integer otherCode) {
		    	if(code.equals(otherCode)) {
		    		return true;
		    	}
		        return false;
		    } 
		    public String nameStr() {
		        return name;
		    }

		    public static boolean isValidate(Integer code) {
		    	if(code!=null) {

		    		AuditStatus[] all_type = AuditStatus.values();
	    	    	for(AuditStatus one:all_type){
	    	    		if(one.code().equals(code)){
	    	    			return true;
	    	    		}
	    	    	}
		    	}
		    	return false;
		    }
		    public static AuditStatus type(Integer code) {
		    	AuditStatus[] all_type = AuditStatus.values();
		    	if(code!=null) {
			    	for(AuditStatus one:all_type){
			    		if(one.code().equals(code)){
			    			return one;
			    		}
			    	}
		    	}
		    	return invalid;
		    }
		}	
		

		/**
		 * @author 合作状态
		 *
		 */
		public static enum CooperationState{

			stop(0,"终止合作"),
			inCooperation (1,"合作中"),
			unCooperation (2,"未合作"),
			invalid(-1,"状态异常");
			/** 定义枚举类型自己的属性 **/
			private final Integer code;
			private final String name;

			private CooperationState(Integer code,String name) {
				this.code = code;
				this.name = name;
			}

		    public Integer code() {
		        return code;
		    }  
		    public boolean equals(CooperationState other) {
		    	if(code.equals(other.code())) {
		    		return true;
		    	}
		        return false;
		    } 
		    public boolean equals(Integer otherCode) {
		    	if(code.equals(otherCode)) {
		    		return true;
		    	}
		        return false;
		    } 
		    public String nameStr() {
		        return name;
		    }

		    public static boolean isValidate(Integer code) {
		    	if(code!=null) {

		    		CooperationState[] all_type = CooperationState.values();
	    	    	for(CooperationState one:all_type){
	    	    		if(one.code().equals(code)){
	    	    			return true;
	    	    		}
	    	    	}
		    	}
		    	return false;
		    }
		    public static CooperationState type(Integer code) {
		    	CooperationState[] all_type = CooperationState.values();
		    	if(code!=null) {
			    	for(CooperationState one:all_type){
			    		if(one.code().equals(code)){
			    			return one;
			    		}
			    	}
		    	}
		    	return invalid;
		    }
		}	
	}
}
