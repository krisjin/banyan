package test;

/**
 * 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛{@link StackOverflowError}异常
 * VM Args: -Xss128k
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/10/14
 * Time: 22:56
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + javaVMStackSOF.stackLength);
            throw e;
        }
    }
}
