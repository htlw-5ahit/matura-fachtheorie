package client.model.exporter;

import common.DatabaseManager;
import common.Lesson;
import common.LessonDAOInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class DatabaseExporter implements Exporter {

    private String propFile;
    private DatabaseManager dbmgr;
    private LessonDAOInterface lessonDAO;

    public DatabaseExporter(String propFile) {
        this.propFile = propFile;
    }

    @Override
    public void init() throws SQLException, IOException {
        this.dbmgr = new DatabaseManager(propFile);
        this.lessonDAO = dbmgr.getLessonDAO();
    }

    @Override
    public void insert(Lesson lesson) throws Exception {
        lessonDAO.insertLesson(lesson);
    }

    @Override
    public void insert(Collection<Lesson> lessons) throws Exception {
        lessonDAO.insertLessons(lessons);
    }

    @Override
    public Collection<Lesson> getAll() throws Exception {
        return lessonDAO.getLessons();
    }

    @Override
    public void close() throws Exception {
        if (dbmgr != null)
            dbmgr.close();
    }
}
