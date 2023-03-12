# Database-project

#### 介绍
数据库课设源程序

#### 软件架构
IDEA 2021.3.3
MySQL 5.7.26
1. 设计任务：设计并实现单位人事管理系统
2. 设计要求
（1）信息要求：单位人事管理系统中应包括部门信息、员工信息、职员家庭信息、人员调动、职工工资信息等。
（2）功能要求：单位人事管理系统应实现部门信息管理、员工信息管理、奖惩信息管理、家庭成员信息管理，基本信息查询等功能。

#### 安装教程

无

#### 使用说明

数据库中删除了配置信息

**课设要求**

数据库中建立了一个职工基本信息的视图，用于反映每个职工的基本信息，包括职工编号、职工姓名、部门名称、工种和职务。它是从职工信息表（emp）和部门信息表（dept）导出的，定义如下：

![image-20230312210534993](https://gitee.com/lanluoying/pictures/raw/master/image-20230312210534993.png)

数据库中insert_trigger该触发器定义在职工信息表（emp）上

![](https://gitee.com/lanluoying/pictures/raw/master/clip_image001.png)

触发器update_trigger该触发器定义在职工信息表（emp）上。

![image-20230312210419465](https://gitee.com/lanluoying/pictures/raw/master/image-20230312210419465.png)

触发器delete_trigger该触发器定义在职工信息表（emp）上。

![image-20230312210513211](https://gitee.com/lanluoying/pictures/raw/master/image-20230312210513211.png)

主要界面：

![image-20230312210858097](https://gitee.com/lanluoying/pictures/raw/master/image-20230312210858097.png)

![image-20230312211303188](https://gitee.com/lanluoying/pictures/raw/master/image-20230312211303188.png)

![image-20230312211330303](https://gitee.com/lanluoying/pictures/raw/master/image-20230312211330303.png)

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
