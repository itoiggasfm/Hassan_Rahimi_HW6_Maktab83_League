import java.util.Scanner;



import entity.*;
import service.*;

public class Menu {

    private  Scanner input = new Scanner(System.in);
    private Club club;
    private League league;
    private Match match;
    private ClubService clubService;
    private LeagueService leagueService;
    private MatchService matchService;

    public Menu() {
        clubService = new ClubService();
        club = new Club();
        leagueService = new LeagueService();
        league = new League();
        matchService = new MatchService();
        match = new Match();
    }




    /**
     * 
     */
    public  void home() {
        String choice = new String();
        boolean flag = true;
homeLoop: while (flag) {
            System.out.println("-----Home Page-----");
            System.out.println("1. Currently availabe leagues");
            System.out.println("2. Add league");
            System.out.println("3. Exit");
            System.out.println("-------------------------");
            choice = input.next();
            switch (choice) {
                case ("1"):
                    if(leagueService.displayAvailableLeagues())
                        leaguesAvailable();
                    break;
                case ("2"):
                    createLeague();
                    break;
                case ("3"):
                    System.out.println("Hope to see you again.");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }//end of method home
//    ----------------------------------------------------------------------------------------------

    public void createLeague(){
        System.out.println("League name: ");
        String leagueName = input.next().toLowerCase();
        while(leagueService.existsLeague(leagueName)){
            System.out.println("League name: ");
            leagueName = input.next().toLowerCase();
        }
        league.setName(leagueName);

        league.setId(calID(leagueName));

        leagueService.create(league);

    }//end of method addLeague
//    ----------------------------------------------------------------------------------------------

    public int calID(String name){
        int id = 0;
        for (char c: name.toCharArray()){
            id += c;
        }
        return id;
    }//end of method calID
//    ----------------------------------------------------------------------------------------------

    public  void leaguesAvailable() {
        String choice = new String();
        boolean flag = true;
        while (flag) {
            System.out.println("-----Leagues available-----");
            System.out.println("1. Currently availabe leagues");
            System.out.println("2. Add league");
            System.out.println("3. Exit");
            System.out.println("-------------------------");
            System.out.println("4. Leagues affairs");
            choice = input.next();
            switch (choice) {
                case ("1"):
                    if(leagueService.displayAvailableLeagues())
                        leagueAffairs();
                    break;
                case ("2"):
                    createLeague();
                    break;
                case ("3"):
                    System.out.println("Hope to see you again.");
                    flag = false;
                    //continue homeLoop;
                    break;
                case ("4"):
                    leagueAffairs();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }//end of method home
//    ----------------------------------------------------------------------------------------------

    /**
     * 
     */
    public  void leagueAffairs() {
        String choice = new String();
        boolean flag = true;
        while (flag) {
            System.out.println();
            System.out.println("-----League affairs-----");
            System.out.println("1. Back");
            System.out.println("2. Add club");
            System.out.println("3. Remove club");
            System.out.println("4. Add match");
            System.out.println("5. Club's matches");
            System.out.println("6. League's matches");
            System.out.println("7. Exit");
            System.out.println("-------------------------");
            choice = input.next();
            switch (choice) {
                case ("1"):
                flag = false;
                    break;
                case ("2"):
                    createClub();
                    break;
                case ("3"):
                    removeClub();
                    break;
                case ("4"):
                    addMatch();
                    break;
                case ("5"):
                    clubMatches();
                    break;
                case ("6"):
                    leagueMatches();
                    break;
                case ("7"):
                    System.out.println("Hope to see you again.");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }//end of method leagueAffairs
//    ----------------------------------------------------------------------------------------------

    public  void createClub(){
        System.out.println("Enter league .......");
        leagueService.displayAvailableLeagues();
        System.out.println("League Name: ");
        String leagueName = input.next().toLowerCase();
        while(!leagueService.existsLeague(leagueName)){
            System.out.println("League Name: ");
            leagueName = input.next().toLowerCase();
        }
        club.setLeagueID(calID(leagueName));

        System.out.println("Club name: ");
        String clubName = input.next().toLowerCase();
        while(clubService.existsClub(club.getLeagueID(), clubName)){
            System.out.println("Club name: ");
            clubName = input.next().toLowerCase();
        }
        club.setName(clubName);

        club.setId(calID(leagueName + clubName));
        System.out.printf("%nCreating club %s .... %n",club.getName());
        clubService.create(club);
    }//end of method addClub
//    ----------------------------------------------------------------------------------------------

public  void removeClub(){
    System.out.println("Enter league .......");
    leagueService.displayAvailableLeagues();
    System.out.println("League Name: ");
    String leagueName = input.next().toLowerCase();
    while(!leagueService.existsLeague(leagueName)){
        System.out.println("League Name: ");
        leagueName = input.next().toLowerCase();
    }
    clubService.leagueClubs(calID(leagueName));

    System.out.println("Club name: ");
    String clubName = input.next().toLowerCase();
    while(!clubService.existsClub(calID(leagueName), clubName)){
        System.out.println("Club name: ");
        clubName = input.next().toLowerCase();
    }

    System.out.printf("%nAre you sure to remove club ? (Y/N)%n", clubName);
    String deletionfirm = input.next();
    while(!(deletionfirm.equals("y") || deletionfirm.equals("Y") || deletionfirm.equals("n") || deletionfirm.equals("N"))){
        System.out.printf("%nAre you sure to remove club %n?(Y/N)", clubName);
        deletionfirm = input.next();
    }

    if(deletionfirm.equals("y") || deletionfirm.equals("Y")){
        System.out.printf("%nRemoving club %s .... %n", clubName);
        clubService.remove(calID(leagueName), clubName);
    }
    
}//end of method removeClub
//    ----------------------------------------------------------------------------------------------

public  void addMatch(){
    System.out.println("Enter league .......");
    leagueService.displayAvailableLeagues();
        System.out.println("League Name: ");
        String leagueName = input.next().toLowerCase();
        while(!leagueService.existsLeague(leagueName)){
            System.out.println("League Name: ");
            leagueName = input.next().toLowerCase();
        }
        match.setLeagueID(calID(leagueName));
        league.setName(leagueName);

        clubService.leagueClubs(calID(leagueName));

        System.out.println("First club name: ");
        String firstClubName = input.next().toLowerCase();
        while(!clubService.existsClub(match.getLeagueID(), firstClubName)){
            System.out.println("First club name: ");
            firstClubName = input.next().toLowerCase();
        }
        match.setFirstClubName(firstClubName);
        match.setFirstClubID(calID(leagueName + firstClubName));

        System.out.println("Second club name: ");
        String secondClubName = input.next().toLowerCase();
        while(!clubService.existsClub(match.getLeagueID(), firstClubName)){
            System.out.println("Second club name: ");
            secondClubName = input.next().toLowerCase();
        }
        match.setSecondClubName(secondClubName);


        int matchHeld = matchService.existsMatch((calID(leagueName + firstClubName + secondClubName)+ 1), (calID(leagueName + firstClubName + secondClubName)+ 2));

        switch (matchHeld){
            case 0:
                match.setId((calID(leagueName + firstClubName + secondClubName)+ 1));
                match.setFirstLeg(true);
                match.setSecondLeg(false);
                addMatchIfNotHeld();
                break;
            case 2:
                match.setId((calID(leagueName + firstClubName + secondClubName)+ 2));
                match.setFirstLeg(false);
                match.setSecondLeg(true);
                addMatchIfNotHeld();
                break;
            case 4:
                break;
        }

}
    //    ----------------------------------------------------------------------------------------------

public void addMatchIfNotHeld(){
    match.setFirstClubView(true);

    System.out.println("First club goals: ");
    String  firstClubGoals = input.next();
    if(match.getLeagueID() == calID("football")){
        while (!matchService.validateFootballGoal(firstClubGoals)){
            System.out.println("First club goals: ");
            firstClubGoals = input.next();
        }
    }
    else if(match.getLeagueID() == calID("volleyball")){
        while (!matchService.validateVolleyballGoal(firstClubGoals)){
            System.out.println("First club goals: ");
            firstClubGoals = input.next();
        }
    }

    match.setFirstClubGoals(firstClubGoals);

    System.out.println("Second club goals: ");
    String secondClubGoals = input.next();
    if(match.getLeagueID() == calID("football")){
        while (!matchService.validateFootballGoal(secondClubGoals)){
            System.out.println("Second club goals: ");
            secondClubGoals = input.next();
        }
    }
    else if(match.getLeagueID() == calID("volleyball")){
        while (!matchService.validateVolleyballGoal(secondClubGoals)){
            System.out.println("Second club goals: ");
            secondClubGoals = input.next();
        }
    }

    match.setSecondClubGoals(secondClubGoals);

    calMatchPoint(match.getLeagueID(), Integer.parseInt(match.getFirstClubGoals()), Integer.parseInt(match.getSecondClubGoals()));

    matchService.create(match);

//the same match is added again but this time the clubs are displaced
    String firstClubName = match.getSecondClubName();
    String secondClubName = match.getFirstClubName();

    match.setFirstClubName(firstClubName);
    match.setFirstClubID(calID(league.getName() + firstClubName));

    match.setSecondClubName(secondClubName);

    match.setFirstClubView(false);

    firstClubGoals = match.getSecondClubGoals();
    secondClubGoals = match.getFirstClubGoals();
    match.setFirstClubGoals(firstClubGoals);
    match.setSecondClubGoals(secondClubGoals);


    calMatchPoint(match.getLeagueID(), Integer.parseInt(match.getFirstClubGoals()), Integer.parseInt(match.getSecondClubGoals()));

    matchService.create(match);
}
//    ----------------------------------------------------------------------------------------------

public void calMatchPoint(int leagueID, int firstGoals, int secondGoals){
    if(leagueID == calID("football")){
        if(firstGoals > secondGoals){
            match.setFirstClubWwin(true);
            match.setFirstClubDraw(false);
            match.setFirstClubLose(false);
            match.setFirstClubPoint(3);
            match.setSecondClubWin(false);
            match.setSecondClubDraw(false);
            match.setSecondClubLose(true);
            match.setSecondClubPoint(0);
        }
        else
        if(firstGoals == secondGoals){
            match.setFirstClubWwin(false);
            match.setFirstClubDraw(true);
            match.setFirstClubLose(false);
            match.setFirstClubPoint(1);
            match.setSecondClubWin(false);
            match.setSecondClubDraw(true);
            match.setSecondClubLose(false);
            match.setSecondClubPoint(1);
        }
        else{
            match.setFirstClubWwin(false);
            match.setFirstClubDraw(false);
            match.setFirstClubLose(true);
            match.setFirstClubPoint(0);
            match.setSecondClubWin(true);
            match.setSecondClubDraw(false);
            match.setSecondClubLose(false);
            match.setSecondClubPoint(3);
        } 
        
    }

    if(leagueID == calID("volleyball")){
		if(firstGoals > secondGoals){
			if((firstGoals == 3 && secondGoals == 0) || (firstGoals == 3 && secondGoals == 1)){
            match.setFirstClubWwin(true);
            match.setFirstClubDraw(false);
            match.setFirstClubLose(false);
            match.setFirstClubPoint(3);
            match.setSecondClubWin(false);
            match.setSecondClubDraw(false);
            match.setSecondClubLose(true);
            match.setSecondClubPoint(0);
			}
			else
			if(firstGoals == 3 && secondGoals == 2){
				match.setFirstClubWwin(true);
				match.setFirstClubDraw(false);
				match.setFirstClubLose(false);
				match.setFirstClubPoint(2);
				match.setSecondClubWin(false);
				match.setSecondClubDraw(false);
				match.setSecondClubLose(true);
				match.setSecondClubPoint(1);
			}
		}
		else{
			if((firstGoals == 0 && secondGoals == 3) || (firstGoals == 1 && secondGoals == 3)){
            match.setFirstClubWwin(false);
            match.setFirstClubDraw(false);
            match.setFirstClubLose(true);
            match.setFirstClubPoint(0);
            match.setSecondClubWin(true);
            match.setSecondClubDraw(false);
            match.setSecondClubLose(false);
            match.setSecondClubPoint(3);
			}
			else
			if(firstGoals == 2 && secondGoals == 3){
				match.setFirstClubWwin(false);
				match.setFirstClubDraw(false);
				match.setFirstClubLose(true);
				match.setFirstClubPoint(1);
				match.setSecondClubWin(true);
				match.setSecondClubDraw(false);
				match.setSecondClubLose(false);
				match.setSecondClubPoint(2);
			}
		}
        
        
    }

    

}
//    ----------------------------------------------------------------------------------------------
public void clubMatches(){
    System.out.println("Enter league .......");
    leagueService.displayAvailableLeagues();
    System.out.println("League Name: ");
    String leagueName = input.next().toLowerCase();
    while(!leagueService.existsLeague(leagueName)){
        System.out.println("League Name: ");
        leagueName = input.next().toLowerCase();
    }

    clubService.leagueClubs(calID(leagueName));

    System.out.println("Club name: ");
    String clubName = input.next().toLowerCase();
    while(!clubService.existsClub(calID(leagueName), clubName)){
        System.out.println("Club name: ");
        clubName = input.next().toLowerCase();
    }
    matchService.clubMatches(calID(leagueName), calID(leagueName + clubName));
    matchService.clubMatchesTotalPoint(calID(leagueName), calID(leagueName + clubName));
}
//    ----------------------------------------------------------------------------------------------


    public void leagueMatches(){
        System.out.println("Enter league .......");
        leagueService.displayAvailableLeagues();
        System.out.println("League Name: ");
        String leagueName = input.next().toLowerCase();
        while(!leagueService.existsLeague(leagueName)){
            System.out.println("League Name: ");
            leagueName = input.next().toLowerCase();
        }


        matchService.leagueMatches(calID(leagueName));

    }

}//end of class menu


