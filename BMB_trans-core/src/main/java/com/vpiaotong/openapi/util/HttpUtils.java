package com.vpiaotong.openapi.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Http请求工具类
 * 
 * @author SONGDAN
 * @modified liuyong 添加decodeParm函数
 */
public final class HttpUtils {

    public static final int timeout = 10;

    /**
     * decode解码函数
     * 
     * @param param
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static String decodeParm(String param) throws UnsupportedEncodingException {
        param = URLDecoder.decode(param, "UTF-8");
        return param;
    }

    /**
     * post 请求
     *
     * @param url
     * @return
     */
    public static String post(String url) {
        return post(url, "");
    }

    /**
     * post请求
     * 
     * @param url
     * @param data
     * @return
     */
    public static String post(String url, String data) {
        return httpPost(url, data);
    }

    /**
     * 发送http post请求
     * 
     * @param url url
     * @param instream post数据的字节流
     * @return
     */
    public static String post(String url, InputStream instream) {
        try {
            HttpEntity entity = Request.Post(url).bodyStream(instream, ContentType.create("text/html", Consts.UTF_8))
                    .execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get请求
     * 
     * @param url
     * @return
     */
    public static String get(String url) {
        return httpGet(url);
    }

    /**
     * post 请求
     *
     * @param url
     * @param data
     * @return
     */
    private static String httpPost(String url, String data) {
        try {
            HttpEntity entity = Request.Post(url).bodyString(data, ContentType.create("text/html", Consts.UTF_8))
                    .execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件
     * 
     * @param url URL
     * @param file 需要上传的文件
     * @return
     */
    public static String postFile(String url, File file) {
        return postFile(url, null, file);
    }

    /**
     * 上传文件
     * 
     * @param url URL
     * @param name 文件的post参数名称
     * @param file 上传的文件
     * @return
     */
    public static String postFile(String url, String name, File file) {
        try {
            HttpEntity reqEntity = MultipartEntityBuilder.create().addBinaryBody(name, file).build();
            Request request = Request.Post(url);
            request.body(reqEntity);
            HttpEntity resEntity = request.execute().returnResponse().getEntity();
            return resEntity != null ? EntityUtils.toString(resEntity) : null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json请求
     * 
     * @param url 请求路径
     * @param json json格式的字符串
     * @return 请求结果
     */
    public static String postJson(String url, String json) {
        try {
            HttpEntity entity = Request.Post(url).bodyString(json, ContentType.create("application/json", Consts.UTF_8))
                    .execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json请求
     *
     * @param url 请求路径
     * @param paramStr json格式的字符串
     * @return 请求结果
     * @auth minchao.du
     */
    public static String sendPost(String url, String paramStr , Map<String, String> headerMap) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setRequestProperty("Content-Type","application/json");
            headerMap = Optional.ofNullable(headerMap).orElse(new HashMap<>());
            for (Map.Entry<String, String> entity : headerMap.entrySet()){
                conn.setRequestProperty(entity.getKey(),entity.getValue());
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            conn.setRequestMethod("POST"); // POST方法

            conn.connect();

            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            System.out.println("使用utf-8写出的参数为： "+ paramStr);
            out.write(paramStr);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK
                    || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED
                    || conn.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 下载文件
     * 
     * @param url URL
     * @return 文件的二进制流，客户端使用outputStream输出为文件
     */
    public static byte[] getFile(String url) {
        try {
            Request request = Request.Get(url);
            HttpEntity resEntity = request.execute().returnResponse().getEntity();
            return EntityUtils.toByteArray(resEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    private static String httpGet(String url) {
        try {
            HttpEntity entity = Request.Get(url).execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity, "UTF-8") : null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
