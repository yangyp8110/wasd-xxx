package common.cli.demo.commandwritemustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MustacheProcessor {

    /**
     * 按模板生成文件
     * @param mustachePath 模板文件
     * @param outputDes 输出路径
     * @param parameter 填充模板的参数
     * @throws Exception
     */
    public static void process(String mustachePath,String outputDes,Object parameter)throws Exception{
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outputDes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Error happens at MustacheProcessor.");
        }
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(mustachePath);
        mustache.execute(writer, parameter);
        writer.flush();
    }

}
