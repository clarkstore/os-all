/*
 *
 *  * Copyright (c) 2021 os-parent Authors. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.onestop.common.azure.storage.util;

import cn.hutool.core.date.DateUtil;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.EnumSet;

/**
 * AzureStorage工具类
 *
 * @author Clark
 * @version 2021-03-29
 */
@Slf4j
public class OsAzureStorageUtils {
    /**
     * 链接串
     */
    private String connectionString;
    /**
     * 容器名称必须为小写
     */
    private String containerName;
    /**
     * token超时时长
     */
    private int expireTimeInMinutes;
    /**
     * 容器
     */
    private CloudBlobContainer container;

    public OsAzureStorageUtils(String connectionString, String containerName, int expireTimeInMinutes) {
        this.connectionString = connectionString;
        this.containerName = containerName;
        this.expireTimeInMinutes = expireTimeInMinutes;
    }

    /**
     * 创建blob容器
     * 容器名称必须为小写
     */
    @PostConstruct
    public void createBlobClient() {
        try {
            CloudStorageAccount account = CloudStorageAccount.parse(connectionString);
            CloudBlobClient serviceClient = account.createCloudBlobClient();

            // Container name must be lower case.容器名称必须为小写
            this.container = serviceClient.getContainerReference(containerName);
            this.container.createIfNotExists();
        } catch (StorageException se) {
            log.error("============createBlobClient==============");
            log.error(se.getMessage());
        } catch (Exception e) {
            log.error("============createBlobClient==============");
            log.error(e.getMessage());
        }
    }

    /**
     * 取得Blob容器
     * @return Blob容器
     */
    public CloudBlobContainer getContainer() {
        return this.container;
    }

    /**
     * 上传文件
     *
     * @param blobName   容器存储中的全路径：如：202103/abc.jpg
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
        } catch (StorageException se) {
            log.error("============upload==============");
            log.error(se.getMessage());
        } catch (Exception e) {
            log.error("============upload==============");
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * 下载文件
     *
     * @param blobName   容器存储中的全路径：如：202103/abc.jpg
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
        } catch (StorageException se) {
            log.error("============download==============");
            log.error(se.getMessage());
        } catch (Exception e) {
            log.error("============download==============");
            log.error(e.getMessage());
        }
    }

    /**
     * 取得SaS Token
     * 默认token超时时长120分钟
     * @return SaS Token
     */
    public String getContainerSaSToken() {
        return this.getContainerSaSToken(this.expireTimeInMinutes);
    }

    /**
     * 取得SaS Token
     * @param expireTimeInMinutes sasToken超时分钟
     * @return SaS Token
     */
    public String getContainerSaSToken(int expireTimeInMinutes) {
        try {
            SharedAccessBlobPolicy sabp = createSharedAccessPolicy(
                    EnumSet.of(SharedAccessBlobPermissions.READ, SharedAccessBlobPermissions.LIST), expireTimeInMinutes);
            BlobContainerPermissions perms = new BlobContainerPermissions();
            perms.getSharedAccessPolicies().put("readlist", sabp);
            this.container.uploadPermissions(perms);

            String containerReadListSas = this.container.generateSharedAccessSignature(sabp, null);
            return containerReadListSas;
        } catch (StorageException se) {
            log.error("============getContainerSaSToken==============");
            log.error(se.getMessage());
        } catch (Exception e) {
            log.error("============getContainerSaSToken==============");
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * createSharedAccessPolicy
     * @param sap EnumSet<SharedAccessBlobPermissions>
     * @param expireTimeInMinutes sasToken超时分钟
     * @return
     */
    private SharedAccessBlobPolicy createSharedAccessPolicy(EnumSet<SharedAccessBlobPermissions> sap,
                                                            int expireTimeInMinutes) {
        SharedAccessBlobPolicy policy = new SharedAccessBlobPolicy();
        policy.setPermissions(sap);
        policy.setSharedAccessExpiryTime(DateUtil.offsetMinute(DateUtil.date(), expireTimeInMinutes));
        return policy;
    }
}
