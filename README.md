# Persevere

## 注意事项

### 代码生成器

子模块 bt-codegen-plugin 下的 codegen-apt 为代码生成器，使用时请先安装该模块。

```shell
mvn clean install -U -e -pl bt-codegen-plugin\codegen-apt
```

### 代码生成

在引入了 bt-codegen-plugin 下的 codegen-apt 依赖的 module 模块中，运行 maven 插件生成代码。

> 本项目可以在 bt-system-center 下的 bt-system-center-domain 模块中进行体验

```shell
mvn clean compile -U -e -pl bt-system-center\bt-system-center-domain
```

**对于命令行执行会报错的**

解决方案一: 在 IDEA 右侧执行 Maven 插件 plugin compiler:compiler。

![img2.png](docs/photo/img2.png)

解决方案二: 在 IDEA 中右键点击 module -> Maven -> Generate Sources and Update Folders

解决方案三: 在 IDEA 中右键点击 module -> Run Maven -> compile

![img3.png](docs/photo/img3.png)

### Live Templates (IDEA) 导入

Manage IDE Settings -> Import Settings -> 选择本项目的 settings.zip 

![img.png](docs/photo/img.png)

具体命令可在 live templates 中查看。

![img4.png](docs/photo/img4.png)