package webcat.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	public String post(String url, String param) {

		String retMes = "";
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);

		try {
			httppost.setEntity(new StringEntity(param, Charset.forName("UTF-8")));
			CloseableHttpResponse response = httpclient.execute(httppost);

			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					retMes = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return retMes;
	}

	public String get(String url) {
		String retMes = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
//			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);

			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				// 打印响应状态
				System.out.println(response.getStatusLine());
				if (entity != null) {
					retMes = EntityUtils.toString(entity);
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return retMes;
	}

	/**
	 * 返回素材ID
	 * @param url
	 * @param file
	 * @return
	 */
	public String uploadMedia(String url, File file){
		String retMes = "";

		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);

		FileBody fb = new FileBody(file);
		HttpEntity entity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532).addPart("media", fb).build();

		httppost.setEntity(entity);

		try {
			CloseableHttpResponse response = httpclient.execute(httppost);

			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					HttpEntity e = response.getEntity();
					String jsonString = EntityUtils.toString(e);
					JSONObject fromObject = JSONObject.parseObject(jsonString);
					Object media_idObject = fromObject.get("media_id");
					if (media_idObject != null) {
						retMes = media_idObject.toString();
					} else {
						System.out.println(fromObject.toString());
					}
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return retMes;
	}
}
