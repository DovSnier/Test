# 解析依赖

- [一. Modules 依赖](#一-modules-依赖)
  - [1.1 单节点](#11-单节点)
  - [1.2 完整性节点](#12-完整性节点)
- [二. Library 依赖](#二-library-依赖)
- [三. 举例](#三-举例)
  - [3.1 脚本命令](#31-脚本命令)
  - [3.2 Modules 实例](#32-modules-实例)
    - [3.2.1 v0.0.1](#321-v001)

## 一. Modules 依赖

### 1.1 单节点

```bash
./gradlew -q :android:app:dependencies --configuration implementation > ./android/Temp/dependencies.txt
```

### 1.2 完整性节点

```bash
:android:app
:android:common:bean
:android:common:utils
:android:framework:doc
:android:framework:base
:android:framework:style
:android:research:interceptor
:android:research:widget
:android:research:view
:android:research:service
:android:research:provider
:android:research:common
:android:research:tpl
:android:java:sample

./gradlew :android:app:dependencies
./gradlew :android:common:bean:dependencies
./gradlew :android:common:utils:dependencies
./gradlew :android:framework:doc:dependencies
./gradlew :android:framework:base:dependencies
./gradlew :android:framework:style:dependencies
./gradlew :android:research:interceptor:dependencies
./gradlew :android:research:widget:dependencies
./gradlew :android:research:view:dependencies
./gradlew :android:research:service:dependencies
./gradlew :android:research:provider:dependencies
./gradlew :android:research:common:dependencies
./gradlew :android:research:tpl:dependencies

./gradlew -q --debug :android:app:dependencies --configuration devDebugCompileClasspath > ./android/Temp/dependencies.txt
./gradlew -q --info :android:app:dependencies --configuration devDebugCompileClasspath > ./android/Temp/dependencies.txt
./gradlew -q :android:app:dependencies --configuration devDebugCompileClasspath > ./android/Temp/dependencies.txt
./gradlew -q :android:app:dependencies --configuration debugRuntimeClasspath > ./android/Temp/dependencies.txt
./gradlew -q :android:app:dependencies --configuration releaseCompileClasspath > ./android/Temp/dependencies.txt
./gradlew -q :android:app:dependencies --configuration releaseRuntimeClasspath > ./android/Temp/dependencies.txt
```

## 二. Library 依赖

```bash
./gradlew -q :android:app:dependencyInsight --configuration devDebugCompileClasspath --dependency design > ./android/Temp/dependencies.txt
./gradlew -q :android:app:dependencyInsight --configuration debugRuntimeClasspath --dependency design > ./android/Temp/dependencies.txt
```

## 三. 举例

### 3.1 脚本命令

```bash
// windows
gradlew -q :android:app:dependencies --configuration devDebugCompileClasspath > devDebugCompileClasspath.txt
gradlew -q :android:app:dependencies --configuration debugRuntimeClasspath > debugRuntimeClasspath.txt
gradlew -q :android:app:dependencies --configuration devDebugCompileClasspath > devDebugCompileClasspath.txt

// mac
./gradlew -q :android:framework:base:dependencies --configuration devDebugCompileClasspath > devDebugCompileClasspath.txt
./gradlew -q :android:app:dependencies --configuration devDebugCompileClasspath > devDebugCompileClasspath.txt

// windows and mac dependencies
./gradlew :android:app:dependencies --configuration devDebugCompileClasspath > ./android/Temp/dependencies.txt
./gradlew :android:common:bean:dependencies :android:common:utils:dependencies :android:framework:doc:dependencies :android:framework:base:dependencies :android:framework:style:dependencies :android:research:interceptor:dependencies :android:research:widget:dependencies :android:research:view:dependencies :android:research:service:dependencies :android:research:provider:dependencies :android:research:common:dependencies :android:research:tpl:dependencies  --configuration debugCompileClasspath > ./android/Temp/dependencies2.txt

// refresh dependencies
./gradlew --refresh-dependencies --info > ./android/Temp/dependencies.txt

// windows and mac dependencies
./gradlew :android:app:dependencies :android:common:bean:dependencies :android:common:utils:dependencies :android:framework:doc:dependencies :android:framework:base:dependencies :android:framework:style:dependencies :android:research:interceptor:dependencies :android:research:widget:dependencies :android:research:view:dependencies :android:research:service:dependencies :android:research:provider:dependencies :android:research:common:dependencies :android:research:tpl:dependencies > ./android/Temp/dependencies.txt

./gradlew :android:app:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:common:bean:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:common:utils:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:framework:doc:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:framework:base:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:framework:style:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:research:interceptor:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:research:widget:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:research:view:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:research:service:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:research:provider:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:research:common:dependencies > ./android/Temp/dependencies.txt
./gradlew :android:research:tpl:dependencies > ./android/Temp/dependencies.txt
```

### 3.2 Modules 实例

#### 3.2.1 v0.0.1

```Django
debugCompileClasspath - Resolved configuration for compilation for variant: debug
+--- com.squareup.leakcanary:leakcanary-android:1.5.4
|    \--- com.squareup.leakcanary:leakcanary-analyzer:1.5.4
|         +--- com.squareup.leakcanary:leakcanary-watcher:1.5.4
|         \--- com.squareup.haha:haha:2.0.3
+--- com.squareup.leakcanary:leakcanary-analyzer:1.5.4 (*)
+--- com.squareup.leakcanary:leakcanary-watcher:1.5.4
+--- com.squareup.haha:haha:2.0.3
+--- com.dvsnier:common:0.0.3
|    \--- com.dvsnier:base:0.0.3
+--- com.dvsnier:base:0.0.3
+--- com.dvsnier:crash:0.0.4
+--- org.greenrobot:greendao:3.2.2
|    \--- org.greenrobot:greendao-api:3.2.2
+--- org.greenrobot:greendao-api:3.2.2
+--- org.xutils:xutils:3.3.42
+--- com.dvsnier:utils:0.0.4
|    \--- com.google.code.gson:gson:2.8.0
+--- com.google.code.gson:gson:2.8.0
+--- com.jakewharton:butterknife:9.0.0
|    \--- com.jakewharton:butterknife-runtime:9.0.0
|         +--- com.jakewharton:butterknife-annotations:9.0.0
|         |    \--- com.android.support:support-annotations:28.0.0
|         \--- com.android.support:support-compat:28.0.0 (*)
+--- com.jakewharton:butterknife-runtime:9.0.0 (*)
+--- com.jakewharton:butterknife-annotations:9.0.0 (*)
+--- com.squareup.retrofit2:retrofit:2.0.2
|    \--- com.squareup.okhttp3:okhttp:3.2.0 -> 3.9.0
|         \--- com.squareup.okio:okio:1.13.0
+--- com.squareup.okhttp3:okhttp:3.9.0 (*)
+--- com.squareup.okio:okio:1.13.0
+--- com.jakewharton:disklrucache:2.0.2
+--- com.github.bumptech.glide:glide:3.7.0
+--- com.facebook.stetho:stetho:1.5.0
|    +--- commons-cli:commons-cli:1.2
|    \--- com.google.code.findbugs:jsr305:2.0.1
+--- commons-cli:commons-cli:1.2
+--- com.google.code.findbugs:jsr305:2.0.1
+--- com.facebook.stetho:stetho-okhttp3:1.5.0
|    +--- com.facebook.stetho:stetho:1.5.0 (*)
|    +--- com.google.code.findbugs:jsr305:2.0.1
|    \--- com.squareup.okhttp3:okhttp:3.4.2 -> 3.9.0 (*)
+--- com.facebook.stetho:stetho-js-rhino:1.5.0
|    +--- com.google.code.findbugs:jsr305:2.0.1
|    \--- org.mozilla:rhino:1.7.6
+--- org.mozilla:rhino:1.7.6
+--- com.orhanobut:logger:2.1.1
+--- org.greenrobot:eventbus:3.0.0
+--- junit:junit:4.13-beta-3
|    \--- org.hamcrest:hamcrest-core:1.3
+--- org.hamcrest:hamcrest-core:1.3
+--- com.squareup.leakcanary:leakcanary-android:1.5.4 (*)
+--- project :base
|    +--- com.dvsnier:common:0.0.3 (*)
|    \--- com.dvsnier:crash:0.0.4
+--- project :bean
|    \--- org.greenrobot:greendao:3.2.2 (*)
+--- project :utils
|    +--- org.xutils:xutils:3.3.42
|    \--- com.dvsnier:utils:0.0.4 (*)
+--- project :research:interceptor
|    \--- project :research:view
+--- com.android.support:recyclerview-v7:28.0.0 (*)
+--- com.android.support:appcompat-v7:28.0.0 (*)
+--- com.android.support:support-annotations:28.0.0
+--- com.android.support:design:28.0.0 (*)
+--- com.android.support.constraint:constraint-layout:1.1.3 (*)
+--- com.jakewharton:butterknife:9.0.0 (*)
+--- com.squareup.retrofit2:retrofit:2.0.2 (*)
+--- com.jakewharton:disklrucache:2.0.2
+--- com.github.bumptech.glide:glide:3.7.0
+--- com.facebook.stetho:stetho:1.5.0 (*)
+--- com.facebook.stetho:stetho-okhttp3:1.5.0 (*)
+--- com.facebook.stetho:stetho-js-rhino:1.5.0 (*)
+--- com.squareup.okhttp3:okhttp:3.9.0 (*)
+--- com.orhanobut:logger:2.1.1
+--- org.greenrobot:eventbus:3.0.0
\--- junit:junit:4.13-beta-3 (*)

(*) - dependencies omitted (listed previously)
```
