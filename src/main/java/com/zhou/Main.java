package com.zhou;

import com.zhou.elasticsearch.create.CreateTheMapping;
import com.zhou.elasticsearch.util.ElasticSearchUtils;
import com.zhou.util.PropertiesUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author 周俊宇
 */
public class Main {
	public static void main(String[] args) throws IOException {
		Map<String, String> stringStringMap = PropertiesUtils.readInsideProperties("elasticsearch.properties");
		System.out.println(stringStringMap.get("service.port"));
		ElasticSearchUtils.initUtils(stringStringMap.get("service.ip"), stringStringMap.get("service.port"));

		//创建索引
		CreateTheMapping.CreateTheDoc();
	}
}
