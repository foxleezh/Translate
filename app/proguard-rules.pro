# 代码混淆压缩比，在0~7之间，默认为5,一般不下需要修改
-optimizationpasses 5
# 混淆时不使用大小写混合，混淆后的类名为小写
# windows下的同学还是加入这个选项吧(windows大小写不敏感)
-dontusemixedcaseclassnames
# 指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclasses
# 不做预检验，preverify是proguard的四个步骤之一
# Android不需要preverify，去掉这一步可以加快混淆速度
-dontpreverify
# 有了verbose这句话，混淆后就会生成映射文件
# 包含有类名->混淆后类名的映射关系
# 然后使用printmapping指定映射文件的名称
-verbose
# -printmapping priguardMapping.txt

# 指定混淆时采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不改变
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-dontoptimize
-dontwarn dalvik.**
-ignorewarnings


#-applymapping mapping/mapping.txt

# 保留了继承自Activity、Application这些类的子类
# 因为这些子类有可能被外部调用
# 比如第一行就保证了所有Activity的子类不要被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View

# 如果有引用android-support-v4.jar包，可以添加下面这行
-keep public class com.null.test.ui.fragment.** {*;}
-keep public class * extends com.gkoudai.finance.mvvm.MvvmBaseViewModel
-keep public class * extends com.gkoudai.finance.mvvm.MvvmBaseModel
# 保留所有的本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保留Activity中的方法参数是view的方法，
# 从而我们在layout里面编写onClick就不会影响
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# 保留自定义控件(继承自View)不能被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(***);
    *** get* ();
}


-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留Parcelable序列化的类不能被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# 保留Serializable 序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[] serialPersistentFields;
   !static !transient <fields>;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

# 对R文件下的所有类及其方法，都不能被混淆
-keepclassmembers class **.R$* {
    public static <fields>;
}

# 对于带有回调函数onXXEvent的，不能混淆
-keepclassmembers class * {
    void *(**on*Event);
}

-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

-dontwarn android.support.**
-keep class android.support.v4.** { *; }
-keep class android.support.multidex.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep public class * extends com.gkoudai.finance.mvp.BaseModel{
 *;
}

-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
#okio包中不发出警告
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**


-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}


-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class com.google.gson.**{ *; }

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
#  避免混淆泛型、抛出异常时保留代码行号
-keepattributes Signature,SourceFile,LineNumberTable

# For using GSON @Expose annotation
# 保护代码中的Annotation不被混淆
# 这在JSON实体映射时非常重要，比如fastJson
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }

-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
# keep butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# keep eventbus
-keepclassmembers class ** {
    public void onEvent*(**);
}

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-keepclasseswithmembers class * {
    ... *JNI*(...);
}

-keepclasseswithmembernames class * {
	... *JRI*(...);
}

-keep class **JNI* {*;}
-keep class com.foxleezh.translate.bean.** {*;}


# RxJava RxAndroid
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-dontnote rx.internal.util.PlatformDependent



