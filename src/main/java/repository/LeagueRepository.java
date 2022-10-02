package repository;

import connection.*;
import entity.*;
import java.sql.*;



public class LeagueRepository {

    private static  final String SELECT_AVAILABLE_LEAGUES_QUERY = "select * from tbl_league";
    public boolean displayAvailableLeagues() {
        try {
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVAILABLE_LEAGUES_QUERY);

            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst() ) {
                return false;
            }
            else{

                System.out.printf("League ID     League name");
                System.out.printf("%n-------------------------");
                while(rs.next()){
                    System.out.printf("%n%-14d%-14s", rs.getInt("id"), rs.getString("name").substring(0, 1).toUpperCase()+rs.getString("name").substring(1));
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

private static  final String SELECT_AVAILABLE_LEAGUES_NAMES_QUERY = "select name from tbl_league where name = ?";
public boolean existsLeague(String leagueName) {
    try {
        Connection connection = DataBaseConnection.getInstance();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVAILABLE_LEAGUES_NAMES_QUERY);
        preparedStatement.setString(1 , leagueName);

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


    private static final String CREATE_LEAGUE_QUERY = "INSERT INTO tbl_league (id, name) values (?, ?)";
    public void create(League league){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LEAGUE_QUERY);
            preparedStatement.setInt(1 , league.getId());
            preparedStatement.setString(2 , league.getName());


            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.printf("League %s created successfully.%n%n", league.getName().toUpperCase());

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
//    ----------------------------------------------------------------------------------------------


//    ----------------------------------------------------------------------------------------------
// private static  final String SELECT_AVAILABLE_LEAGUES_IDs_QUERY = "select id from tbl_league where id = ?";
//     public boolean existsLeagueID(String leagueID) {
//         try {
//             Connection connection = DataBaseConnection.getInstance();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVAILABLE_LEAGUES_IDs_QUERY);
//             preparedStatement.setInt(1 , Integer.parseInt(leagueID));

//             ResultSet rs = preparedStatement.executeQuery();

//             if(rs.next())
//                     return true;


//                 preparedStatement.close();



//         }
//         catch (Exception e){
//             e.printStackTrace();
//         }
//         return false;
//     }
//    ----------------------------------------------------------------------------------------------


    

//    public void showAllArticles(){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IS_PUBLISHED_ARTICLES);
//            //preparedStatement.setString(1 , username);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()){
//                System.out.println("Article id   : "+rs.getInt("id"));
//                System.out.println("Article title: "+rs.getString("title"));
//                System.out.println("Article brief: "+rs.getString("brief"));
//                System.out.println("Creation Date: "+rs.getString("create_date"));
//                System.out.println("Author name  : "+rs.getString("username"));
//            }
//            preparedStatement.close();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    private static  final String SELECT_MY_ARTICLES = "select id, title, brief, content, create_date from articles  where user_id = ?" ;
//    public void findMyArticle(String searchCase, int userID){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MY_ARTICLES);
//            preparedStatement.setInt(1 , userID);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//                while (rs.next()){
//                    if (rs.getString("title").contains(searchCase) ||
//                            rs.getString("brief").contains(searchCase) ||
//                            rs.getString("content").contains(searchCase)){
//                        System.out.println("Article id   : "+rs.getInt("id"));
//                        System.out.println("Article title: "+rs.getString("title"));
//                        System.out.println("Article brief: "+rs.getString("brief"));
//                        System.out.println("Creation Date: "+rs.getString("create_date"));
//                    }
//                    else
//                        System.out.println("Nothing found");
//
//                }
//            ;
//            preparedStatement.close();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    private static  final String SELECT_ALL_MY_ARTICLES = "select id, title, brief, create_date from articles where user_id = ?" ;
//    public void showAllMyArticles(int userID){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MY_ARTICLES);
//            preparedStatement.setInt(1 , userID);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()){
//                System.out.println("Article id   : "+rs.getInt("id"));
//                System.out.println("Article title: "+rs.getString("title"));
//                System.out.println("Article brief: "+rs.getString("brief"));
//                System.out.println("Creation Date: "+rs.getString("create_date"));
//            }
//            preparedStatement.close();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    private static  final String SELECT_ARTICLE_CONTENT = "select title, content from articles where id = ?" ;
//    public void showArticleContent(String articleID){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICLE_CONTENT);
//            preparedStatement.setInt(1 , Integer.parseInt(articleID));
//
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()){
//                System.out.println("Article title: "+rs.getString("title"));
//                System.out.println("Content:"+rs.getString("content"));
//            }
//            preparedStatement.close();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    private static  final String UPDATE_BRIEF_CONTENT = "update articles set brief = ?, content = ? where id = ?" ;
//    public void updateBriefContent(String articleID, String newBrief, String newContent){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BRIEF_CONTENT);
//            preparedStatement.setString(1 , newBrief);
//            preparedStatement.setString(2 , newContent);
//            preparedStatement.setInt(3 , Integer.parseInt(articleID));
//
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    private static  final String UPDATE_CONTENT = "update articles set content = ? where id = ?" ;
//    public void updateContent(String articleID, String newContent){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONTENT);
//            preparedStatement.setString(1 , newContent);
//            preparedStatement.setInt(2 , Integer.parseInt(articleID));
//
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    private static  final String UPDATE_BRIEF = "update articles set brief = ? where id = ?" ;
//    public void updateBrief(String articleID, String newBrief){
//        try{
//            Connection connection = DataBaseConnection.getInstance();
//            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BRIEF);
//            preparedStatement.setString(1 , newBrief);
//            preparedStatement.setInt(2 , Integer.parseInt(articleID));
//
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
