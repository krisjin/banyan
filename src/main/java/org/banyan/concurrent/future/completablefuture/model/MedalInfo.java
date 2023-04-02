package org.banyan.concurrent.future.completablefuture.model;

/**
 * @author kris
 * @date 2023/4/2
 */
public class MedalInfo {
    private String id;
    private String name;

    public MedalInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
