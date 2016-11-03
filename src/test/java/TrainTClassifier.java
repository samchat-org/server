import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.TfIdfClassifierTrainer;
import com.aliasi.tokenizer.CharacterTokenizerFactory;
import com.aliasi.tokenizer.TokenFeatureExtractor;
import com.aliasi.util.Files;

public class TrainTClassifier {  
  
    //训练语料文件夹  
    private static File TDIR = new File("d:\\lingpipe\\training");  
    //定义分类  
    private static String[] CATEGORIES = {"金融学","军事"};  
  
    public static void main(String[] args) throws ClassNotFoundException,  
            IOException {  
          
        TfIdfClassifierTrainer<CharSequence> classifier = new TfIdfClassifierTrainer<CharSequence>(  
                new TokenFeatureExtractor(CharacterTokenizerFactory.INSTANCE));  
  
        // 开始训练  
        for (int i = 0; i < CATEGORIES.length; i++) {  
            File classDir = new File(TDIR, CATEGORIES[i]);  
            if (!classDir.isDirectory()) {  
                System.out.println("不能找到目录=" + classDir);  
            }  
  
            // 训练器遍历分类文件夹下的所有文件  
            for (File file : classDir.listFiles()) {  
                String text = Files.readFromFile(file, "utf-8");  
                System.out.println("正在训练 " + CATEGORIES[i] + ":" + file.getName());  
                Classification classification = new Classification(  
                        CATEGORIES[i]);  
                Classified<CharSequence> classified = new Classified<CharSequence>(  
                        text, classification);  
                classifier.handle(classified);  
            }   
        }  
          
  
        // 把分类器模型写到文件上  
        System.out.println("开始生成分类器");  
        String modelFile = "d:\\lingpipe\\tclassifier";  
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(  
                modelFile));  
        classifier.compileTo(os);  
        os.close();  
          
        System.out.println("分类器生成完成");  
    }  
}