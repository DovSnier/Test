# Test

- [一. 说明](#一-说明)
  - [1.1 项目目录图](#11-项目目录图)
  - [1.2 项目依赖图](#12-项目依赖图)
- [二. 安装](#二-安装)

## 一. 说明

### 1.1 项目目录图

```Django
+--- project :app
|    +--- dir :common
|    |     \--- project :common:bean
|    |     \--- project :common:utils
|    +--- dir :framework
|    |     \--- project :framework:base
|    |     \--- project :framework:doc
|    |     \--- project :framework:style
|    \--- dir :research
|         \--- project :research:common
|         \--- project :research:interceptor
|         \--- project :research:provider
|         \--- project :research:service
|         \--- project :research:tpl
|         \--- project :research:view
|         \--- project :research:widget

```

### 1.2 项目依赖图

```Django
+--- app
|    +--- project :framework:base
|    |     \--- project :common:utils
|    |     \--- project :framework:style
|    \--- project :common:bean
|    +--- project :research:interceptor
|         +--- project :research:view
|         |     +--- project :framework:base (*)
|         |     \--- project :framework:style (t)
|         |     \--- project :framework:utils (t)
|         +--- project :research:widget
|         |     +--- project :framework:base (*)
|         |     \--- project :framework:style (t)
|         |     \--- project :framework:utils (t)
|         +--- project :research:common
|         |     +--- project :framework:base (*)
|         |     \--- project :framework:style (t)
|         |     \--- project :framework:utils (t)
|         +--- project :research:tpl
|         |     +--- project :framework:base (*)
|         |     \--- project :framework:style (t)
|         |     \--- project :framework:utils (t)
|         |     \--- project :common:bean
|         \--- project :research:service
|               +--- project :framework:base (*)
|               \--- project :framework:style (t)
|               \--- project :framework:utils (t)
|
+--- project :research:common (*)
+--- project :research:service (*)
+--- project :framework:style
+--- project :research:tpl (*)
+--- project :research:utils
+--- project :research:view (*)
\--- project :research:widget (*)


(*) - dependencies omitted (listed previously)
(n) - Not resolved (configuration is not meant to be resolved)
(t) - transitive dependencies and that not directly dependent
```

## 二. 安装

运行APP 之前需要执行如下命令:

```bash
$ npm install .
```
