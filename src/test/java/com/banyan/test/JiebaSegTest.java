package com.banyan.test;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.jd.jr.jseg.Jseg;
import org.junit.Test;

import java.util.List;


/**
 * @User krisjin
 * @date 2020/9/3
 */
public class JiebaSegTest {

    @Test
    public void test() {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        String sentences = "点点app借一万多少利息";
        System.out.println(segmenter.sentenceProcess(sentences));

        String text = "点点app";
        List<String> termList = Jseg.conciseSeg(text, true);//不使用停用词
        System.err.println(termList);
    }

}
