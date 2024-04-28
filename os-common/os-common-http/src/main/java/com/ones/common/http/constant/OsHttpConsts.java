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

package com.ones.common.http.constant;

/**
 * http常量
 * @author Clark
 * @version 2023-03-14
 */
public interface OsHttpConsts {
    /**
     * 限流异常code
     */
    int BIZ_EXCEPTION_CODE_ACCESS_LIMIT = 501;
    /**
     * 重复提交code
     */
    int BIZ_EXCEPTION_CODE_REPEAT_SUBMIT = 502;
}
