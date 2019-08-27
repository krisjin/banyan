package org.banyan.calc.lr;

import java.util.ArrayList;

/**
 * User:shijingui
 * Date:2019-08-23
 */
public class Logistic {



    public static void main(String[] args) {
        colicTest();
    }
    /**
     * @author haolidong
     * @Description: [逻辑回归的简单测试]
     */
    public static void LogisticTest() {
        // TODO Auto-generated method stub
        CreateDataSet dataSet = new CreateDataSet();
        dataSet = readFile("I:\\machinelearninginaction\\Ch05\\testSet.txt");
        ArrayList<Double> weights = new ArrayList<Double>();
        weights = gradAscent1(dataSet, dataSet.lables, 150);
        for (inti = 0; i< 3; i++) {
            System.out.println(weights.get(i));
        }
        System.out.println();
    }
    /**
     * @paraminX
     * @param weights
     * @return
     * @author haolidong
     * @Description: [sigmod分类]
     */
    public static String classifyVector(ArrayList<String>inX, ArrayList<Double> weights) {
        ArrayList<Double> sum = new ArrayList<>();
        sum.clear();
        sum.add(0.0);
        for (inti = 0; i<inX.size(); i++) {
            sum.set(0, sum.get(0) + Double.parseDouble(inX.get(i)) * weights.get(i));
        }
        if (sigmoid(sum).get(0) > 0.5)
            return "1";
        else
            return "0";
    }
    /**
     * @author haolidong
     * @Description: [预测马的疝气病的死亡率]
     */
    public static void colicTest() {
        CreateDataSet trainingSet = new CreateDataSet();
        CreateDataSet  testSet = new CreateDataSet();
        trainingSet = readFile("I:\\machinelearninginaction\\Ch05\\horseColicTraining.txt");
        testSet = readFile("I:\\machinelearninginaction\\Ch05\\horseColicTest.txt");
        ArrayList<Double> weights = new ArrayList<Double>();
        weights = gradAscent1(trainingSet, trainingSet.lables, 500);
        interrorCount = 0;
        for (inti = 0; i<testSet.data.size(); i++) {
            if (!classifyVector(testSet.data.get(i), weights).equals(testSet.lables.get(i))) {
                errorCount++;
            }
            System.out.println(classifyVector(testSet.data.get(i), weights) + "," + testSet.lables.get(i));
        }
        System.out.println(1.0 * errorCount / testSet.data.size());
    }
    /**
     * @paraminX
     * @return
     * @author haolidong
     * @Description: [sigmod函数]
     */
    public static ArrayList<Double> sigmoid(ArrayList<Double>inX) {
        ArrayList<Double>inXExp = new ArrayList<Double>();
        for (inti = 0; i<inX.size(); i++) {
            inXExp.add(1.0 / (1 + Math.exp(-inX.get(i))));
        }
        return inXExp;
    }
    /**
     * @paramdataSet
     * @paramclassLabels
     * @paramnumberIter
     * @return
     * @author haolidong
     * @Description: [改进的随机梯度上升算法]
     */
    public static ArrayList<Double> gradAscent1(Matrix dataSet, ArrayList<String>classLabels, intnumberIter) {
        int m = dataSet.data.size();
        int n = dataSet.data.get(0).size();
        double alpha = 0.0;
        intrandIndex = 0;
        ArrayList<Double> weights = new ArrayList<Double>();
        ArrayList<Double>weightstmp = new ArrayList<Double>();
        ArrayList<Double> h = new ArrayList<Double>();
        ArrayList<Integer>dataIndex = new ArrayList<Integer>();
        ArrayList<Double>dataMatrixMulweights = new ArrayList<Double>();
        for (inti = 0; i< n; i++) {
            weights.add(1.0);
            weightstmp.add(1.0);
        }
        dataMatrixMulweights.add(0.0);
        double error = 0.0;
        for (int j = 0; j <numberIter; j++) {
            // 产生0到99的数组
            for (int p = 0; p < m; p++) {
                dataIndex.add(p);
            }
            // 进行每一次的训练
            for (inti = 0; i< m; i++) {
                alpha = 4 / (1.0 + i + j) + 0.0001;
                randIndex = (int) (Math.random() * dataIndex.size());
                dataIndex.remove(randIndex);
                double temp = 0.0;
                for (int k = 0; k < n; k++) {
                    temp = temp + Double.parseDouble(dataSet.data.get(randIndex).get(k)) * weights.get(k);
                }
                dataMatrixMulweights.set(0, temp);
                h = sigmoid(dataMatrixMulweights);
                error = Double.parseDouble(classLabels.get(randIndex)) - h.get(0);
                double tempweight = 0.0;
                for (int p = 0; p < n; p++) {
                    tempweight = alpha * Double.parseDouble(dataSet.data.get(randIndex).get(p)) * error;
                    weights.set(p, weights.get(p) + tempweight);
                }
            }
        }
        return weights;
    }
    public static CreateDataSetreadFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        CreateDataSetdataSet = new CreateDataSet();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                String[] strArr = tempString.split("\t");
                ArrayList<String> as = new ArrayList<String>();
                as.add("1");
                for (inti = 0; i<strArr.length - 1; i++) {
                    as.add(strArr[i]);
                }
                dataSet.data.add(as);
                dataSet.lables.add(strArr[strArr.length - 1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return dataSet;
    }
}
