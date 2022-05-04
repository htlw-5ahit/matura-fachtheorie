package client.model.importer;

import common.Lesson;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class CSVImporter implements Importer {

    private File file;
    private String delimiter = "\n", separator = "\t";

    public CSVImporter(File file) {
        this.file = file;
    }

    public HashSet<Lesson> start() throws FileNotFoundException {
        HashSet<Lesson> lessons = new HashSet<>();
        try (Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)))) {
            String[] input;
            Lesson lesson;

            scanner.useDelimiter(delimiter); // set delimiter => next line
            scanner.next(); // skip csv header

            // iterate file line for line
            while (scanner.hasNext()) {
                // read line and split into pieces
                input = scanner.next().split(separator);

                // create lesson array out of read line
                lesson = new Lesson(input[0], input[1], input[2], input[3], Integer.parseInt(input[4]), Integer.parseInt(input[5]));

                // add lesson to array
                lessons.add(lesson);
            }
        }
        return lessons;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
