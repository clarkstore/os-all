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

/**
 * 密钥：必须16位
 */
var keyStr = "0123456789ABCDEF";
var key = CryptoJS.enc.Utf8.parse(keyStr);

/**
 * 加密（需要先加载crypto-js.js文件）
 * @param content
 * @returns {*}
 */
function encrypt(content){
  let srcs = CryptoJS.enc.Utf8.parse(content);
  let encrypted = CryptoJS.AES.encrypt(srcs, key, {mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7});
  return encrypted.toString();
}

/**
 * 解密
 * @param content
 * @returns {*}
 */
function decrypt(content){
  let decrypt = CryptoJS.AES.decrypt(content, key, {mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7});
  return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}