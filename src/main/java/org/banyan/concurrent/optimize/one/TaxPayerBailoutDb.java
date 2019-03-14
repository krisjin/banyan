package org.banyan.concurrent.optimize.one;

/**
 * 纳税人救助数据库
 * User: krisjin
 * Date: 2016/3/19
 */
public interface TaxPayerBailoutDb {
    static final int NUMBER_OF_RECORDS_DESIRED = 2 * 1000000;

    /**
     * 根据纳税人的Id从数据库获取记录
     *
     * @param id 纳税人Id
     * @return 纳税人记录
     */
    public TaxPayerRecord get(String id);


}
