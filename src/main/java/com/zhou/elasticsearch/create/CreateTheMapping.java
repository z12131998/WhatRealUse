package com.zhou.elasticsearch.create;

import com.zhou.elasticsearch.util.ElasticSearchUtils;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

/**
 * @author 周俊宇
 */
public class CreateTheMapping {


	public static void CreateTheDoc() throws IOException {
		RestHighLevelClient client = ElasticSearchUtils.getClient();
		CreateIndexRequest create = new CreateIndexRequest("test");
		create.alias(new Alias("ipaddress"));
		client.indices().create(create, RequestOptions.DEFAULT);
		XContentBuilder dynamic = XContentFactory.jsonBuilder();

		//在7.2后 使用默认的_doc类型 JSON串不需要再设定
		dynamic.startObject()
				//限定字段,只有包含需需要的字段才进行存储,否者抛错
				.field("dynamic", "strict")
				.startObject("properties")
				.startObject("ip").field("type", "keyword").endObject()
				.startObject("date").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()
				.startObject("name").field("type", "text").endObject()
				.startObject("level").field("type", "byte").endObject()
				.endObject()
				.endObject();
		client.indices().putMapping(new org.elasticsearch.client.indices.PutMappingRequest("test")
				.source(dynamic), RequestOptions.DEFAULT);
	}
}
