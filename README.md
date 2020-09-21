# 手写`EventBus3.0`框架

[TOC]

## 一、实现思想

`EventBus3.0`延续了`EventBus2.0`的思想，也是收集管理被`Subscribe`注解的方法，将其放在`Map`中，执行`post`方法时从这个`Map`中遍历，找到与`post`方法参数类型相同的所有方法，然后通过反射执行这些方法。

区别在于`EventBus3.0`通过`APT(Annotation Processing Tool)`技术在编译期收集被`Subscribe`注解的方法，并自动生成`EventBusIndex`类，在该类的静态块中将所有被收集到的方法加入到名字为`SUBSCRIBER_INDEX`的`HashMap`中，该`Map`以`Activity/Fragment`的类名为键，以`SubscriberInfo`为值，`SubscriberInfo`中包含了一个`SubscriberMethod`数组，该数组中存放着被`Subscribe`注解的方法的信息，包括方法名称，线程模式，事件优先级，类名（`Activity/Fragment`）等。

自动生成`EventBusIndex`类文件使用的是`javapoet`技术：`javapoet is a Java API for generating .java source files`，其`github`地址为：[https://github.com/square/javapoet](https://github.com/square/javapoet) 。

## 二、实现步骤

首先各个模块的依赖关系如下图所示：

![image](https://github.com/tianyalu/NeEventBus3ManualImpl/raw/master/show/eventbus_module_dependence_relation.png)

> 1. `eventbus_annotation`：定义`EventBus`相关的注解；
> 2. `eventbus_compiler`：注解处理器，用来收集`EventBus`注解信息并自动生成`EventBusIndex`类；
> 3. `eventbus`：`EventBus`核心库，用来收集和分发`EventBus`事件。





