# Test

- [一. 项目目录图](#一-项目目录图)
- [二. 项目依赖图](#二-项目依赖图)

## 一. 项目目录图

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

## 二. 项目依赖图

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
