/*
 *
 *  * Copyright (C) 2021 ClarkChang
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *         http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.ones.ali.nlp.constant;

/**
 * 消息常量类
 *
 * @author Clark
 * @version 2021-11-10
 */
public interface OsNlpConsts {
    /**
     * RegionId
     */
    String REGION_ID = "cn-hangzhou";
    /**
     * SysEndpoint
     */
    String SYS_ENDPOINT = "alinlp.cn-hangzhou.aliyuncs.com";
    /**
     * ServiceCode
     */
    String SERVICE_CODE = "alinlp";
    /**
     * 中文分词基础版Action
     */
    String ACTION_GET_WS_CH_GENERAL = "GetWsChGeneral";
    /**
     * 中文分词高级版Action
     */
    String ACTION_GET_WS_CUSTOMIZED_CH_GENERAL = "GetWsCustomizedChGeneral";
    /**
     * 分词器类型，默认为GENERAL_CHN
     */
    String TOKENIZER_ID_GENERAL_CHN = "GENERAL_CHN";
}
