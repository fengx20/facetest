package com.fx.demo;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author fengda
 * @date 2019/10/30 14:56
 */
public class ImageBase64Util {

    /**
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     * @param imgStr base64编码字符串
     * @param path 图片路径-具体到文件
     * @return
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null)
        return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
        // 解密
        byte[] b = decoder.decodeBuffer(imgStr);
        // 处理数据
        for (int i = 0; i < b.length; ++i) {
        if (b[i] < 0) {
        b[i] += 256;
        }
        }
        OutputStream out = new FileOutputStream(path);
        out.write(b);
        out.flush();
        out.close();
        return true;
        } catch (Exception e) {
        return false;
        }
    }

    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 示例
     */
    public static void main(String[] args) {
        String strImg = getImageStr("C:/Users/Administrator/Desktop/fx.jpg");
        System.out.println(strImg);
        //generateImage(strImg, "E:/86619-107.jpg");
    }

}
