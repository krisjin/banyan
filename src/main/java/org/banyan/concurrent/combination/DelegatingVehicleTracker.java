package org.banyan.concurrent.combination;

import org.banyan.concurrent.model.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 将线程安全委托给ConcurrentHashMap
 * User : krisjin
 * Date: 2015/9/9
 */
public class DelegatingVehicleTracker {

    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unModifyAbleMap;

    /**
     * 构造函数初始化，保证线程的安全性。
     *
     * @param map
     */
    public DelegatingVehicleTracker(Map<String, Point> map) {
        locations = new ConcurrentHashMap<String, Point>(map);
        unModifyAbleMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unModifyAbleMap;
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name : " + id);
        }
    }

    public static void main(String[] args) {
        List<String> l = new ArrayList<>();

        String s = "1.3641936E7";
        BigDecimal bd = new BigDecimal(s);
        bd.setScale(2, RoundingMode.FLOOR);
        System.err.println(bd.doubleValue());


    }

}
