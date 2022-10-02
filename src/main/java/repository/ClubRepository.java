package repository;

import java.sql.*;
import connection.*;
import entity.*;

public class ClubRepository {


    private static  final String SELECT_AVAILABLE_CLUBS_NAMES_QUERY = "select name from tbl_club where name = ? and league_id = ?";
    public boolean existsClub(int leagueID, String clubName) {
    try {
        Connection connection = DataBaseConnection.getInstance();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVAILABLE_CLUBS_NAMES_QUERY);
        preparedStatement.setString(1 , clubName);
        preparedStatement.setInt(2 , leagueID);

        ResultSet rs = preparedStatement.executeQuery();

        if(rs.next())
                return true;


            preparedStatement.close();



    }
    catch (Exception e){
        e.printStackTrace();
    }
    return false;
}
//    ----------------------------------------------------------------------------------------------

    private static final String CREATE_CLUB_QUERY = "INSERT INTO tbl_club (id, name, league_id) values (?, ?, ?)";
    public boolean create(Club club){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLUB_QUERY);
            preparedStatement.setInt(1 , club.getId());
            preparedStatement.setString(2 , club.getName());
            preparedStatement.setInt(3 , club.getLeagueID());


            if(preparedStatement.executeUpdate()>0)
            return true;
            preparedStatement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    //    ----------------------------------------------------------------------------------------------

    private static final String SELECT_LEAGUE_CLUBS_QUERY = "select id, name from tbl_club where league_id = ?";
    public boolean leagueClubs(int leagueID){
        try {
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LEAGUE_CLUBS_QUERY);
            preparedStatement.setInt(1 , leagueID);
    
            ResultSet rs = preparedStatement.executeQuery();
    
            if (!rs.isBeforeFirst() ) {
                return false;
            }
            else{

                System.out.printf("%nClub ID     Club name");
                System.out.printf("%n-------------------------");
                while(rs.next()){
                    System.out.printf("%n%-12d%-14s", rs.getInt("id"), rs.getString("name").substring(0, 1).toUpperCase()+rs.getString("name").substring(1));
                }
                System.out.printf("%n%n");
                preparedStatement.close();
                return true;      
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    //    ----------------------------------------------------------------------------------------------

    private static  final String DELETE_CLUBS_QUERY = "delete from tbl_club where name = ? and league_id = ?";
    /**
     * @param clubName
     * @return
     */
    public boolean remove(int leagueID, String clubName){
    try {
        Connection connection = DataBaseConnection.getInstance();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLUBS_QUERY);
        preparedStatement.setString(1 , clubName);
        preparedStatement.setInt(2 , leagueID);

        
        if(preparedStatement.executeUpdate()>0)
                return true;

            preparedStatement.close();

    }
    catch (Exception e){
        e.printStackTrace();
    }
    return false;
}
//    ----------------------------------------------------------------------------------------------




//    private static final String INSERT_QUERY = "INSERT INTO users (username, password, national_code, birthday) values (?, ?, ?, ?)";
//    public int insertUser(Club club){
//        int result=0;
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
//            preparedStatement.setString(1 , club.getUsername().toLowerCase());
//            preparedStatement.setString(2 , club.getPassword());
//            preparedStatement.setString(3 , club.getNationalCOde());
//            preparedStatement.setString(4 , club.getBirthday());
//
//            result = preparedStatement.executeUpdate();
//
//            preparedStatement.close();
//
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    private static final String GET_USER_ID = "SELECT id FROM users WHERE username = ?";
//    public int getUserID(String username){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ID);
//            preparedStatement.setString(1, username);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next())
//                return rs.getInt("id");
//            preparedStatement.close();
//
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    private static  final String CHECK_USERNAME_BY_USERNAME = "select username from USERS where username = ?" ;
//    public Boolean checkUserByUsername(String username){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME_BY_USERNAME);
//            preparedStatement.setString(1 , username);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next())
//                return true;
//            preparedStatement.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    private static  final String CHECK_USERNAME_BY_OLD_PASSWORD = "select username from USERS where username = ? and password = ?" ;
//    public Boolean checkUserByOldPassword(String username, String oldPassword){
//        try{
////            System.out.println(username+" / "+oldPassword);
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME_BY_OLD_PASSWORD);
//            preparedStatement.setString(1 , username);
//            preparedStatement.setString(2 , oldPassword);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next())
//                return true;
//            preparedStatement.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    private static  final String CHANGE_PASSWORD = "update USERS set password = ? where username = ?" ;
//    public Boolean updatePassword(String username, String newPassword){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD);
//            preparedStatement.setString(1 , newPassword);
//            preparedStatement.setString(2 , username);
//
//            if (preparedStatement.executeUpdate()>0)
//                return true;
//            preparedStatement.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    private static  final String SIGNIN_USERNAME_PASSWORD = "select username, password from USERS where username = ? and password = ?" ;
//    public Boolean checkSignIn(String username, String password){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(SIGNIN_USERNAME_PASSWORD);
//            preparedStatement.setString(1 , username);
//            preparedStatement.setString(2 , password);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next())
//                return true;
//            preparedStatement.close();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }

}
