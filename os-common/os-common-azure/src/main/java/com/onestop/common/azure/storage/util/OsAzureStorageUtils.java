package com.onestop.common.azure.storage.util;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * AzureStorage工具类
 *
 * @author Clark
 * @version 2021-03-29
 */
@Slf4j
public class OsAzureStorageUtils {
    private String connectionString;
    /**
     * 容器名称必须为小写
     */
    private String containerName;
    /**
     * 容器
     */
    private CloudBlobContainer container;

    public OsAzureStorageUtils(String connectionString, String containerName) {
        this.connectionString = connectionString;
        this.containerName = containerName;
    }

    /**
     * 创建blob容器
     * 容器名称必须为小写
     */
    public void createCloudBlobClient() {
        try {
            CloudStorageAccount account = CloudStorageAccount.parse(connectionString);
            CloudBlobClient serviceClient = account.createCloudBlobClient();

            // Container name must be lower case.容器名称必须为小写
            this.container = serviceClient.getContainerReference(containerName);
            this.container.createIfNotExists();
        }
        catch (StorageException se) {
            log.error("============StorageException==============");
            log.error(se.getMessage());
        }
        catch (Exception e) {
            log.error("============Exception==============");
            log.error(e.getMessage());
        }
    }

    /**
     * 上传文件
     * @param blobName 容器存储中的全路径：如：202103/abc.jpg
     * @param fileStream InputStream
     * @return boolean
     */
    public boolean upload(String blobName, InputStream fileStream) {
        try {
            CloudBlockBlob blob = this.container.getBlockBlobReference(blobName);
            blob.upload(fileStream, -1);
            log.debug("============upload==============");
            log.debug("blobName upload: " + blob.exists());
            return blob.exists();
        }
        catch (StorageException se) {
            log.error("============StorageException==============");
            log.error(se.getMessage());
        }
        catch (Exception e) {
            log.error("============Exception==============");
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * 下载文件
     * @param blobName 容器存储中的全路径：如：202103/abc.jpg
     * @param fileStream OutputStream
     */
    public void download(String blobName, OutputStream fileStream) {
        try {
            CloudBlockBlob blob = this.container.getBlockBlobReference(blobName);
            log.debug("============download==============");
            log.debug("blobName exists: " + blob.exists());
            if (blob.exists()) {
                blob.download(fileStream);
            }
        }
        catch (StorageException se) {
            log.error("============StorageException==============");
            log.error(se.getMessage());
        }
        catch (Exception e) {
            log.error("============Exception==============");
            log.error(e.getMessage());
        }
    }
}
