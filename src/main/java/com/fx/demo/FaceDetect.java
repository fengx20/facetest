package com.fx.demo;

import com.fx.demo.HttpUtil;
import com.fx.demo.GsonUtils;
import com.google.gson.*;

import java.util.*;

/**
 * 人脸检测与属性分析
 */
public class FaceDetect {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String faceDetect() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", "http://n.sinaimg.cn/edu/transform/413/w550h663/20180910/FxjJ-hivtsyk9387969.jpg");
            map.put("face_field", "age,beauty,expression,face_shape");
            map.put("image_type", "URL");
            map.put("max_user_num", 10);

            String param = GsonUtils.toJson(map);

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
        FaceDetect.faceDetect();
    }
}