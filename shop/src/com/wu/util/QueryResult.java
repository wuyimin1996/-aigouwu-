package com.wu.util;

import java.util.List;
import java.util.Map;

//封装查询结果的类
public class QueryResult {
	
	
	//查询的数据
	public List<Map<String,Object>> records;
	
	//记录总数
	private  int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Map<String, Object>> getRecords() {
		return records;
	}

	public void setRecords(List<Map<String, Object>> records) {
		this.records = records;
	}

	
	
}
