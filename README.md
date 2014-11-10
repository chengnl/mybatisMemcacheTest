mybatisMemcacheTest
=====
##简介
    插件环境： 
    mybatis   3.2.6   ，mybatis-memcached  1.0.0
    因项目中出现查询缓存bug问题，具体现象如下：
    不管相同mapper下如何更新值，某些查询方法返回的值，始终不变。
    跟踪日志，发现始终查询的缓存，而缓存已经不是最新值，也就是缓存已经是脏数据，并且后续无法更新清除，除非缓存失效或者手工清除缓存，让其查数据库。
    
## 分析代码
   跟踪分析原因发现是mybatis-memcached中的这段代码有问题：
   ```
   
         // add namespace key into memcached
        Set<String> group = getGroup(groupKey);
        if (group == null) {
            group = new HashSet<String>();
        }
        group.add(keyString);

        if (log.isDebugEnabled()) {
            log.debug("Insert/Updating object ("
                    + groupKey
                    + ", "
                    + group
                    + ")");
        }

        storeInMemcached(groupKey, group);
        
   ```
   memcached这个插件，一个mapper对应一个groupkey，其他的所有方法都是该group下的keyString，每次有更新，会更新groupkey下的所有keyString的值或者让其重查。但是在多线程并发的情况下，如果group为null，会导致group的值被后面的覆盖，从而使一部分keyString丢失，引起上面说的现象。  
   
## 验证测试

com.vrv.im.test.Main这个类验证测试
1、模拟两个线程查询同一个记录，使用方法不同
2、修改该记录
3、再次查询
会发现开始结果一样，修改后再查询，结果不一样了。有一个方法的缓存已经是脏数据，并且无法更新了。

    
