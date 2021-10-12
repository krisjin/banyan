package org.banyan.concurrent.lock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <pre>
 * 当竞争存在时，如果线程可以很快获得锁，那么可以不在OS层挂起线程，让线程做几个空操作（自旋）
 * JDK1.6中-XX:+UseSpinning开启
 * JDK1.7中，去掉此参数，改为内置实现
 * 如果同步块很长，自旋失败，会降低系统性能
 * 如果同步块很短，自旋成功，节省线程挂起切换时间，提升系统性能
 * </pre>
 *
 * @author krisjin on 2019/4/26
 */
public class SpinLock {
}
