package common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

public class LessonDAO implements LessonDAOInterface {

    private DatabaseManager dbmgr;
    private PreparedStatement insertStmt, selectAllStmt;

    public LessonDAO(DatabaseManager dbmgr) throws SQLException {
        this.dbmgr = dbmgr;
        initSchema();
        initStatements();
    }

    private void initStatements() throws SQLException {
        insertStmt = dbmgr.getConnection().prepareStatement("INSERT INTO LESSON (ID,TEACHER,CLASSROOM,SUBJECT,DAY,LESSON) VALUES (?,?,?,?,?,?)");
        selectAllStmt = dbmgr.getConnection().prepareStatement("SELECT * FROM LESSON");
    }

    private void initSchema() throws SQLException {
        try (Statement stmt = dbmgr.getConnection().createStatement()) {
            stmt.execute("DELETE FROM LESSON");
        }
    }

    @Override
    public int insertLesson(Lesson lesson) throws SQLException {
        insertStmt.clearParameters();
        insertStmt.setString(1, lesson.getId());
        insertStmt.setString(2, lesson.getTeacher());
        insertStmt.setString(3, lesson.getClassroom());
        insertStmt.setString(4, lesson.getSubject());
        insertStmt.setInt(5, lesson.getDay());
        insertStmt.setInt(6, lesson.getLesson());
        return insertStmt.executeUpdate();
    }

    @Override
    public int insertLessons(Collection<Lesson> lessons) throws SQLException {
        int amount = 0;
        // iterate lessons and save inserted lessons as result value
        for (Lesson lesson : lessons)
            amount += insertLesson(lesson);
        return amount;
    }

    @Override
    public Collection<Lesson> getLessons() throws SQLException {
        HashSet<Lesson> lessons = new HashSet<>();
        try (ResultSet rs = selectAllStmt.executeQuery()) {
            while (rs.next()) {
                lessons.add(new Lesson(rs.getString("ID"), rs.getString("TEACHER"),
                        rs.getString("CLASSROOM"), rs.getString("SUBJECT"),
                        rs.getInt("DAY"), rs.getInt("LESSON")));
            }
        }
        return lessons;
    }

    @Override
    public void close() throws Exception {
        if (insertStmt != null) insertStmt.close();
        if (selectAllStmt != null) selectAllStmt.close();
    }
}
