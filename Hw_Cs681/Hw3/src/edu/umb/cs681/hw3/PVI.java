package edu.umb.cs681.hw3;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PVI {

    public static void main(String args[]) throws IOException {
        Path tmp = FileSystems.getDefault().getPath("").toAbsolutePath();
        String pathName = tmp+"\\PVI2.csv";
        Path path = Paths.get (pathName);

        System.out.println(pathName);

        try (Stream<String> lines = Files.lines(path)) {
            List<List<String>> matrix = lines.map(line -> {
                return Stream.of( line.split(",") ) .map(value->value.substring(0, value.length()))
                        .collect( Collectors.toList() ); }) .collect( Collectors.toList() );
            System.out.println(matrix);

            List Mass = matrix.stream().filter((i) -> i.get(4).contains("Massachusetts")).collect(Collectors.toList());

            List suffolk = matrix.stream().filter((i) -> i.get(5).contains("Suffolk")).collect(Collectors.toList()).get(0);
            System.out.println("\n==> Deaths in Suffolk county of the Mass state are:   " + suffolk.get(7));

            String deathInMA = matrix.stream().filter((i) -> i.get(4).contains("Massachusetts"))
                    .map((i) -> i.get(7)).reduce("0", (subtotal, element) -> String.valueOf(Integer.parseInt(subtotal) + Integer.parseInt(element)));

            System.out.println("\n==> Total deaths in Mass State are:                   " + deathInMA);

            System.out.println("\n==> Average deaths in Mass State are:                 " + Integer.parseInt(deathInMA)/Mass.size());

        } catch (IOException ex) {
            System.out.println("Exception Occured");
        }
    }

}