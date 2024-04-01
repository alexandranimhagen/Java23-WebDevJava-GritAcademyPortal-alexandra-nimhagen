import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private Connection connection;

    public TeacherDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM Teachers";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setfName(resultSet.getString("fName"));
                teacher.setlName(resultSet.getString("lName"));
                teacher.setTown(resultSet.getString("town"));
                teacher.setEmail(resultSet.getString("email"));
                teacher.setPhone(resultSet.getString("phone"));
                teacher.setUsername(resultSet.getString("username"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setPrivilegeType(resultSet.getString("privilege_type"));
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    // Add more methods for specific CRUD operations as needed
}
