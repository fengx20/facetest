package com.fx.demo;

import com.fx.demo.HttpUtil;
import com.fx.demo.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * 人脸对比
 */
public class FaceMatch {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String faceMatch() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            JSONObject json1 = new JSONObject();
            json1.put("image", "http://n.sinaimg.cn/sinacn10121/534/w1379h755/20190325/b4db-hutwezf3658408.jpg");
            json1.put("image_type", "URL");
            json1.put("face_type", "LIVE");
            json1.put("quality_control", "LOW");
            //json1.put("liveness_control", "NONE");  //活体检测

            JSONObject json2 = new JSONObject();
            json2.put("image", "https://p1.ssl.qhimg.com/t01f9dd5914bfa209ed.jpg");
            json2.put("image_type", "URL");
            json2.put("face_type", "LIVE");
            json2.put("quality_control", "LOW");
            //json2.put("liveness_control", "NONE");   //活体检测

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(json1);
            jsonArray.put(json2);

            //Gson gson = new Gson();
            String param = jsonArray.toString();
            System.out.println(param);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.745b5c0e3c91116adec467f8c25775e8.2592000.1575007549.282335-15699170";

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            String jsonresult = toPrettyFormat(result);
            System.out.println(jsonresult);
            return jsonresult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON格式化
     * @param json
     * @return
     */
    public static String toPrettyFormat(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }

    public static void main(String[] args) {
        FaceMatch.faceMatch();
    }
}