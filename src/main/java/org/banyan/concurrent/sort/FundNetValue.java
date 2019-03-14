package org.banyan.concurrent.sort;

import java.math.BigDecimal;
import java.util.*;

/**
 * User:krisjin
 * Date:2019/2/25
 */
public class FundNetValue {
    /**
     * 净值日期
     */
    private Date netValueDate;
    /**
     * 净值
     */
    private BigDecimal netValue;

    /**
     * 构造函数
     */
    public FundNetValue(Date netValueDate, BigDecimal netValue) {
        this.netValueDate = netValueDate;
        this.netValue = netValue;
    }

    /**
     * setter for netValueDate
     */
    public void setNetValueDate(Date netValueDate) {
        this.netValueDate = netValueDate;
    }

    /**
     * setter for netValue
     */
    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    /**
     * getter for netValueDate
     */
    public Date getNetValueDate() {
        return this.netValueDate;
    }

    /**
     * getter for netValue
     */
    public BigDecimal getNetValue() {
        return this.netValue;
    }

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(day);
        calendar.add(Calendar.DATE, -1);
        FundNetValue fundNetValue1 = new FundNetValue(calendar.getTime(), new BigDecimal(45.424));

        calendar.add(Calendar.DATE, -2);
        FundNetValue fundNetValue2 = new FundNetValue(calendar.getTime(), new BigDecimal(41.424));

        Date date = calendar.getTime();


        FundNetValue fundNetValue = new FundNetValue(new Date(), new BigDecimal(42.424));
        List<FundNetValue> fundNetValues = new ArrayList<>();
        fundNetValues.add(fundNetValue);
        fundNetValues.add(fundNetValue1);
        fundNetValues.add(fundNetValue2);

        FundAnalyseTool.analyse(fundNetValues);


    }
}

class FundAnalyseTool {
    public static BigDecimal analyse(List<FundNetValue> netValues) {
        TreeMap<Date, BigDecimal> netValueMap = new TreeMap<>();

        for (FundNetValue fundNetValue : netValues) {
            netValueMap.put(fundNetValue.getNetValueDate(), fundNetValue.getNetValue());
        }

        Collection<BigDecimal> fundNetValues = netValueMap.values();
        Set<Date> dateSet = netValueMap.keySet();
        Object[] dateArr = dateSet.toArray();
        Date today = (Date) dateArr[dateArr.length - 1];

        BigDecimal todayValue = netValueMap.get(today);
        //取最高净值
        BigDecimal maxValue = Collections.max(fundNetValues);

        //某一天净值的最大回撤率计算
        double retreatRatio = (todayValue.subtract(maxValue)).doubleValue() / todayValue.doubleValue();

        return new BigDecimal(retreatRatio);
    }
}