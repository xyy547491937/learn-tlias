package com.itheima.utils;

import cn.yunlingfly.qiniuspringbootstarter.infra.config.QiNiuProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Component
public class QiniuCloud {
//    @Value("${qiniu.bucket-name}")
//    private String bucket;//xxxxxxxx换成七牛云的空间名称
//    @Value("${qiniu.access-key}")
//    private String accessKey;
//    @Value("${qiniu.secret-key}")
//    private String secretKey ;
    //指定需要上传的文件路径
    @Autowired
    private QiNiuProperties qiNiuProperties;
    public String upload(MultipartFile image) {

        // 依赖注入
        String bucket = qiNiuProperties.getBucketName();
        String accessKey = qiNiuProperties.getAccessKey();
        String secretKey = qiNiuProperties.getSecretKey();

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region1());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
//        String accessKey = "xxxxxxxx";//xxxxxxxx换成七牛云的AK密钥
//        String secretKey = "xxxxxxxx";//xxxxxxxx换成七牛云的SK密钥
        String localFilePath = image.getOriginalFilename();
        log.info("原始路勁{}", localFilePath);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = UUID.randomUUID().toString();
        System.out.println(key);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
            String str = "http://s4pyscjcq.hb-bkt.clouddn.com/" + key;
            System.out.println(response);

            return str;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            return "";
        }
    }
}
