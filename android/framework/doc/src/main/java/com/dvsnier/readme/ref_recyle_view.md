
### RecycleView

#### FAQ

1. 弄明白相对滚动和绝对滚动的区别和联系?

> 相对滚动: 父子滚动,嵌套滚动
> 绝对滚动: 相对于屏幕滚动

2. View 滚动基础知识汇总

- 理解`布局坐标`,`视图坐标`,`滚动坐标`,结合`n-X`考虑正负向问题;
- `手势操作`针对的是屏幕中待滚动View 画布的操作, 而不是屏幕呈现滚动的组件(AdapterView, RecycleView,XXXScrollView)的滚动;

3. layoutManager.findXXX() 相关API ?

```
        layoutManager.findFirstVisibleItemPosition();
        layoutManager.findLastVisibleItemPosition();
```

4. recycleView.findXXX() 相关API ?

```
        recyclerView.findViewHolderForLayoutPosition(position);
        recyclerView.findViewHolderForAdapterPosition(position);
        recyclerView.findViewHolderForItemId(id);
```

5. recycleView 滚动计算和屏幕位置计算问题 ?

- 相对滚动的计算
- 绝对滚动的计算
- 可视组件的计算
- 可视组件局部滚动的计算
- 相对与Windows 的计算
- 相对与Screen 的计算


