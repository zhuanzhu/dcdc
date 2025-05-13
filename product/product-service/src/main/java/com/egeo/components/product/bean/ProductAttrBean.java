package com.egeo.components.product.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class ProductAttrBean {
	public String groupName;
	public List<HashMap<String,Object>> atts;
	
	
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public List<HashMap<String, Object>> getAtts() {
		return atts;
	}


	public void setAtts(List<HashMap<String, Object>> atts) {
		this.atts = atts;
	}

/*
	public List<HashMap<String,String>> attrStr() {
		List<HashMap<String,String>> rslt = new ArrayList<HashMap<String,String>>();
		if(atts!=null || atts.size()>0) {
			for(HashMap<String,String> one: atts) {
				if(one==null || one.size()==0) {
					continue;
				}
				for(Entry<String,List<String>> entry : one.entrySet() ) {
					HashMap<String,String> data = new HashMap<String,String>();
					data.put(entry.getKey(), String.join(";", entry.getValue()));
					rslt.add(data);
				}
			}
		}
		return rslt;
	}*/
	
}
