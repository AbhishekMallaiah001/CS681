package edu.umb.cs681.hw3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PVIDataParse {

    public static void main(String args[]) throws IOException {
        Path currentDir = FileSystems.getDefault().getPath("").toAbsolutePath();
        String pathName = currentDir + "\\PVI_data.csv";
        Path path = Paths.get(pathName);
        final int STATEINDEX = 4;
        final int COUNTYINDEX = 5;
        final int CASEINDEX = 6;


        try (Stream<String> lines = Files.lines(path)) {
            List<List<String>> pviData = lines.map(
                    line -> Stream.of(line.split(",")).collect(Collectors.toList())
            ).collect(Collectors.toList());

            List stateMA = pviData.stream().filter((i) -> i.get(STATEINDEX).contains("Massachusetts")).collect(Collectors.toList());

            List suffolk = pviData.stream().filter((i) -> i.get(COUNTYINDEX).contains("Suffolk")).collect(Collectors.toList()).get(0);
            System.out.println("\n==> Total cases found in Suffolk county:   " + suffolk.get(CASEINDEX));

            String casesInMA = pviData.stream().filter((i) -> i.get(STATEINDEX).contains("Massachusetts"))
                    .map((i) -> i.get(CASEINDEX)).reduce("0", (subtotal, element) -> String.valueOf(Integer.parseInt(subtotal) + Integer.parseInt(element)));

            System.out.println("\n==> Total Csess in Massachussetts  State :      " + casesInMA);

            System.out.println("\n==> MEAN of the total cases found in MAsschussetts State:     " + Integer.parseInt(casesInMA) / stateMA.size());

            float suffolkCasePercent = (Float.parseFloat(suffolk.get(CASEINDEX).toString())/Float.parseFloat(casesInMA)) * 100;
            System.out.println("\n==> The Percentage of cases in Suffolk county with respect to MA state:  "+suffolkCasePercent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}