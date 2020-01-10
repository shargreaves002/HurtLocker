import org.apache.commons.io.IOUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Main {

    private String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        return IOUtils.toString(Objects.requireNonNull(classLoader.getResourceAsStream("RawData.txt")));
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        Parser parser = new Parser(output, "##");
        HashMap<String, List<String>> parsed = new HashMap<>(parser.parseToMap());
        Printer printer = new Printer();
        String result = printer.print(parsed);
        System.out.println(result);
    }
}
