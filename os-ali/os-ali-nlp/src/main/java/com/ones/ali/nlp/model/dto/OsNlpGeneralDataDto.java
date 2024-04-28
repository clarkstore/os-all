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

package com.ones.ali.nlp.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

import java.util.List;

/**
 * 分词请求Dto
 * @author Clark
 * @version 2022-02-25
 */
@Builder
@Getter
@Setter
@ToString
public class OsNlpGeneralDataDto {
    @Tolerate
    public OsNlpGeneralDataDto() {
    }

    /**
     * 需要分析的文本，最大长度1024个字符
     */
    private List<ResultDto> result;
    /**
     * 0：大粒度，1：中粒度（默认），2：小粒度
     */
    private boolean success;

    @Getter
    @Setter
    public class ResultDto {
        /**
         * 需要分析的文本，最大长度1024个字符
         */
        private String id;
        /**
         * 需要分析的文本，最大长度1024个字符
         */
        private String word;
        /**
         * 需要分析的文本，最大长度1024个字符
         */
        private List<String> tags;
    }
}
