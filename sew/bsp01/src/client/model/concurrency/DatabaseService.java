package client.model.concurrency;

import client.model.exporter.Exporter;
import client.model.importer.Importer;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.Collection;

public class DatabaseService extends Service<Collection> {

    private Importer importer;
    private Exporter exporter;

    public DatabaseService() {
    }

    public DatabaseService(Importer importer, Exporter exporter) {
        this.importer = importer;
        this.exporter = exporter;
    }

    public void setImporter(Importer importer) {
        this.importer = importer;
    }

    public void setExporter(Exporter exporter) {
        this.exporter = exporter;
    }

    @Override
    protected Task<Collection> createTask() {
        return new DatabaseTask(importer, exporter);
    }
}
