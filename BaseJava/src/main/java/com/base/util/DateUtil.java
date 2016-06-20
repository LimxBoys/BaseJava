package com.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 创建一个 SimpleDateFormat实例的开销比较昂贵，解析字符串时间时频繁创建生命周期短暂的实例导致性能低下。
 * 即使将 SimpleDateFormat定义为静态类变量，貌似能解决这个问题，但是SimpleDateFormat是非线程安全的，
 * 同样存在问题，如果用 ‘synchronized’线程同步同样面临问题，同步导致性能下降（线程之间序列化的获取SimpleDateFormat实例）。 
 * 使用Threadlocal解决了此问题，对于每个线程SimpleDateFormat不存在影响他们之间协作的状态，
 * 为每个线程创建一个SimpleDateFormat变量的拷贝或者叫做副本。 
 * @author Administrator
 *
 */
public class DateUtil {
	// SimpleDateFormat is not thread-safe, so give one to each thread  
    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){  
        @Override  
        protected SimpleDateFormat initialValue()  
        {  
            return new SimpleDateFormat("yyyyMMdd HHmm");  
        }  
    };  
  
    public static String formatIt(Date date)  
    {  
        return formatter.get().format(date);  
    }  
}
