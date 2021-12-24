# `SoulKnight`

**仓库说明：**

本小组在Github和Gitee双远程仓库更新，在Gitee中可以看到从0开始的所有提交记录。

Gitee仓库地址：https://gitee.com/liaojingpu/SoulKnight


Github仓库地址：https://github.com/NPU-Java-Web/SoulKnight


dev为功能最多最全的开发分支，master为经过测试排除bug后的稳定上线版本，成员个人开发分支命名格式为dev-name。
开发成员单元测试完成后合并到dev分支，每个迭代阶段结束经过测试的dev分支合并到master分支。
分支：廖菁璞 dev-ljp
    翁宇哲 dev-wyz
    孟辰林 dev-test and dev-test2

## 文件树

```shell
SoulKnight
│  .gitignore
│  pom.xml
│  README.md
│              
├─client #客户端
│  │  pom.xml
│  │  
│  └─src
│     ├─main
│     │  ├─java
│     │  │  └─org
│     │  │      └─example
│     │  │          └─client
│     │  │              │  ClientApplication.java  # 客户端启动类
│     │  │              │  ClientCore.java  # 客户端菜单核心类
│     │  │              │  GameStartCore.java  # 客户端游戏核心类
│     │  │              │  
│     │  │              ├─calculate  # 客户端数据处理及通讯 Conroller层
│     │  │              │  │  CalculationMain.java
│     │  │              │  ├─communication  # 与服务端通讯类
│     │  │              │  └─service  # 本地数据处理
│     │  │              ├─display  # View层
│     │  │              └─Input  # 输入监听层
│     │  │                      GameMouseListener.java
│     │  │                      KeyEventListener.java
│     │  │                      
│     │  └─resources  # 客户端图片音乐等静态资源
│     │              
│     └─test  # 客户端单元测试
│                          
├─common
│  │  pom.xml
│  │  
│  └─src
│     └─main
│         └─java
│             └─org
│                 └─example
│                     └─common
│                         ├─config  # 客户端静态配置
│                         │  │  GameConfig.java
│                         │  │  
│                         │  └─level  # 地图配置
│                         ├─keyListener  # 按键监听类
│                         ├─model  # 实体类
│                         │  ├─animation  # 特效实体类  
│                         │  ├─bullet  # 子弹实体类
│                         │  ├─monster # 怪物实体类
│                         │  └─player  # 英雄实体类 
│                         └─protocol  # 通信格式
│                                 
└─server
    │  pom.xml
    │  
    └─src
       ├─main
       │  └─java
       │      └─org
       │          └─example
       │              └─server
       │                  │  ServerApplication.java
       │                  │  ServerCore.java
       │                  │  ThreadConfig.java
       │                  │  
       │                  ├─dao  # 服务端DAO层
       │                  ├─handler
       │                  ├─service  # 服务端业务逻辑Service层
       │                  ├─thread
       │                  └─util
       │                          
       └─test  # 服务端单元测试
```

## 构建说明

本项目用使用`Mavean`构建项目，采用IDEA集成开发环境构建，整体结构分为客户端，服务端。

## 运行说明

### 1. 本项目使用`Redis`缓存游戏数据，如尚未安装请先安装Redis。

### 2. 用IDE打开项目

（1）修改`server`模块中的`ThreadConfig`类中的Redis连接配置

（2）先运行服务端：运行`service`模块中的`ServerApplication`类

（3）再运行服务端：运行`client`模块中的`ClientApplication`类

### 3. 使用方式

#### 菜单界面

|   `↑`    |   `↓`    | Enter | 鼠标 |
| :------: | :------: | :---: | :------: |
| 上一选项 | 下一选项 | 确定  | 点击选项 |

#### 游戏界面

|   `↑`    |   `↓`    | `←`      | `→`      | 鼠标 |
| :------: | :------: | -------- | -------- | -------- |
| 向上移动 | 向下移动 | 向左移动 | 向右移动 | 射击     |

