#### CustomScrollView的使用
1. .在你的project的build.gradle添加如下代码：
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. 在你的module中添加如下依赖：
```
dependencies {
	compile 'com.github.diewu0421:CustomScrollView:-SNAPSHOT'
}
```


### 用法
##### 1. 在xml中使用：
```
    <com.example.mylib.widget.CustomHorizontalScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#b88b8b"
        android:id="@+id/custom_scroll"/>
```

##### 2. 在Activity中使用：
```
custom_scroll.apply {
	//设置一页显示多少张图片
	pageCount = 4

    //设置滑动的阈值 值越小，即滑动越小的距离松手即可自动滚动到上/下页
    SCROLL_DISTANCE = getScreenSize(this@MainActivity).widthPixels / 4
}

```

### 效果

![CustomScrollView](https://raw.githubusercontent.com/diewu0421/CustomScrollView/master/blob/master/2017-11-07_14-21-37.gif)

