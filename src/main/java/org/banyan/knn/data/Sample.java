package org.banyan.knn.data;

/**
 * 抽象类，用于定义KNN算法示例的基本元素
 */
public abstract class Sample {

    /**
     * Method that returns the tag or class of the example
     *
     * @return The tag or class of the examples
     */
    public abstract String getTag();

    /**
     * Method that return the values of the attributes of the example as an array of doubles
     *
     * @return The values of the attributes of the example
     */
    public abstract double[] getExample();
}
