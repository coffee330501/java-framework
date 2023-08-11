package io.github.coffee330501.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import io.github.coffee330501.common.config.OssConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class OssUtil {
    static OssConfig ossConfig;


    @Autowired
    public OssUtil(OssConfig ossConfig) {
        OssUtil.ossConfig = ossConfig;
    }

    /**
     * 上传OSS
     *
     * @param inputStream 输入流
     * @param filename    OSS文件名称
     * @return 文件名称
     */
    public static String upload(InputStream inputStream, String filename) {
        OSS ossClient = OssClient.INSTANCE;
        ossClient.putObject(ossConfig.getBucket(), filename, inputStream);
        return filename;
    }

    /**
     * 上传OSS
     *
     * @param dir         文件夹
     * @param inputStream 输入流
     * @param filename    OSS文件名称
     * @return 文件名称
     */
    public static String upload(InputStream inputStream, String dir, String filename) {
        // OSS文件夹不以/开头
        if (dir.startsWith("/")) dir = dir.replace("/", "");


        OSS ossClient = OssClient.INSTANCE;
        ossClient.putObject(ossConfig.getBucket(), filename, inputStream);
        return filename;
    }


    private static class OssClient {
        private static final OSS INSTANCE = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
    }
}