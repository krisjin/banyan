package org.banyan.knn.main;

import org.banyan.knn.data.BankMarketing;
import org.banyan.knn.loader.BankMarketingLoader;
import org.banyan.knn.serial.KnnClassifier;

import java.util.Date;
import java.util.List;


/**
 * Main class that launches the tests using the serial knn with serial sorting
 *
 * @author Usuario
 */
public class SerialMain {

    public static void main(String[] args) {

        BankMarketingLoader loader = new BankMarketingLoader();
        List<BankMarketing> train = loader.load("/usr/local/gitrep/banyan/target/classes/data/bank.data");
        System.out.println("Train: " + train.size());
        List<BankMarketing> test = loader.load("/usr/local/gitrep/banyan/target/classes/data/bank.test");
        System.out.println("Test: " + test.size());
        double currentTime = 0d;
        int success = 0, mistakes = 0;

        int k = 10;
        if (args.length == 1) {
            k = Integer.parseInt(args[0]);
        }

        success = 0;
        mistakes = 0;
        KnnClassifier classifier = new KnnClassifier(train, k);
        try {
            Date start, end;
            start = new Date();
            for (BankMarketing example : test) {
                String tag = classifier.classify(example);
                if (tag.equals(example.getTag())) {
                    success++;
                } else {
                    mistakes++;
                }
            }
            end = new Date();

            currentTime = end.getTime() - start.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("******************************************");
        System.out.println("Serial Classifier - K: " + k);
        System.out.println("Success: " + success);
        System.out.println("Mistakes: " + mistakes);
        System.out.println("Execution Time: " + (currentTime / 1000)
                + " seconds.");
        System.out.println("******************************************");

    }

}
