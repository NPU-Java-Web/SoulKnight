# `SoulKnight`

**仓库说明：**

dev为功能最多最全的开发分支，master为经过测试排除bug后的稳定上线版本，成员个人开发分支命名格式为dev-name。
开发成员单元测试完成后合并到dev分支，每个迭代阶段结束经过测试的dev分支合并到master分支。

## 文件树

```shell
SoulKnight
│  .gitignore
│  pom.xml
│  README.md
│  soulKnight.iml
│  
├─.idea
│              
├─client #客户端
│  │  client.iml
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
│     │  │              │  │  
│     │  │              │  ├─communication  # 与服务端通讯类
│     │  │              │  │      AnalysisMessage.java
│     │  │              │  │      DeliverPlayer.java
│     │  │              │  │      MyClientInboundHandler.java
│     │  │              │  │      UploadMessage.java
│     │  │              │  │      
│     │  │              │  └─service  # 本地数据处理
│     │  │              │          GameDataProcess.java
│     │  │              │          StaticInfo.java
│     │  │              │          
│     │  │              ├─display  # View层
│     │  │              │      Dialog.java
│     │  │              │      DisplayMain.java
│     │  │              │      GamePanel.java
│     │  │              │      GameRenderThread.java
│     │  │              │      MainPanel.java
│     │  │              │      PlaySound.java
│     │  │              │      
│     │  │              └─Input  # 输入监听层
│     │  │                      GameMouseListener.java
│     │  │                      KeyEventListener.java
│     │  │                      
│     │  └─resources  # 客户端图片音乐等静态资源
│     │              
│     └─test  # 单元测试类
│         └─java
│             └─org
│                 └─example
│                     └─client
│                             ClientApplicationTest.java
│                            
│                          
├─common
│  │  common.iml
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
│                         │  └─level
│                         │          Level.java
│                         │          Level1.java
│                         │          Level2.java
│                         │          Level3.java
│                         │          
│                         ├─keyListener  # 按键监听类
│                         │      GameInput.java
│                         │      
│                         ├─model  # 实体类
│                         │  ├─animation
│                         │  │  │  Animation.java
│                         │  │  │  
│                         │  │  └─entity
│                         │  │          Explosion.java
│                         │  │          Portal.java
│                         │  │          
│                         │  ├─bullet
│                         │  │  │  Bullet.java
│                         │  │  │  BulletFactory.java
│                         │  │  │  
│                         │  │  └─entity
│                         │  │          Bullet1.java
│                         │  │          Bullet2.java
│                         │  │          Bullet3.java
│                         │  │          Bullet4.java
│                         │  │          Bullet5.java
│                         │  │          Bullet6.java
│                         │  │          Bullet7.java
│                         │  │          Bullet8.java
│                         │  │          
│                         │  ├─monster
│                         │  │  │  Monster.java
│                         │  │  │  MonsterFactory.java
│                         │  │  │  
│                         │  │  └─entity
│                         │  │          Monster1.java
│                         │  │          Monster2.java
│                         │  │          Monster3.java
│                         │  │          Monster4.java
│                         │  │          Monster5.java
│                         │  │          Monster6.java
│                         │  │          Monster7.java
│                         │  │          Monster8.java
│                         │  │          
│                         │  └─player
│                         │      │  Player.java
│                         │      │  PlayerFactory.java
│                         │      │  
│                         │      └─entity
│                         │              Player1.java
│                         │              Player2.java
│                         │              PLayer3.java
│                         │              
│                         └─protocal
│                                 Connect.java
│                                 Order.java
│                                 Result.java
│                                 
└─server
    │  pom.xml
    │  server.iml
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
       │                  │      AnimationDAO.java
       │                  │      BulletDAO.java
       │                  │      MonsterDAO.java
       │                  │      PlayerDAO.java
       │                  │      
       │                  ├─handler
       │                  │      MyServerInboundHandler.java
       │                  │      
       │                  ├─service  # 服务端业务逻辑Service层
       │                  │      AnimationService.java
       │                  │      BulletService.java
       │                  │      MonsterService.java
       │                  │      OrderService.java
       │                  │      PlayerService.java
       │                  │      
       │                  ├─thread
       │                  │      RefreshThread.java
       │                  │      
       │                  └─util
       │                          Coordinate.java
       │                          Creatures.java
       │                          Verification.java
       │                          
       └─test  # 服务端单元测试
           └─java
               └─org
                   └─example
                       └─server
                               ServerApplicationTest.java
```

## 构建说明

本项目用使用`Mavean`构建项目，采用IDEA集成开发环境构建，整体结构分为客户端，服务端，Common实体类层。

## 运行说明

### 1. 本项目使用高性能非关系型数据库`Redis`，如尚未安装：

Windows：请下载此路径`Redis`安装包https://github.com/microsoftarchive/redis/releases/tag/win-3.2.100。解压后一直点击下一步即可。

Linux：执行以下命令

```bash
wget http://download.redis.io/releases/redis-6.0.8.tar.gz
tar -xvf redis-6.0.8.tar.gz
mkdir /usr/local/redis-6.x
mv  redis-6.0.8   /usr/local/redis-6.x
cd  /usr/local/redis-6.x/redis-6.0.8
make
make  install  PREFIX=/usr/local/redis-6.x
cd /usr/local/redis6.x/bin/
mkdir conf
cp /usr/local/redis6.x/redis-6.0.8/redis.conf /usr/local/redis6.x/bin/conf/

# 启动测试
cd /usr/local/redis6.x/bin/
./redis-server conf/redis.conf
./redis-cli -p  8081
```

### 2. 使用IDEA打开项目

（1）先运行服务端：运行`Service`包中的`ServerApplication`类

（2）再运行服务端：运行`Client`包中的`ClientApplication`类

### 3. 使用方式

本项目无按键冲突，支持多按键同时使用。

#### 菜单界面

|   `↑`    |   `↓`    | Enter | 鼠标右键 |
| :------: | :------: | :---: | :------: |
| 上一选项 | 下一选项 | 确定  | 点击选项 |

#### 游戏界面

|   `↑`    |   `↓`    | `←`      | `→`      | 鼠标右键 |
| :------: | :------: | -------- | -------- | -------- |
| 向上移动 | 向下移动 | 向左移动 | 向右移动 | 射击     |

