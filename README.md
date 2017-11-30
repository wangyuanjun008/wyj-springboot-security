## 项目说明
# wyj-springboot-security
wyj-springboot-security是一个前后端分离的基础权限管理后台，前端：vue+element+webpack，后端：spring-boot+mybatis
基于 https://github.com/wangyuanjun008/wyj-parent.git (个人编写的另一套基础权限管理平台)开发的
已经完成基于角色的操作权限管理，对前后端进行封装，可快速实现CRUD的开发。项目采用Maven多模块构建，方便按需求进行模块化扩展。

### 传送门
- 前端地址 ：https://github.com/wangyuanjun008/wyj-vue-security.git

### 项目介绍
- 一个轻量级的Java快速开发框架，能快速开发项目并交付（规划后期不定时发布更新）
- 友好的代码结构及注释，便于阅读及二次开发，命名规范和工程分层规约参考阿里巴巴JAVA开发规范
- 前后端开发封装，快速实现CRUD开发
- 基于角色的权限管理，细分到按钮权限（规划将支持数据权限）
- 基于Maven模块化开发，可快速扩展个性化业务模块

### 技术方案
- 核心框架：SpringBoot
- ORM框架：Mybatis
- 缓存技术：reids
- 安全框架：Shiro
- JS框架：vue.js
- 前端组件库：Element UI
- 前端工具：webpack

### 命名规范（参考阿里巴巴Java开发手册）
- 获取单个对象的方法用 get 做前缀
- 获取多个对象的方法用 list 做前缀
- 获取统计值的方法用 count 做前缀
- 插入的方法用 save(推荐) 或 insert 做前缀
- 删除的方法用 remove(推荐) 或 delete 做前缀
- 修改的方法用 update 做前缀