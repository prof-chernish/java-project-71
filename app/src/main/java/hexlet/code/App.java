package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true)

public class App implements Callable<Integer> {
    @Option(names = { "-f", "--format" }, description = "output format [default: stylish]", defaultValue = "stylish")
    String format = "stylish";

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filePath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filePath2;

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(filePath1, filePath2));
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);

        System.exit(exitCode);
    }
}
