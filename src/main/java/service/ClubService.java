package service;

import entity.Club;
import repository.*;

public class ClubService {
    private ClubRepository clubRepository;
    public ClubService() {
        this.clubRepository = new ClubRepository();
    }


    public boolean existsClub(int leagueID, String clubName){
        if(clubRepository.existsClub(leagueID, clubName)){
            System.out.printf("%nClub %s exists.%n", clubName);
            return true;
        }
        else{
            System.out.printf("%nClub %s does not exist.%n", clubName);
            return false;
        }
        
    }
    //    ----------------------------------------------------------------------------------------------

    public void create(Club club){
        if(clubRepository.create(club))
        System.out.printf("Club %s created successfully.%n%n", club.getName().toUpperCase());
        else
        System.out.printf("Club %s was not created.%n%n", club.getName().toUpperCase());
    }

    //    ----------------------------------------------------------------------------------------------

    public void leagueClubs(int leagueID){
        if (clubRepository.leagueClubs(leagueID));
        else {
            System.out.println("No Club available.\n");
        }
    }
    //    ----------------------------------------------------------------------------------------------

    public void remove(int leagueID, String clubName){
        if(clubRepository.remove(leagueID, clubName))
        System.out.printf("%nClub %s removed successfully.%n", clubName);
        else
        System.out.printf("%nClub %s Was not removed.%n", clubName);
    }




}