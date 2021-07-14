### LayoutManager

RecyclerView缓存池：

​	```mAttachedScrap```:已经binding在屏幕上的viewholder

​		 **只有**在触发了RV的```onMeasure```与```onLayout```才会把当前vh存入，绘制和布局完后会清空

​	```mCacheViews```:只会缓存最新的两个（默认大小）

​			采用现进先出，每次只会存最新，然后移除旧的移除vh。

​	```mViewCacheExtension```:自定义缓存池

​	```recyclerPool```：缓存所有的type类型viewholder

取vh顺序：

​	```mCacheViews->mViewCacheExtension->recyclerPool```

**滚动的时候```mAttachedScrap```是空的所以不会取此处vh**