import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String args[]) throws IOException {
        //String fileName = "D:\\HesSo\\ProjetHes\\vijava\\src\\resource\\data.csv";
        //String fileName = "C:\\Users\\dma\\Downloads\\en.openfoodfacts.org.products.csv";
        String filename = "data\\en.openfoodfacts.org.products.csv";
        String workingDirectory = System.getProperty("user.dir");
        String absoluteFilePath = workingDirectory + File.separator + filename;
        System.out.println("Final filepath : " + absoluteFilePath);

        toJsonFile(createList(absoluteFilePath));
    }

    private static List<Map<String, String>> createList(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.map(ligne -> ligne.split("\t"))
                         .parallel()
                         .filter(values -> values.length > findIndexMax()
                                 && values[Champs.NUTRITION.getIndex()].length() == 1
                                 && hasAllChamps(values))
                         .peek(values -> values[Champs.PAYS.getIndex()] = replaceTextInBrand(values[Champs.PAYS.getIndex()].trim().split(",")[0]))
                         .peek(values -> values[Champs.MARQUE.getIndex()] = values[Champs.MARQUE.getIndex()].split(",")[0])
                         .map(values -> Arrays.stream(Champs.values())
                                              .collect(Collectors.toMap(v -> v.name().toLowerCase(), v -> values[v.getIndex()])))
                         .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static String replaceTextInBrand(String brand) {
        Map<String, String> replaceString = new HashMap<>();
        replaceString.put("en:", "");
        replaceString.put("nl:", "");
        replaceString.put("fr:", "");
        replaceString.put("UK", "United Kingdom");
        replaceString.put("US", "United States");
        replaceString.put("Estados Unidos", "United States");
        replaceString.put("Australia", "Australie");
        replaceString.put("Austria", "Autriche");
        replaceString.put("Österreich", "Autriche");
        replaceString.put("BE", "Belgique");
        replaceString.put("Belgium", "Belgique");
        replaceString.put("België", "Belgique");
        replaceString.put("BG", "Belgique");
        replaceString.put("Belgien", "Belgique");
        replaceString.put("Brasil", "Brazil");
        replaceString.put("CA", "Canada");
        replaceString.put("CH", "Suisse");
        replaceString.put("Switzerland", "Suisse");
        replaceString.put("Schweiz", "Suisse");
        replaceString.put("Denmark", "Danemark");
        replaceString.put("Deutschland", "Allemagne");
        replaceString.put("Germany", "Allemagne");
        replaceString.put("germany", "Allemagne");
        replaceString.put("DE", "Allemagne");
        replaceString.put("ES", "Espagne");
        replaceString.put("España", "Espagne");
        replaceString.put("spain", "Espagne");
        replaceString.put("Spain", "Espagne");
        replaceString.put("FR", "France");
        replaceString.put("france", "France");
        replaceString.put("Francia", "France");
        replaceString.put("Frankreich", "France");
        replaceString.put("Frankrijk", "France");
        replaceString.put("Frankrike", "France");
        replaceString.put("IT", "Italie");
        replaceString.put("Italia", "Italie");
        replaceString.put("Italy", "Italie");
        replaceString.put("MEXICO", "Mexique");
        replaceString.put("MÉXICO", "Mexique");
        replaceString.put("México", "Mexique");
        replaceString.put("Mexico", "Mexique");
        replaceString.put("Magyarország", "Hongrie");
        replaceString.put("Hungary", "Hongrie");
        replaceString.put("Netherlands", "Nederland");
        replaceString.put("Norway", "Norvège");
        replaceString.put("Poland", "Pologne");
        replaceString.put("România", "Roumanie");
        replaceString.put("Romania", "Roumanie");
        replaceString.put("Sverige", "Suède");
        replaceString.put("Sweden", "Suède");
        replaceString.put("Česko", "République tchèque");
        replaceString.put("Россия", "Russie");
        replaceString.put("Србија", "Serbie");
        replaceString.put("Serbia", "Serbie");
        replaceString.put("საქართველო", "Géorgie");
        replaceString.put("GB", "Grande-Bretagne");
        replaceString.put("United Kingdom", "Royaume-Uni");
        replaceString.put("New Zealand", "Nouvelle-Zélande");
        replaceString.put("Nederland", "Pays-Bas");

        for (Map.Entry<String, String> v : replaceString.entrySet()) {
            brand = brand.replace(v.getKey(), v.getValue());
        }
        return brand;
    }

    private static void toJsonFile(List<Map<String, String>> list) throws IOException {
        Map<String, Integer> l = new HashMap<>();
        list.forEach(o -> {
            String m = o.get(Champs.PAYS.toString().toLowerCase());
            if (!l.containsKey(m)) {
                l.put(m, 1);
            } else {
                l.put(m, l.get(m) + 1);
            }
        });

        list =  list.stream().filter(map -> {
            String pays = map.get("pays");
            Integer nbProduct = l.get(pays);
            return l.get(pays) > 20;
        }).collect(Collectors.toList());

        l.entrySet().stream().filter(o -> o.getValue() > 20).map(o -> o.getKey() + " " + o.getValue()).sorted(String::compareTo)
         .forEach(o -> System.out.println(o));
        File file = new File("data.json");
       //    list = partition(list, 10).iterator().next();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getPath()))) {
            writer.write("var data = " + new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(list));
        }
    }

    private static <T> Collection<List<T>> partition(List<T> list, int size) {
        final AtomicInteger counter = new AtomicInteger(0);

        return list.stream()
                   .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / size))
                   .values();
    }

    private static int findIndexMax() {
        return Arrays.stream(Champs.values()).map(Champs::getIndex).max(Integer::compare).get();
    }

    private static boolean hasAllChamps(String[] values) {
        return Arrays.stream(Champs.values()).allMatch(champs -> values[champs.getIndex()].trim().length() != 0);
    }
}
