package service;

import entity.*;
import repository.*;

public class LeagueService {

    private LeagueRepository leagueRepository;

    public LeagueService() {
        this.leagueRepository = new LeagueRepository();
    }




    public boolean displayAvailableLeagues() {
        if (leagueRepository.displayAvailableLeagues())
            return true;
        else {
            System.out.println("No League available.\n");
            return false;
        }
    }

//    ----------------------------------------------------------------------------------------------

public boolean existsLeague(String leagueName){
    if(leagueRepository.existsLeague(leagueName)){
        System.out.printf("%nLeague %s exists.%n", leagueName);
        return true;
    }
    else{
        System.out.printf("%nLeague %s does not exist.%n", leagueName);
        return false;
    }
    
}
// ----------------------------------------------------------------------------------------------

    public void create(League league){
        leagueRepository.create(league);
    }
//    ----------------------------------------------------------------------------------------------

//     public boolean existsLeagueName(String leagueName){
//             System.out.println("League not found.");
//             return false;
//         else {
// //            System.out.println(Integer.parseInt(leagueID));
// //            league.setId(Integer.parseInt(leagueID));
// //            System.out.println(Integer.parseInt(leagueID));
// //            club.setLeagueID(Integer.parseInt(leagueID));
// //            System.out.println(club.getLeagueID());
//             return true;
//         }

//     }
//
//    public void showAllArticles(){
//        leagueRepository.showAllArticles();
//    }
//
//   // private static  final String SELECT_ALL_MY_ARTICLES = "select articles.id, articles.title, articles.brief, users.username from articles left join users on articles.user_id = users.id where is_published = 'true'" ;
//
//
//    public void findMyArticle(String searchCase, int userID){
//        leagueRepository.findMyArticle(searchCase, userID);
//    }
//    public void showAllMyArticles(int userID){
//        leagueRepository.showAllMyArticles(userID);
//    }
//
//
//    public void showArticleContent(String articleID){
//        leagueRepository.showArticleContent(articleID);
//    }
//
//
//    public void updateBriefContent(String articleID, String newBrief, String newContent){
//        leagueRepository.updateBriefContent(articleID, newBrief, newContent);
//    }
//
//
//    public void updateContent(String articleID, String newContent){
//        leagueRepository.updateContent(articleID, newContent);
//    }
//
//
//    public void updateBrief(String articleID, String newBrief){
//        leagueRepository.updateBrief(articleID, newBrief);
//    }
//
//    public void insertArticle(League article){
//        leagueRepository.insertArticle(article);
//    }
//
//    // public void updateCustomer(String name , int id){
//    //     UserService.updateCustomer(name , id);
//    // }
}
