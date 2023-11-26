package com.itheima.mapper;

import com.itheima.pojo.Result;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.util.UUID;

/**
 * 文件上传
 */
public class Demo {
    public static void main(String[] args) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region1());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
//        String accessKey = "xxxxxxxx";//xxxxxxxx换成七牛云的AK密钥
//        String secretKey = "xxxxxxxx";//xxxxxxxx换成七牛云的SK密钥
        String bucket = "web-tliass";//xxxxxxxx换成七牛云的空间名称

        String accessKey = "c85fQObE2fr9hP0jVerIMXFqJ36mh6QM7tntEeM_";
        String secretKey = "_CLSqhPo-w9Leoo-O-urkyGjZCK4WjMG6DCZrkOI";
        //指定需要上传的文件路径
        String localFilePath = "C:\\Users\\Administrator\\Desktop\\RE4wEad.jpg";
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
           String str =  "http://s4pyscjcq.hb-bkt.clouddn.com/"+key;
            System.out.println(response);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }
}
