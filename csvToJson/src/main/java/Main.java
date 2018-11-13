import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String args[]) throws IOException {
        //String fileName = "D:\\HesSo\\ProjetHes\\vijava\\src\\resource\\data.csv";
        String fileName = "C:\\Users\\dma\\Downloads\\en.openfoodfacts.org.products.csv";
        toJsonFile(createList(fileName));
    }

    private static List<Map<String, String>> createList(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.map(ligne -> ligne.split("\t"))
                         .parallel()
                         .filter(values -> values.length > findIndexMax()
                                 && values[Champs.NUTRITION.getIndex()].length() == 1
                                 && hasAllChamps(values))
                         .map(values -> Arrays.stream(Champs.values())
                                              .collect(Collectors.toMap(v -> v.name().toLowerCase(), v -> values[v.getIndex()])))
                         .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void toJsonFile(List<Map<String, String>> list) throws IOException {
        new ObjectMapper().writeValue(new File("data.json"), list);
    }

    private static int findIndexMax() {
        return Arrays.stream(Champs.values()).map(Champs::getIndex).max(Integer::compare).get();
    }

    private static boolean hasAllChamps(String[] values) {
        return Arrays.stream(Champs.values()).allMatch(champs -> values[champs.getIndex()].trim().length() != 0);
    }
}
