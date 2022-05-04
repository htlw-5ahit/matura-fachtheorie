package server;

import common.DatabaseManager;
import common.Lesson;
import common.LessonDAOInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class DatabaseHandler implements AutoCloseable {

    private DatabaseManager dbmgr;
    private LessonDAOInterface lessonDAO;

    public DatabaseHandler(String propFile) throws SQLException, IOException {
        this.dbmgr = new DatabaseManager(propFile);
        this.lessonDAO = dbmgr.getLessonDAO();
    }

    public synchronized void insertLesson(Lesson lesson) throws Exception {
        lessonDAO.insertLesson(lesson);
    }

    public synchronized void insertLesson(Collection<Lesson> lessons) throws Exception {
        lessonDAO.insertLessons(lessons);
    }

    public Collection<Lesson> getAllLessons() throws Exception {
        return lessonDAO.getLessons();
    }

    @Override
    public void close() throws Exception {
        if (dbmgr != null)
            dbmgr.close();
    }
}
