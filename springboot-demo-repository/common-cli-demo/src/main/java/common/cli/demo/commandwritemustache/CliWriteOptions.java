package common.cli.demo.commandwritemustache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.File;

public class CliWriteOptions {
    private static final HelpFormatter formatter = new HelpFormatter();
    private static final Options OPTIONS = new Options();
    private static BasicParser parser = new BasicParser();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    //todo Options need to be extended.
    public CliWriteOptions() {
        OPTIONS.addOption("i", true, "the input directory where the proto files are");
        OPTIONS.addOption("o", true, "the output directory which is the output path");
        OPTIONS.addOption("c",true,"whether we use xmlConfig.json or not");

        OPTIONS.addOption("h", true ,"get command input help");
        OPTIONS.addOption("help", true ,"get command input help");
    }

    public void runCommand(String[] args) throws Exception {
        XmlConfig xmlConfig = null;

        System.out.println(">>>>>>>>>> args:" + MAPPER.writeValueAsString(args));
        try {
            CommandLine cli = parser.parse(OPTIONS, args);
            String outputDirec = "";
            /**
             * 指定配置文件
             */
            if (cli.hasOption("c")) {
                String confPath = cli.getOptionValue("c");
                File configFile = new File(confPath);
                if (configFile.exists()) {
                    xmlConfig = MAPPER.readValue(configFile, XmlConfig.class);
                }
            }
            if (!cli.hasOption("o")) {
                System.out.println("please input output path");
                return;
            }
            outputDirec = cli.getOptionValue("o");
            if (null == xmlConfig) {
                //读取默认值
                xmlConfig = MAPPER.readValue(getClass().getClassLoader().getResourceAsStream("xmltemplate.json"), XmlConfig.class);
            }

            try {
                File outputFile = new File(outputDirec);
                outputFile.mkdirs();
                MustacheProcessor.process("generator.mustache",
                        new File(outputFile, "generator.xml").getAbsolutePath(), xmlConfig);
            } catch (Exception e) {
                System.out.println(e.toString());
                throw new Exception("Exception happens at .");
            }

            System.out.println(">>>>>>>>>> xmlConfig:" + MAPPER.writeValueAsString(xmlConfig));
        } catch (Exception e) {
            System.out.println(e.toString());
            formatter.printHelp("input -h or -help for more message", OPTIONS);
        }
    }

    /**
     * return the file path of CLI JAR
     * */
    public static String getCliJarPath(Class target) {
        java.net.URL url = target.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;
        try {
            filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar")) {
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        File file = new File(filePath);
        filePath = file.getAbsolutePath();
        return filePath;
    }
}
