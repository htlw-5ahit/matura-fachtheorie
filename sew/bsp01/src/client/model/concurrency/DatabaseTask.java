package client.model.concurrency;

import client.model.exporter.Exporter;
import client.model.importer.Importer;
import common.Lesson;
import javafx.concurrent.Task;

import java.util.Collection;
import java.util.HashSet;

public class DatabaseTask extends Task<Collection> {

    private Importer importer;
    private Exporter exporter;

    public DatabaseTask(Importer importer, Exporter exporter) {
        this.importer = importer;
        this.exporter = exporter;
    }

    @Override
    protected Collection call() throws Exception {
        Collection<Lesson> retLessons;

        try {
            // import lessons
            HashSet<Lesson> lessons = importer.start();

            // export lessons
            exporter.init();
            exporter.insert(lessons);

            // get all lessons
            retLessons = exporter.getAll();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (exporter != null)
                exporter.close();
        }
        return retLessons;
    }
}
