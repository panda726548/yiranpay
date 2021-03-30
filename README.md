## 聚合支付平台简介
聚合支付是一种第四方支付服务。简而言之，第三方支付提供的是资金清算通道，而聚合支付提供的是支付基础之上的多种衍生服务。聚合支付服务”不具备支付牌照，而是通过聚合多种第三方支付平台、合作银行及其他服务商接口等支付工具的综合支付服务。聚合支付不进行资金清算，但能够根据商户的需求进行个性化定制，形成支付通道资源优势互补，具有中立性、灵活性、便捷性等特点。目前已经对接微信，支付宝，银联支付等渠道。
## 技术交流群
- QQ群：909552379 【备注：依然聚合支付】
- 微信号:love520yr1314xm 加微信【备注：依然聚合支付】拉入微信交流群，联系作者
- yiranpay-paychannel、yiranpay-payorder QQ群下载
## 在线体验
```
后台：用户名：test  密码:123456
商户：用户名：498617606@qq.com  密码：123456
```
- 后台地址：http://yiranpay.xyz
- 商户站地址：http://merchant.yiranpay.xyz
- 收银台：http://cashier.yiranpay.xyz/demo
- 项目信息：http://crapapi.yiranpay.xyz/static/157734276529007000001/157746064441509000022-articleList--1.html
## 核心技术
- 核心框架：Spring Boot
- 权限框架：Apache Shiro
- 模板引擎：thymeleaf
- 持久层框架：MyBatis
- 数据库连接池：Alibaba Druid
- 缓存框架：Redis、EhCache
- 日志管理：LogBack
- 工具类：Apache Commons、HuTools
- 视图框架：Spring MVC
- 定时器：Quartz
- 数据库连接池：Druid
- 日志管理：logback
- 页面交互：基于hplus和inspinia
- 下拉框：bootstrap-select
- 文件上传：Bootstrap File Input
- 通讯技术：webSocket
- 数据库：MySQL
- 分布式文件系统：FastDFS
- 持续集成：Jenkins
## 系统结构


```
yiranpay //聚合支付
├── yiranpay-admin //后台管理模块 单独部署
|
├── yiranpay-amqp //MQ消息队列模块
|
├── yiranpay-common //公共基础模块
|
├── yiran-framework //系统核心模块
|
├── yiranpay-gateway //支付网关
|
├── yiranpay-generator //代码生成模块
|
├── yiranpay-member //会员管理模块
|
├── yiranpay-message //消息模块
|
├── yiranpay-paychannel //支付渠道路由模块
|
├── yiranpay-payorder //支付核心模块
|
├── yiranpay-quartz //定时任务模块
|
├── yiranpay-reconciliation //交易对账模块
|
├── yiran-system //系统管理模块

channel-pay //支付渠道（独立部署）

merchant //商户后台管理系统（独立部署）

cashier //收银台（测试，独立部署）

```
```
2020.12.28 更新版本
    1.新增添加商户生成收款二维码
    2.新增H5手机支付收银台，支持微信和支付宝支付

```
```
2020.11.24 更新版本
    1.新增银行卡签约接口
    2.优化一些细节，修复bug
```
## 新增H5手机支付收银台
备注：测试金额请小额支付（0.01），不要大额支付，测试金额无法退还
!![输入图片说明](https://images.gitee.com/uploads/images/2020/1229/003529_40bf232f_928853.jpeg "rBEADF_oVhqAPtABAADDwE8Jpyw543.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2020/1229/003723_972cf895_928853.jpeg "微信图片_20201229003503.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2020/1229/003801_c4c1ef31_928853.jpeg "微信图片_20201229003519.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2020/1229/003832_5bf901bf_928853.jpeg "微信图片_20201229003513.jpg")


## 架构图
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234147_f7bcb04e_928853.png "40.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234207_fd42637b_928853.png "41.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004403_f6a6b12e_928853.png "q1.png")
## 支付流程
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234423_1160a210_928853.png "50.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234436_d6294059_928853.png "51.png")
## 对账流程
![输入图片说明](https://images.gitee.com/uploads/images/2019/1128/145153_8a410e28_928853.png "屏幕截图.png")
## 模块功能说明
1.  **用户管理** ：用户是系统操作者，该功能主要完成系统用户配置。对各个基本的用户增删改查，导出excel表格，批量删除。
1.  **角色管理** ：角色菜单权限分配、设置角色按机构进行分配菜单权限和增删改查权限。
1.  **菜单管理** ：N级别自定义菜单，自定义菜单图标，业务菜单和系统菜单分离，菜单状态显示隐藏，配置系统菜单，操作权限，按钮权限标识等。
1.  **部门管理** ：配置系统组织机构（公司、部门、小组），树结构展现。
1.  **岗位管理** ：配置系统用户所属担任职务。
1.  **字典管理** ：对系统中经常使用的一些较为固定的数据进行维护。
1.  **参数管理** ：对系统动态配置常用参数。
1.  **通知公告** ：系统通知公告信息发布维护。
1.  **操作日志** ：系统正常操作日志记录和查询；系统异常信息日志记录1. 查询。
1.  **登录日志** ：系统登录日志记录查询包含登录异常。
1.  **在线用户** ：当前系统中活跃用户状态监控。可强制用户下线。
1.  **定时任务** ：在线（添加、修改、删除)任务调度包含执行结果日志。启动、暂停、执行定时任务操作。
1.  **数据监控** ：监视当期系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。
1.  **服务监控** ：监控服务器相关信息。
1.  **表单构建** ：拖拽式快速构建表单，组建元素丰富，有富文本、上传控件、下拉框等等
1.  **代码生成** ：前后端代码的生成（java、html、xml、sql)支持CRUD下载 。
1.  **系统接口** ：根据业务代码自动生成相关的api接口文档。开发人员只需要加好注解自动生成API接口文档。
1.  **UES加密** ：系统加密模块，对敏感信息加密，提供加密解密方法。
1.  **渠道管理** ：支付渠道管理，包括资金渠道配置（支付渠道），目标机构配置，API结果码设置，统一结果码配置...
1.  **平台订单渠道** ：平台支付订单，所有的交易都走支付核心，所有的交易都记录在渠道订单中。
1.  **综合管理** ：联合查询，根据不同的条件查询订单支付结果，机构订单结构查询，根据机构订单号（提供给第三方的订单号）从第三方支付或者银行查询支付结果。
1.  **支付网关**：支付网关是直接对接业务系统的接口，它本身并不执行任何支付相关的业务逻辑。它将支付产品接口中和业务无关的功能提取出来，在这里统一实现。这样在具体产品接口中，就无需考虑这些和业务无关的逻辑。支付网关设计还和对外的接口参数有关。商户接口配置、接口权限、IP白名单、商户秘钥管理
1.  **交易对账管理** ：每天定时对前一天平台的交易订单和银行方（例如：微信、支付宝...）订单进行匹配校验，校验订单状态、手续费、交易金额等。
1.  **平台订单渠道** ：平台支付订单，所有的交易都走支付核心，所有的交易都记录在渠道订单中。
1.  **MQ管理** ：配置MQ消息，记录发送的MQ消息信息，按照一定的规则处理发送失败的消息数据。
1.  **支付产品** ：支付产品模块是按照支付场景来为业务方提供支付服务。这个模块一般位于支付网关之后，支付渠道之前。 它根据支付能力将不同的支付渠道封装成统一的接口，通过支付网关来对外提供服务。所以，从微服务的角度，支付产品本身也是一个代理模式的微服务，它透过支付网关响应业务方请求， 进行一些统一处理后，分发到不同的支付渠道去执行，最后将执行结果做处理后，通过支付网关再回传给业务方。
1.  **会员管理**: 会员管理分为内部客户与外部客户两种，内部客户是指集团内部的公司或个人，外部客户则是使用平台服务且与集团无关的外部公司或者个人。
    内部客户：集团内部的公司或个人。以阿里巴巴集团为例，不同业务线包含众多子公司，根据集团战略需要统一接入支付宝，这种情况下内部公司的接入在支付宝系统时一般会定义为内部客户，此类客户和外部客户之间会有一定的差异化服务，在一些风险、服务以及产品层面均会作出一定的调整；
    外部客户：使用平台服务且与集团无关的外部公司或者个人。以支付宝举例，喜马拉雅接入了支付宝，对于支付宝来说即外部客户，因为接入了支付宝所以可以使用支付宝的部分功能。
## 运营后台演示图
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234655_a73560e0_928853.png "1.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234706_bbb188a8_928853.png "2.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234718_528443de_928853.png "3.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234730_465659cc_928853.png "4.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234741_9841a8bd_928853.png "5.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234752_437fe133_928853.png "6.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234804_5ad90f97_928853.png "7.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234815_e5aeaa9e_928853.png "8.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234830_ff0c8b94_928853.png "9.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234840_a08d3819_928853.png "10.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234857_d967a665_928853.png "12.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234909_88be7930_928853.png "13.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234920_b0bf58d4_928853.png "14.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234933_de93f80e_928853.png "15.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235002_afd4b45b_928853.png "16.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/234950_e68b7454_928853.png "17.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235019_0b3932d3_928853.png "18.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235033_1ed1a69e_928853.png "19.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235046_8818b964_928853.png "20.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235113_ee064a7b_928853.png "21.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235102_63a2f474_928853.png "22.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235128_98a78d34_928853.png "23.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235141_8b2d8216_928853.png "24.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235154_fda9c833_928853.png "25.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235212_63f04fce_928853.png "26.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0530/235225_4fa75115_928853.png "27.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004429_6a44a210_928853.png "28.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004443_e3572520_928853.png "29.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004456_15a986b6_928853.png "30.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004522_2fc9769d_928853.png "31.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004544_3f78b917_928853.png "32.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004604_f356e4fa_928853.png "33.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004640_fd700e61_928853.png "34.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004620_86a2ee58_928853.png "35.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004700_c081e60d_928853.png "36.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/004855_aaf2da79_928853.png "60.png")
![银行卡信息](https://www.showdoc.com.cn/server/api/attachment/visitfile/sign/8534a5bde59e549674d309675552d13d)
![签约信息](https://www.showdoc.com.cn/server/api/attachment/visitfile/sign/33420a04d3e1c28fba630ed3ebaf38b6)

## 商户后台演示图
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005022_514233de_928853.png "m1.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005044_5efc321b_928853.png "m2.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005103_9b7ad8f7_928853.png "m3.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005118_be05f033_928853.png "m4.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005135_e9034711_928853.png "m5.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005150_f55f1c22_928853.png "m6.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005205_9df89e34_928853.png "m7.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005222_fc6d7837_928853.png "m8.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005242_f729c504_928853.png "m9.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005258_eb3dbefa_928853.png "m10.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005314_30f82f62_928853.png "m11.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005328_cad0f90c_928853.png "m13.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005344_16d83710_928853.png "m14.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005402_8a9a4183_928853.png "m15.png")

## 收银台演示图
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/005908_f44053e5_928853.png "c1.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/010047_e0548484_928853.jpeg "c2.jpg")

