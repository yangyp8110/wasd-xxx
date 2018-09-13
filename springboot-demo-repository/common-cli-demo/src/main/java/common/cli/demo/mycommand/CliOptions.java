package common.cli.demo.mycommand;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by mr.yang on 2018/5/6.
 */
public class CliOptions {
    private static final HelpFormatter formatter = new HelpFormatter();
    private static final Options OPTIONS = new Options();
    private static BasicParser parser = new BasicParser();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    //todo Options need to be extended.
    public CliOptions() {
        OPTIONS.addOption("i", true, "the input directory where the config files are");
        OPTIONS.addOption("o", true, "the output directory which is the output path");
        OPTIONS.addOption("c",true,"whether we use config.json or not");

        OPTIONS.addOption("groupId",true,"maven project related parameter");
        OPTIONS.addOption("artifactId",true,"maven project related parameter");
        OPTIONS.addOption("artifactVersion",true,"maven project related parameter");

        OPTIONS.addOption("h", true ,"get command input help");
        OPTIONS.addOption("help", true ,"get command input help");
    }

    public void runCommand(String[] args) throws Exception {
        CommandConfig commandConfig = null;

        System.out.println(">>>>>>>>>> args:" + MAPPER.writeValueAsString(args));
        try {
            CommandLine cli = parser.parse(OPTIONS, args);

            if (cli.hasOption("h") || cli.hasOption("help")) {
                System.out.println("============= input command tips =============");
                System.out.println("-i                         the input directory where the proto files are");
                System.out.println("-o                         the output directory which is the output path");
                System.out.println("-c                         use your config.json path");

                System.out.println("-groupId                   maven project related parameter");
                System.out.println("-artifactId                maven project related parameter");
                System.out.println("-artifactVersion           maven project related parameter");
                System.out.println("-h                         get command input help");
                System.out.println("-help                      get command input help");
                System.out.println("============= input command tips =============");

                return;
            }

            /**
             * 指定配置文件
             */
            if (cli.hasOption("c")) {
                String confPath = cli.getOptionValue("c");
                File configFile = new File(confPath);
                if (configFile.exists()) {
                    commandConfig = MAPPER.readValue(configFile, CommandConfig.class);
                }
            }
            if (commandConfig == null) {
                //读取默认的配置文件
                commandConfig = MAPPER.readValue(new File(getCliJarPath(this.getClass()) + File.separatorChar + "config.json"), CommandConfig.class);
            }
            if (cli.hasOption("o")) {
                commandConfig.setOutputDirectory(cli.getOptionValue("o"));
            }

            System.out.println(">>>>>>>>>> commandConfig:" + MAPPER.writeValueAsString(commandConfig));
        } catch (Exception e) {
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
