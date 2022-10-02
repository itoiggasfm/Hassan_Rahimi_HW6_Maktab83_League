package repository;

import java.sql.*;
import connection.*;
import entity.*;

public class MatchRepository {



    private static final String SELECT_DONE_MATCHES_QUERY = "select id, first_leg, second_leg from tbl_match where id = ? or id = ?";
    public int existsMatch(int ID1, int ID2){
        int count = 0;
        try {
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DONE_MATCHES_QUERY);
            preparedStatement.setInt(1 , ID1);
            preparedStatement.setInt(2 , ID2);

            ResultSet rs = preparedStatement.executeQuery();

                while(rs.next()){
                    if((rs.getBoolean("first_leg") && !rs.getBoolean("second_leg")) || (!rs.getBoolean("first_leg") && rs.getBoolean("second_leg")))
                        ++count;
                }
                preparedStatement.close();
                return  count;


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }//end of existsMatch
    //    ----------------------------------------------------------------------------------------------
    private static final String ADD_MATCH_QUERY = "INSERT INTO tbl_match (league_id, id, first_club_view, first_club_id, first_club_name, second_club_name, first_club_goals, second_club_goals, first_club_win, first_club_draw, first_club_lose, first_club_point, second_club_win, second_club_draw, second_club_lose, second_club_point, first_leg, second_leg) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public boolean create(Match match){
        try{
            System.out.println(match.getId());
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MATCH_QUERY);
            preparedStatement.setInt(1 , match.getLeagueID());
            preparedStatement.setInt(2 , match.getId());
            preparedStatement.setBoolean(3 , match.getFirstClubView());
            preparedStatement.setInt(4 , match.getFirstClubID());
            preparedStatement.setString(5 , match.getFirstClubName());
            preparedStatement.setString(6 , match.getSecondClubName());
            preparedStatement.setInt(7 , Integer.parseInt(match.getFirstClubGoals()));
            preparedStatement.setInt(8 , Integer.parseInt(match.getSecondClubGoals()));
            preparedStatement.setBoolean(9 , match.getFirstClubWwin());
            preparedStatement.setBoolean(10 , match.getFirstClubDraw());
            preparedStatement.setBoolean(11 , match.getFirstClubLose());
            preparedStatement.setInt(12 , match.getFirstClubPoint());
            preparedStatement.setBoolean(13 , match.getSecondClubWin());
            preparedStatement.setBoolean(14 , match.getSecondClubDraw());
            preparedStatement.setBoolean(15 , match.getSecondClubLose());
            preparedStatement.setInt(16 , match.getSecondClubPoint());
            preparedStatement.setBoolean(17 , match.getFirstLeg());
            preparedStatement.setBoolean(18 , match.getSecondLeg());


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

    private static final String SELECT_CLUB_MATCHES_QUERY = "select first_club_name, second_club_name, first_club_goals, second_club_goals, first_club_win, first_club_draw, first_club_lose, first_club_point, first_leg, second_leg from tbl_match where league_id = ? and first_club_id = ?";
    public boolean clubMatches(int leagueID, int clubID){
        try {
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLUB_MATCHES_QUERY);
            preparedStatement.setInt(1 , leagueID);
            preparedStatement.setInt(2 , clubID);

            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst() ) {
                return false;
            }
            else{

                System.out.printf("%nFirst club     Second club     First club goals     Second club goals     Result     Point     1st/2nd leg");
                System.out.printf("%n----------------------------------------------------------------------------------------------------------");
                while(rs.next()){
                    System.out.printf("%n%-15s%-16s%-21d%-22d%-11s%-10d%-11s", rs.getString("first_club_name").substring(0, 1).toUpperCase()+rs.getString("first_club_name").substring(1), rs.getString("second_club_name").substring(0, 1).toUpperCase()+rs.getString("second_club_name").substring(1), rs.getInt("first_club_goals"), rs.getInt("second_club_goals"), rs.getBoolean("first_club_win")? "Win" :(rs.getBoolean("first_club_draw")? "Draw" : "Lose"), rs.getInt("first_club_point"), rs.getBoolean("first_leg")?"First leg":"Second Leg");
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
    }//end of clubMatches
    private static final String SELECT_CLUB_MATCHES_TOTAL_POINT_QUERY = "select SUM(first_club_point) as total_point, sum(first_club_goals) as goals_scored, sum(second_club_goals) as goals_against from tbl_match where league_id = ? and first_club_id = ?";
    public void clubMatchesTotalPoint(int leagueID, int clubID){
        try {
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLUB_MATCHES_TOTAL_POINT_QUERY);
            preparedStatement.setInt(1 , leagueID);
            preparedStatement.setInt(2 , clubID);

            ResultSet rs = preparedStatement.executeQuery();

                while(rs.next()){
                    System.out.printf("Total point  : %d", rs.getInt("total_point"));
                    System.out.printf("%nGoals scored : %d", rs.getInt("goals_scored"));
                    System.out.printf("%nGoals against: %d", rs.getInt("goals_against"));
                }
                System.out.printf("%n%n");
                preparedStatement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }//end of clubMatchesTotalPoint
    //    ----------------------------------------------------------------------------------------------
    private static final String SELECT_LEAGUE_MATCHES_QUERY = "select first_club_name, count(first_club_name) as match_number, count(case when first_leg then 1 end) as first_leg_number, count(case when second_leg then 1 end) as second_leg_number, count(case when first_club_win then 1 end) as win_number,  count(case when first_club_draw then 1 end) as draw_number, count(case when first_club_lose then 1 end) as lose_number, sum(first_club_goals) as goals_scored_number, sum(second_club_goals) as goals_against_number, sum(first_club_point) as total_point from tbl_match where league_id = ? group by first_club_name order by sum(first_club_point) desc";

    public boolean leagueMatches(int leagueID){
        try {
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LEAGUE_MATCHES_QUERY);
            preparedStatement.setInt(1 , leagueID);

            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst() ) {
                return false;
            }
            else{

                System.out.printf("%nClub Name     Match number     First leg number     Second leg number     Win number     Draw number     Lose number     Goals scored number     Goals against number     Total point");
                System.out.printf("%n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while(rs.next()){
                    System.out.printf("%n%-15s%-17d%-21d%-22d%-15d%-16d%-16d%-24d%-25d%-11d", rs.getString("first_club_name").substring(0, 1).toUpperCase()+rs.getString("first_club_name").substring(1), rs.getInt("match_number"), rs.getInt("first_leg_number"), rs.getInt("second_leg_number"), rs.getInt("win_number"), rs.getInt("draw_number"), rs.getInt("lose_number"), rs.getInt("goals_scored_number"), rs.getInt("goals_against_number"), rs.getInt("total_point") );
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
    }//end of clubMatches

}//end of class MatchRepository
