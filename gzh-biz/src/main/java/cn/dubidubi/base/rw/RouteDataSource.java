package cn.dubidubi.base.rw;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Auther: 16224
 * @Date: 2018/5/9 0009 13:18
 * @Description:扩展spring的数据源路由类
 */
public class RouteDataSource extends AbstractRoutingDataSource {
    //每一个请求对应一个线程
    private static ThreadLocal<String> holder = new ThreadLocal<>();
    @Override
    protected Object determineCurrentLookupKey() {
        return  holder.get();
    }

    /**@description: 设置key,之后会根据得到key去获取连接池的连接（在配置文档中配置）
     * 流程  一个sql语句执行-->根据前缀判断是走主库还是从库,并将判断的结果缓存，之后直接根据sql语句为key直接取出库的结果
     * -->如果是从库的话-->将enum的name作为key传入路由，之后会依据key，去获取配置文档中配置的数据源
     *
     * @param:
     * @return:
     * @auther: 16224
     * @date: 2018/5/9 0009 13:19
     */
    public static void setDbKey(RouteDataSourceKeyEnum dbKey) {
        holder.set(dbKey.name());
    }
}
