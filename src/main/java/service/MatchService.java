package service;

import entity.Match;
import repository.MatchRepository;

import java.util.regex.Pattern;

public class MatchService {

    private MatchRepository matchRepository;
    public MatchService() {
        this.matchRepository = new MatchRepository();
    }

    //    ----------------------------------------------------------------------------------------------


    public int existsMatch(int ID1, int ID2) {
        if (matchRepository.existsMatch(ID1, ID2) == 0) {
            System.out.println("Match has not been held yet.");
            return 0;
        } else if (matchRepository.existsMatch(ID1, ID2) == 2) {
            System.out.println("Only first leg has been held. You can continue to add second leg.");
            return 2;
        } else {
            System.out.println("First leg and second leg have been held and you are not allowed to add any matches.");
            return 4;
        }
    }
    //    ----------------------------------------------------------------------------------------------
    public void create(Match match){
        if(matchRepository.create(match))
        System.out.printf("Match %s vs %s added successfully.%n%n", match.getFirstClubName().toUpperCase(), match.getSecondClubName().toUpperCase());
        else
        System.out.printf("Match %s vs %s was not added.%n%n", match.getFirstClubName().toUpperCase(), match.getSecondClubName().toUpperCase());
    }//end of method create
    //    ----------------------------------------------------------------------------------------------

    public boolean validateFootballGoal(String goal){
        if(Pattern.compile("\\d{1,2}").matcher(goal).matches())
           return true;
        else{
            System.out.println("Invalid number for goal");
            return false;
        }

    }
    //    ----------------------------------------------------------------------------------------------

    public boolean validateVolleyballGoal(String goal){
        if(Pattern.compile("[0-3]").matcher(goal).matches())
            return true;
        else{
            System.out.println("Invalid number for goal");
            return false;
        }

    }
    //    ----------------------------------------------------------------------------------------------

    public void clubMatches(int leagueID, int clubID){
        if(matchRepository.clubMatches(leagueID, clubID)){

        }
        else{
            System.out.println("\nNo matches\n");
        }
    }//end of clubMatches
    //    ----------------------------------------------------------------------------------------------

    public void clubMatchesTotalPoint(int leagueID, int clubID){
        matchRepository.clubMatchesTotalPoint(leagueID, clubID);
    }//end of clubMatches
    //    ----------------------------------------------------------------------------------------------

    public void leagueMatches(int leagueID){
        if(matchRepository.leagueMatches(leagueID)){

        }
        else{
            System.out.println("%nNo matches.%n");
        }
    }//end of clubMatches


}//end of class MatchService



