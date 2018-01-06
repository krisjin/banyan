package org.concurrent.guard;

/**
 * User shijingui
 * Date 2017/5/14
 */
public class Test {

    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();

        //create and startup server thread
        for (int i = 0; i < 10; i++) {
            new ServerThread(requestQueue, "ServerThread" + i).start();
        }

        //create and startup client thread
        for (int i = 0; i < 10; i++) {
            new ClientThread(requestQueue, "ClientThread" + i).start();
        }
    }
}
