[![](logo.png)](https://*.png "一站")
----
<p align="center">
     <a target="_blank" href="https://www.apache.org/licenses/LICENSE-2.0">
        <img alt="" src="https://img.shields.io/badge/License-Apache--2.0-brightgreen.svg"/>
     </a>
     <a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
        <img alt="" src="https://img.shields.io/badge/JDK-1.8+-green.svg"/>
     </a>
     <a target="_blank" href="https://maven-badges.herokuapp.com/maven-central/com.github.clarkstore/os-parent">
        <img alt="" src="https://maven-badges.herokuapp.com/maven-central/com.github.clarkstore/os-parent/badge.svg"/>
     </a> 
     <a target="_blank" href="https://gitee.com/clarkstore/os-parent/guide/donate">
        <img alt="" src="https://img.shields.io/badge/OneStop%20Author-Clark-ff69b4.svg"/>
     </a>
</p>

## OneStop工具平台
### 平台简介：
##### 本项目构建初衷是结合近年若干项目使用的技术与经验，整理可为类似项目提供业务快速实现的自身共性的定制工具平台为目标，纯属个人实践积累总结与偏好。非基础工具包适合所有需求场景。
#### 温馨提示：使用前请确认业务场景与技术实现是否匹配。
1. 使用前认真阅读各模块下:README.md
---    
1. os-starter模块【自定义starter不要修改，供业务模块引入】
    - os-azure
    - os-core
    - os-task
    - os-wxmini
    - os-wxmp
2. 公共模块
    - 核心模块
        * 异常类：业务异常/支付异常
        * 常用工具类：邮件/Token/redis/rest客户端
    - 定时任务模块
    - Azure模块
        * azure storage工具类
    - 安全模块：api路径访问控制
3. 微信模块【基于weixin-java开源项目封装】
    - 企业微信
    - 小程序
    - 服务号
    - 微信支付

---
[![JetBrains IDEA](jetbrains.png)](https://jb.gg/OpenSource)
[感谢JetBrains IDEA对开源项目的支持](https://jb.gg/OpenSource)
