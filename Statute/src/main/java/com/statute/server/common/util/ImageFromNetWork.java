package com.statute.server.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ImageFromNetWork {

    /**
     * 下载图片到本地
     * @param imgUrl  需要下载的图片链接
     * @param savePath 保存到本地的位置 示例：/Users/jinx/Downloads/1.jpg
     */
    public static void writeImageToDisk(String imgUrl, String savePath) {
        writeImageToDisk(getImageFromNetByUrl(imgUrl), savePath);
    }

    /**
     * 将获取的字节数组保存为文件写入硬盘
     *
     * @param data
     * @param fileName
     */
    public static void writeImageToDisk(byte[] data, String fileName) {
        try {
            File file = new File(fileName); // 本地目录
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
                file.createNewFile();
            }
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(data);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取远程http地址视图片
     *
     * @param strUrl
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            byte[] btData = readInputStream(inStream);
            return btData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取流
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}
