package scheduler.CSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    public String[][] read(String fileName) {
        List<String[]> lines = new ArrayList<>();
        File file = new File(fileName);
        String absolutePath = file.getAbsolutePath();
        try (CSVReader reader = new CSVReader(new FileReader(absolutePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            // handle exception
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return lines.toArray(new String[0][]);
    }
}