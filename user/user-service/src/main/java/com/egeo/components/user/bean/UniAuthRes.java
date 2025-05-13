package com.egeo.components.user.bean;

public class UniAuthRes {
	private Integer uuid;
	private String parent_key;
	private String parents_key;
	private String parent_uuid;
	private String full_key;
	private String key;
	private String meta;
	private String id;

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public void setIs_array(Integer is_array) {
		this.is_array = is_array;
	}

	private String title;
	private String desc;
	private String name;
	private String value;
	private Integer type;
	private Integer order;
	private Integer is_array;
	private Integer level;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getIs_array() {
		return is_array;
	}

	public void setIs_array(int is_array) {
		this.is_array = is_array;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getParent_key() {
		return parent_key;
	}

	public void setParent_key(String parent_key) {
		this.parent_key = parent_key;
	}

	public String getParents_key() {
		return parents_key;
	}

	public void setParents_key(String parents_key) {
		this.parents_key = parents_key;
	}

	public String getParent_uuid() {
		return parent_uuid;
	}

	public void setParent_uuid(String parent_uuid) {
		this.parent_uuid = parent_uuid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getIsArray() {
		return is_array;
	}

	public boolean isArray() {
		return is_array == null ? false : (is_array == 0 ? false : true);
	}

	public void setIsArray(int isArray) {
		this.is_array = isArray;
	}

	public String getFull_key() {
		return full_key;
	}

	public void setFull_key(String full_key) {
		this.full_key = full_key;
	}

	public boolean equals(UniAuthRes object) {
		return object.getUuid().equals(this.uuid);
	}

}
