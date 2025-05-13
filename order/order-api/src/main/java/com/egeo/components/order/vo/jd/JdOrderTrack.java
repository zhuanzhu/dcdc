package com.egeo.components.order.vo.jd;

import java.util.List;

/**
 * Created by 0.0 on 2019/3/25.
 */
public class JdOrderTrack {
    private List<JdOrderTrackBody> orderTrack;
    private List<JdWaybillCode> waybillCode;
	public List<JdOrderTrackBody> getOrderTrack() {
		return orderTrack;
	}
	public void setOrderTrack(List<JdOrderTrackBody> orderTrack) {
		this.orderTrack = orderTrack;
	}
	public List<JdWaybillCode> getWaybillCode() {
		return waybillCode;
	}
	public void setWaybillCode(List<JdWaybillCode> waybillCode) {
		this.waybillCode = waybillCode;
	}
	public boolean hasInfo() {
		return orderTrack!=null && waybillCode!=null && orderTrack.size()>0 && waybillCode.size()>0;
	}
}
