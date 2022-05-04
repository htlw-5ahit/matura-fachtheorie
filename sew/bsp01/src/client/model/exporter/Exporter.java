package client.model.exporter;

import common.Lesson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface Exporter extends AutoCloseable {

    public void init() throws SQLException, IOException;
    public void insert(Lesson lesson) throws Exception;
    public void insert(Collection<Lesson> lessons) throws Exception;

    public Collection<Lesson> getAll() throws Exception;
}
