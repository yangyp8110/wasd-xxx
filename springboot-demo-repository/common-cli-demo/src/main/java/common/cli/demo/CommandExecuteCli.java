package common.cli.demo;

import common.cli.demo.commandwritemustache.CliWriteOptions;
import common.cli.demo.mycommand.CliOptions;

/**
 * Created by mr.yang on 2018/5/6.
 */
public class CommandExecuteCli {
    public static void main(String[] args) throws Exception {
        System.out.println(">>>>>>>>>> start. args:" + args);
        try {
//            CliOptions cliOptions = new CliOptions();
//            cliOptions.runCommand(args);

            new CliWriteOptions().runCommand(args);
        } catch (Exception ex) {
            System.out.println("============= input help or h for more message =============");
        }
    }
}
