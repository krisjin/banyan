package org.concurrent.combination;

import org.concurrent.model.KeyListener;
import org.concurrent.model.MouseListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 将线程安全性委托给多状态变量
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/9
 * Time: 23:12
 */
public class VisualComponent {

    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<KeyListener>();

    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<MouseListener>();

    public void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
    }

    public void addMouseListener(MouseListener mouseListener) {
        mouseListeners.add(mouseListener);
    }

    public boolean removeKeyListener(KeyListener keyListener) {
        return keyListeners.remove(keyListener);
    }

    public boolean removeMouseListener(MouseListener mouseListener) {
        return mouseListeners.remove(mouseListener);
    }
}
