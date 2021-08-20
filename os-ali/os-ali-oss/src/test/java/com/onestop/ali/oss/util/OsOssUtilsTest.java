package com.onestop.ali.oss.util;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author Clark
 * @version 2021/8/20
 */
public class OsOssUtilsTest {
    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    public static String accessKeyId = "LTAI5tF5Cp6aS7MRYQg5SYQF";
    public static String accessKeySecret = "93JNgJkPOnvUp20KaMb1uunl74XvLu";

    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
    // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
    // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
    // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
    public static String endpoint = "oss-cn-beijing.aliyuncs.com";

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    public static String bucketName = "onduty-dev";

    public static void main(String[] args) {
        try {
            OsOssUtils ossUtils = new OsOssUtils(accessKeyId, accessKeySecret, endpoint, bucketName);
            File file = new File("d:\\abc.txt");
//            ossUtils.upload("abc/1.txt", new FileInputStream(file));

//            ossUtils.download("abc/1.txt", "d:\\1.txt");

//            ossUtils.deleteObject("abc");

            ossUtils.deleteObjectList("abc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void upload() {
    }

    @Test
    void download() {
    }

    @Test
    void deleteObject() {
    }
}