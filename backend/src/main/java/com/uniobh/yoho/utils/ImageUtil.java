package com.uniobh.yoho.utils;

import com.uniobh.yoho.enums.FileTypeEnum;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件（图片）工具类
 */
public class ImageUtil {
    public static final String SERVER_PATH = "http://localhost:8081";   // 服务器地址

    /**
     * 上传图片
     * @param files
     * @param fileTypeEnum
     * @param uId 用户id
     * @return 上传成功返回保存路径名构成的集合
     */
    public static List<String> upload(MultipartFile[] files, FileTypeEnum fileTypeEnum, Integer uId) {
        if(files.length == 0 || files == null) return null;
        else {
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                try {
                    if (files[i].isEmpty()) return null; // 上传文件不存在
                    String s = files[i].getOriginalFilename();  // 获取文件原始的名称
                    String[] split = s.split("\\.");  // 按 。 切割字符串
                    String suffix = split[split.length - 1];    // 获取后缀名
                    // 只支持 jpg、png、jpeg、gif、webp格式的图片
                    if ("jpg".equals(suffix) || "png".equals(suffix) || "jpeg".equals(suffix) || "gif".equals(suffix) || "webp".equals(suffix)) {
                        String saveFileName = UUID.randomUUID().toString() + "-" + s;  // 设置保存文件的文件名
                        // 获取当前服务器/target/classes/的路径
                        String serverPath = ResourceUtils.getURL("classpath:").getPath();
                        String dirPath = serverPath + fileTypeEnum.getPath() + uId.toString() + "/";  // 保存图片的文件目录
                        File f = new File(dirPath);
                        if (!f.exists()) f.mkdirs(); // 如果保存文件的目录不存在则创建目录
                        files[i].transferTo(new File(dirPath + saveFileName));  // 上传文件
                        strings.add(fileTypeEnum.getPath() + uId.toString() + "/" + saveFileName);
                    } else return null;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return strings;
        }
    }
}
