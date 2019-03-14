package org.banyan.concurrent.masterworker;

/**
 * @author krisjin on 2017/5/13
 */
public class PlusWorker extends Worker {

    /**
     * cubic sum
     *
     * @param input
     * @return
     */
    @Override
    public Object handle(Object input) {
        Integer i = (Integer) input;

        return i * i * i;
    }
}
