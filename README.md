## AndStateBox

Android状态反馈页面管理框架，根据需求切换状态页面，全面支持自定义状态页面，如加载中，空数据，网络异常，占位页等常用页面。

## 框架说明
|关键类|类描述|
|---|---|
|`StateBox.class`|页面配置入口，配合Config内部类，将预先配置的状态页面注入到指定View上|
|`BoxManager.class`|状态页面调度，StateBox注入状态页面后，会返回一个BoxManager管理器，用来调度状态页面的切换
|`BaseStateView.class`|抽象类，标识用户动态授权时勾选“不再询问”选项时则回调该方法|


## 依赖配置

```groovy

//需要在使用到本库的模块添加以下依赖
dependencies {
    ...
    implementation 'com.utopia:state-box:1.0.0'
}

```

## 使用示例
1、开启全局配置，建议在Application中加入以下代码块：
````java
//预置常用的状态页面，可以加快页面加载速度
StateBox.Config boxConfig = new StateBox.Config()
        .addStateView(EmptyStateView.class)       
        //...添加自定义状态页面       
        .addStateView(LoadingStateView.class, true);//默认显示的状态页面
//应用配置
StateBox.getDefault().setConfig(boxConfig);
````

2、非全局配置方式（应用与特定页面配置）：
````java
BoxManager stateManager = StateBox.getDefault().inject(this, R.id.root_view, v -> loadData());

//局部添加StateView，作用域跟随stateManager生命周期
stateManager.addStatePage(new Http50xStateView());

//显示此服务器错误页面
stateManager.show(Http50xStateView.class);


````

3、完整示例：
````java
>请参考示例demo
````
## 开源协议
```text
Copyright 2021 freeutopia.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```