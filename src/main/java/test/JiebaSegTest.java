package test;

import com.huaban.analysis.jieba.JiebaSegmenter;
import org.junit.Test;

/**
 * @User krisjin
 * @date 2020/9/3
 */
public class JiebaSegTest {

    @Test
    public void test() {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        String sentences = "习大大的演讲";
        System.out.println(segmenter.sentenceProcess(sentences));
    }

}
