package mf.utils.qiniu;

import java.io.IOException;
import java.io.InputStream;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiNiuUtil {
	// 密钥配置
	private static Auth auth = Auth.create(Const.QN_ACCESS_KEY, Const.QN_SECRET_KEY);

	public static String getUpToken(String bucketName) {
		return auth.uploadToken(bucketName);
	}

	/**
	 * 上传文件，使用默认策略，只需要设置上传的空间名就可以了
	 * 
	 * @param filePath
	 *            本地文件路径
	 * @param key
	 *            上传到七牛的该文件的标识符
	 * @return
	 * @throws IOException
	 */
	public static boolean upload(String filePath, String key, String bucketName) throws IOException {
		return uploadReal(filePath, key, bucketName);
	}

	/**
	 * 上传文件，使用默认策略，只需要设置上传的空间名就可以了
	 * @param bytes
	 * @param key
	 * @param bucketName
	 * @return
	 * @throws IOException
	 */
	public static boolean upload(byte[] bytes, String key, String bucketName) throws IOException {
		return uploadReal(bytes, key, bucketName);
	}

	/**
	 * 上传文件，使用默认策略，只需要设置上传的空间名就可以了
	 * @param inputStream
	 * @param key
	 * @param bucketName
	 * @return
	 * @throws IOException
	 */
	public static boolean upload(InputStream inputStream, String key, String bucketName) throws IOException {
		try {
			UploadManager uploadManager = getUploadManager();// 创建上传对象

			Response res = uploadManager.put(inputStream,key,getUpToken(bucketName),null, null);
			// 打印返回的信息
			System.out.println(res.isOK() + "," + res.url() + "," + res.bodyString());
			return res.isOK();
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
			return false;
		}
	}


	private static UploadManager getUploadManager(){
		Configuration cfg = new Configuration(Zone.autoZone());

		UploadManager uploadManager = new UploadManager(cfg);// 创建上传对象

		return uploadManager;
	}

	private static boolean uploadReal(Object obj, String key, String bucketName){
		try {
			UploadManager uploadManager = getUploadManager();// 创建上传对象

			Response res;

			if(obj instanceof  String){
				res = uploadManager.put((String)obj, key, getUpToken(bucketName));// 调用put方法上传
			}
			else{
				res = uploadManager.put((byte[]) obj, key, getUpToken(bucketName));// 调用put方法上传
			}
			// 打印返回的信息
			System.out.println(res.isOK() + "," + res.url() + "," + res.bodyString());
			return res.isOK();
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
			return false;
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 *            七牛资源url
	 * @return
	 */
	public static String download(String url) {
		String downloadRUL = auth.privateDownloadUrl(url);// 调用privateDownloadUrl方法生成下载链接
		System.out.println("downloadRUL:" + downloadRUL);
		return downloadRUL;
	}


	public static void main(String[] args) {

		String fileName = "D:\\testfile\\banner-3.jpg";

		try {
			QiNiuUtil.upload(fileName,"test201702283.jpg",Const.QN_BUCKETNAME);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
