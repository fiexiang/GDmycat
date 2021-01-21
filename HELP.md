# GIT URL
 http://192.100.1.53/kd_wangyuqiang/mycat.git 

# issu
1. 动态表，修改时间戳报错
 mycat是不允许修改分库分表所依赖的字段的，例如TIME字段
 动态表是通过TIME字段进行分库分表的，如果修改这个字段会影响分库分表策略，所以不可以修改的

 