package org.banyan.concurrent.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * -XX:-UseCompressedOops
 * @author kris
 * @date 2023/1/16
 */
public class JolExample {

    public static void main(String[] args) {
        User user = new User();
        out.println(VM.current().details());
        out.println(ClassLayout.parseInstance(user).toPrintable());
    }

}
