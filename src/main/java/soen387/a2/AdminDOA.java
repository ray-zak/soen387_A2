package soen387.a2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminDOA {

    public int verify_Admin(int adminID){

        String SELECT_ADMIN_SQL = "SELECT * FROM Assignment1.Administrator WHERE " + "EmployementID='" + adminID + "';";

        ResultSet result;
        Connection conn = DB_Connection.connectDB();

        try{
            PreparedStatement preparedStatement = conn.prepareStatement((SELECT_ADMIN_SQL));
            System.out.println(preparedStatement);

            result =  preparedStatement.executeQuery();
           // System.out.println(result);
            System.out.println(result.isBeforeFirst());

            if (result.isBeforeFirst()){
                return 1;
            }
            else{
                return 0;
            }

        }catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return 0;
    }

    public int registerAdmin(Admin admin)  {

        String INSERT_USERS_SQL ="INSERT INTO assignment1.administrator" + " (EmployementID,Address,DOB,Email,Phone,FirstName,LastName) VALUES " +
                " (?,?,?,?,?,?,?);";


        int result = 0;
        Connection conn = DB_Connection.connectDB();


        try{
            PreparedStatement preparedStatement = conn.prepareStatement((INSERT_USERS_SQL));

            preparedStatement.setInt(1, admin.getID());
            preparedStatement.setString(2, admin.getAddress());
            preparedStatement.setString(3, admin.getDOB());
            preparedStatement.setString(4, admin.getEmail());
            preparedStatement.setInt(5, admin.getPhone());
            preparedStatement.setString(6, admin.getFirstname());
            preparedStatement.setString(7, admin.getLastname());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();


        }catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

            return result;
    }


    public int verifyCourse(String courseCode){

        String SELECT_COURSE_SQL = "SELECT * FROM assignment1.course WHERE " + "CourseCode='" +  courseCode+ "';";

        ResultSet result;
        Connection conn = DB_Connection.connectDB();

        try{
            PreparedStatement preparedStatement = conn.prepareStatement((SELECT_COURSE_SQL));
            System.out.println(preparedStatement);

            result =  preparedStatement.executeQuery();

            System.out.println(result.isBeforeFirst());

            if (result.isBeforeFirst()){
                return 1;
            }
            else{
                return 0;
            }

        }catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }


        return 0;
    }
    public int createCourse(course newCourse){

        String INSERT_COURSE_SQL ="INSERT INTO Assignment1.Course" + " (CourseCode,Title,Semester,days,time,instructor,room,StartDate,EndDate) VALUES " +
                " (?,?,?,?,?,?,?,?,?);";

        int result = 0;

        System.out.println(newCourse.getTime());

        Connection conn = DB_Connection.connectDB();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement((INSERT_COURSE_SQL));

            preparedStatement.setString(1,newCourse.getCourseCode());
            preparedStatement.setString(2,newCourse.getTitle());
            preparedStatement.setString(3,newCourse.getSemester());
            preparedStatement.setString(4, newCourse.getDays());
            preparedStatement.setString(5, String.valueOf(newCourse.getTime()));
            preparedStatement.setString(6, newCourse.getInstructor());
            preparedStatement.setString(7, newCourse.getRoom());
            preparedStatement.setString(8, String.valueOf(newCourse.getStartDate()));
            preparedStatement.setString(9, String.valueOf(newCourse.getEndDate()));

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        }catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }


        return result;
    }



    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }






}
