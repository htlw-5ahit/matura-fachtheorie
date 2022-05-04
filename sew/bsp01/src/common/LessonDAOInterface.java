package common;

import java.sql.SQLException;
import java.util.Collection;

public interface LessonDAOInterface extends AutoCloseable {

    public int insertLesson(Lesson lesson) throws SQLException;
    public int insertLessons(Collection<Lesson> lessons) throws SQLException;
    public Collection<Lesson> getLessons() throws SQLException;

}
