package com.lhh.weather;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedInputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.util.zip.GZIPInputStream;

import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;



/**

 * 天气预报工具类(调用中华万年历接口获取)

 * @author sun.xh

 * @date 2018-08-02

 */
public class WeatherUtil {
    private static Logger logger = LoggerFactory.getLogger(WeatherUtil.class);

    /**

     * 获取今天实时天气

     * @param cityName 城市名称

     * @return type

     */
    public static String getTodayWeather(String cityName){
        String res = null;

        try {
            JSONArray array = getWeather(cityName);

            if (array != null && !array.isEmpty()) {
                JSONObject today = array.getJSONObject(0);
                System.out.println(today);
                // 获取低温
                res = today.getString("low");
                // 获取高温
                res = today.getString("high");
                System.out.println(res);

            }

        } catch (Exception e) {
            logger.error("获取天气失败", e);

        }

        return res;
    }
    /**

     * 获取最近几天天气 + 昨日天气

     * @param cityName

     * @return jsonArray

     */
    public static JSONArray getWeather(String cityName) {
        String url = "http://wthrcdn.etouch.cn/weather_mini?city=" + cityName;

        JSONArray array = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);

            String result = getJsonStringFromGZIP(httpResponse);// 获取到解压缩之后的字符串

            JSONObject jsonData = JSONObject.fromObject(result);

            logger.info("请求天气接口返回状态如下：cityName:{}, status:{},desc:{}", cityName, jsonData.getString("status"), jsonData.getString("desc"));
            if (jsonData.getString("desc").equals("OK")) {
                JSONObject data = jsonData.getJSONObject("data");

                array = data.getJSONArray("forecast");

            }

        } catch (Exception e) {
            logger.error("获取一周天气数据失败", e);

        } finally {
            try {
                httpClient.close();

            } catch (IOException e) {
                logger.error("IO异常", e);

            }

        }

        return array;

    }

    /**

     * 解压缩gzip

     * @param response

     * @return

     */

    private static String getJsonStringFromGZIP(HttpResponse response) {
        String jsonString = null;

        try {
            InputStream is = response.getEntity().getContent();

            BufferedInputStream bis = new BufferedInputStream(is);

            bis.mark(2);

                // 取前两个字节

            byte[] header = new byte[2];

            int result = bis.read(header);

            // reset输入流到开始位置

            bis.reset();

            // 判断是否是GZIP格式

            int headerData = getShort(header);

            if (result != -1 && headerData == 0x1f8b) {
                is = new GZIPInputStream(bis);

            } else {
                is = bis;

            }

            InputStreamReader reader = new InputStreamReader(is, "utf-8");

            char[] data = new char[100];

            int readSize;

            StringBuffer sb = new StringBuffer();

            while ((readSize = reader.read(data)) > 0) {
                sb.append(data, 0, readSize);

            }

            jsonString = sb.toString();

            bis.close();

            reader.close();

        } catch (Exception e) {
            logger.error("解压JSON异常", e);

        }

        return jsonString;

    }

    private static int getShort(byte[] data) {
        return (int) ((data[0] << 8) | data[1] & 0xFF);

    }

    public static void main(String[] args) {
            //测试获取实时天气

        String res = getTodayWeather("深圳");

        System.out.println(res);

    }

    

}
