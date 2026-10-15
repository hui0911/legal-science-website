# 法律科普网站 (Legal Website)

基于 Java 的前后端分离法律科普与咨询预约平台，实现管理员、律师、用户三角色权限控制。

## 项目结构

- `StatuteUser/`：用户端（Vue 前端，含资讯浏览、视频科普、律师查询、在线预约、AI咨询）
- `StatuteManage/`：管理员端和律师端（Vue 前端，管理员端含资讯/视频/律师/预约/留言/轮播图/分类/评论全量管理，律师端含预约/留言/咨询管理）
- `Statute/`：后端服务（Spring Boot，提供 RESTful API 与多角色权限校验）
- `admin.sql`：数据库脚本

## 技术栈

- **前端**：Vue-CLI、Vuex（三角色登录态管理）、Axios（请求拦截器封装）、Vue Router（动态路由/权限控制）
- **后端**：Java、Spring Boot、MySQL、JWT（角色鉴权）
- **组件**：抽象轮播图、视频播放器、资讯列表、律师卡片、预约表单等 10+ 可复用组件
- **工程化**：Webpack 打包优化、环境变量配置、Git

## 核心功能模块

### 管理员 (StatuteManage)
预约信息、资讯信息、科普视频管理、留言信息、律师信息、轮播图、法律分类、评论管理

### 律师 (StatuteManage 角色分支)
登录注册、我的留言、我的预约、个人信息管理

### 用户 (StatuteUser)
登录注册、首页、法律资讯、科普视频、律师事务所、个人中心、在线预约

## 技术亮点

- 封装 Axios 统一请求拦截器，集中处理 token 注入与登录态失效跳转
- 基于 Vuex 统一管理三角色登录态，实现动态路由与按钮级权限控制
- 抽象 10+ 通用组件，覆盖首页、资讯、视频、律所四大模块
- 后端基于角色做接口级拦截，前端路由守卫 + 按钮 v-if 双重控制

## 快速启动

### 数据库
1. 导入 `admin.sql` 到 MySQL
2. 修改 Statute/src/main/resources/application.yml 数据库连接配置
### 后端
```bash
cd Statute
mvn spring-boot:run
```
### 前端
**用户端：**
cd StatuteUser
npm install
npm run dev
**管理端：**
cd StatuteManage
npm install
npm run dev
