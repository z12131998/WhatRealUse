package com.zhou.elasticsearch.util;

import org.apache.http.HttpHost;
import org.elasticsearch.action.IndicesRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.GetIndexRequest;

import java.io.IOException;

/**
 * @author 周俊宇
 * ES工具类,获取连接,获取ES状态等方法
 */
public class ElasticSearchUtils {
	/**
	 * ES IP地址
	 */
	private static String EsIp = null;
	/**
	 * ES 端口号
	 */
	private static String EsPort = null;
	/**
	 * ES客户端
	 */
	private static RestHighLevelClient client = null;

	/**
	 * fixme 后续实际需要进行初始化的修改
	 * 初始化ES工具类
	 *
	 * @param esIp
	 * @param esPort
	 */
	public static void initUtils(String esIp, String esPort) {
		EsIp = esIp;
		EsPort = esPort;
		init();
	}

	/**
	 * 内部初始化操作,用于生成client
	 */
	private static void init() {
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		HttpHost httpHost = new HttpHost(EsIp, Integer.parseInt(EsPort));
		RestClientBuilder clientBuilder = RestClient.builder(httpHost);
		client = new RestHighLevelClient(clientBuilder);
	}

	/**
	 * 检测索引是否存在
	 *
	 * @param index
	 * @return
	 */
	public static boolean existIndex(String index) throws IOException {
		boolean result = false;
		result = client.indices().exists(new GetIndexRequest(index), RequestOptions.DEFAULT);
		return result;
	}


	public static String getEsIp() {
		return EsIp;
	}


	public static String getEsPort() {
		return EsPort;
	}

	public static RestHighLevelClient getClient() {
		return client;
	}
}
