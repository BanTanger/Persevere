# Class Not Found？

插件：Maven Search。报 Class Not Found 的具体类使用，找到该类的相关依赖，进而排查到底有没有引入对应的 Maven 依赖

# 两个 Class 冲突？

命令： mvn dependency:tree 不要用可视化，对应 maven 版本要调好

# ClassLoader 源码学习

深入学习 ClassLoader。

`shenyu` 项目: `shenyu-web#loader#ShenyuPluginLoader`（动态加载）

`dubbo` 项目: `Extensionloader<T>`

`spring-boot-loader`: springboot 解析单体 Jar 包

`elasticsearch-plugin-classloader`: 在 es 安装目录下的 plugins 目录下，可以放置自定义的插件，通过 ClassLoader 加载（需要重启）