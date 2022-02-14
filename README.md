## app/mainfests/AndroidManifests.xml

```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

## Gradle Scripts/build.gradle(Project)

```
dependencies {
classpath 'com.github.dcendents:android-maven-gradle-plugin:2.1'
}

allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```
## Gradle Scripts/build.gradle(Module)


```
dependencies {
implementation 'com.dinuscxj:circleprogressbar:1.3.6'
implementation 'com.github.bootpay:client_android_java:3.3.91'
implementation 'com.squareup.okhttp3:okhttp:4.9.2'
}
```
## Gradle Scripts/setting.gradle(Module)

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven { url 'https://jitpack.io' }
    }
}
```
