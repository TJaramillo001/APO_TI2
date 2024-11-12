package ui;

import java.lang.*;
import java.util.*;
import model.Controller;
import model.GoalDetail;
import model.Match;
import model.PlayerPosition;
import model.RefereeType;

public class Executable {
    private Scanner in;
	private Controller cont;
	private static boolean flag;
    /**
     * Description: Initializes the Executable class. Initializes scanner and controller class
     */
	private Executable() {
		in = new Scanner(System.in);
		cont = new Controller();
	}
    /**
     * Description: Considered as the menu. The run method is what the user primarily interacts with
     * @param boolean flag: Initialized as false. Used to cycle the loop for as long as the program runs. When switched to true, a Systenm.exit method is used
     */
    public void run_one(boolean flag){
        flag=false;
        System.out.println("Welcome to the Soccer Tournament.");
        while(!flag){

            System.out.println("\n\nWelcome to the menu:\n");
			System.out.println( "Please select one of the following:\n" + 
                                "1. Register Team \n" + 
                                "2. Register Player \n" + 
                                "3. Register Referee \n" + 
                                "4. Show Team Information \n" + 
                                "5. Show Player Information \n" + 
                                "6. Show Referee Information \n" + 
                                "7. Preload Teams \n" +
                                "8. Preload Referees \n" +
                                "9. Verify next stage \n" +
                                "10. Exit \n");
            int select=in.nextInt();
            in.nextLine(); //Clear buffer
            
            switch(select){
                case 1:
                    registerTeam();
                    break;
                case 2:
                    registerPlayer();
                    break;
                case 3:
                    registerReferee();
                    break;
                case 4:
                    showTeamInfo();
                    break;
                case 5:
                    showPlayerInfo();
                    break;
                case 6: 
                    showRefInfo();
                    break;
                case 7:
                    cont.preloadTeams();
                    break;
                case 8:
                    cont.preloadReferees();
                    break;
                case 9:
                    if(cont.verifyAdvance()){
                        flag=true;
                    }
                    break;
                case 10:
                    System.out.println("Thank you for using the application! Goodbye.");
                    flag=true;
                    in.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Sorry, please select a valid option");
                    continue;
            }
        }
        return;

    }
    /**
     * Description: Executable side code where the user is asked for all information needed to register a team to the tournament.
     */
    public void registerTeam(){
        String teamName, country, coach;
        System.out.println("Please enter the team name:");
        teamName=in.nextLine();

        System.out.println("Please enter "+teamName+"'s country");
        country=in.nextLine();

        System.out.println("Please enter "+teamName+"'s head coach's name");
        coach=in.nextLine();

        cont.registerTeam(teamName, country, coach);
    }
    /**
     * Description: Method used to create a new player. Asks the user for all pertaining information needed to register a player and associate it to an already registered team.
     */
    public void registerPlayer(){
        System.out.println("What team would you like to add your player to?");
        String teamName=in.nextLine();

        System.out.println("What is your player's name?");
        String playerName=in.nextLine();

        System.out.println("What is "+playerName+"'s jersey number");
        int playerNum=in.nextInt();
        in.nextLine();
        
        PlayerPosition playerPosition=null;
        while(playerPosition==null){
            System.out.println("What position does "+playerName+" play? (1. GOALKEEPER, 2. DEFENDER, 3. MIDFIELDER, 4. FORWARD)");
            int selection=in.nextInt();
            in.nextLine();
            playerPosition=cont.selectPosition(selection);
        }

        cont.registerPlayer(playerName, playerNum, playerPosition, playerName, teamName);
    }
    /**
     * Description: Method used to register a referee. The user is asked for all information needed to register a referee.
     */
    public void registerReferee(){
        System.out.println("What is the Referee's name");
        String refName= in.nextLine();

        System.out.println("What is "+refName+"'s referee ID number?");
        String refID=in.nextLine();
        
        RefereeType refType=null;
        while(refType==null){
            System.out.println("Is the referee CENTRAL or ASSISTANT?");
            String selection=in.nextLine().toUpperCase();
            refType=cont.selectPosition(selection);
        }
        System.out.println("What is "+refName+"'s country of origin?");
        String country=in.nextLine();
        
        cont.registerReferee(refName, refID, refType, country);
    }
    /**
     * Description: Shows all teams and then asks the user for a selection of the pickings to return information regarding that team.
     */
    public void showTeamInfo(){
        System.out.println("\nWhat team would you like to consult?");
        System.out.println("Here are the following options:\n");
        cont.showAllTeams();
        String selection=in.nextLine();
        boolean flag=cont.teamInfo(selection);
        if(!flag){
            System.out.println("Sorry, we weren't able to find that team");
        }
    }
    /**
     * Description: Shows player information after receiving a player name 
     */
    public void showPlayerInfo(){
        System.out.println("\nWhich player would you like to consult?");
        String selection=in.nextLine();
        boolean flag=cont.playerInfo(selection);
        
        if(!flag){
            System.out.println("Sorry, we weren't able to find that Player");
        }
    }
    /**
     * Description : Displays a list of referees and retrieves information about a selected referee.
     */
    public void showRefInfo(){
        System.out.println("\nPlease enter the name of the referee you would like to consult");
        System.out.println("Here are the following options:\n");
        cont.showAllRefs();
        
        String refName=in.nextLine();
        boolean flag=cont.refereeInfo(refName);
        if(!flag){
            System.out.println("Sorry, we weren't able to find that referee");
        }
    }
    public void run_two(){
        boolean flag=false;
        int select;
        while (!flag) {
            System.out.println("\n\nWelcome to the menu:\n");
			System.out.println( "Please select one of the following:\n" + 
                                "1. Generate fixture \n" + 
                                "2. Consult Group generation \n" + 
                                "3. Consult Matches \n" + 
                                "4. Assign referees to games \n" + 
                                "5. Register game scores \n" + 
                                "6. Show final scores \n" +
                                "7. Register cards \n" +
                                "8. Print Standings \n" +
                                "10. Exit \n\n" +

                                "15. Show Team Information \n" + 
                                "20. Show Player Information \n" + 
                                "25. Show Referee Information \n");
            select=in.nextInt();
            in.nextLine();
            switch (select) {
                case 1:
                    cont.generateFixture();
                    break;
                case 2:
                    cont.consultFixture();
                    break;
                case 3:
                    cont.consultMatches();
                    break;
                case 4:
                    cont.assignReferee();
                    break;
                case 5:
                    registerScore();
                    break;
                case 6:
                    cont.showGameScores();
                    break;
                case 7:
                    registerCards();
                    break;
                case 8:
                    cont.printStandings();
                    break;
                case 10:
                    System.out.println("Thank you for using the application! Goodbye.");
                    flag=true;
                    in.close();
                    System.exit(0);
                    break;
                case 15:
                    showTeamInfo();
                    break;
                case 20:
                    showPlayerInfo();
                    break;
                case 25:
                    showRefInfo();
                    break;
                default:
                    System.out.println("Sorry, please select a valid option");
                    continue;
            }
        }
    }

    public void registerScore() {
        System.out.println("To enter the scores manually, press 1. To simulate scores, press any number.");
        int opt=in.nextInt();
        in.nextLine();

        if(opt==1){
            System.out.println("Which group do you want to enter scores for? (A or B)");
            String group = in.nextLine();

            Match[] matches = group.equalsIgnoreCase("A") ? cont.getGroupAMatches() : cont.getGroupBMatches();

            for (Match match : matches) {
                System.out.println("Enter score for " + match.getHomeTeam().getTeamName() + " vs " + match.getAwayTeam().getTeamName());
                System.out.print("Home score: ");
                int homeScore = in.nextInt();
                in.nextLine();

                System.out.print("Away score: ");
                int awayScore = in.nextInt();
                in.nextLine(); // Clear the newline
                cont.sendGoals(match, homeScore, awayScore);
              
                for (int i = 0; i < homeScore; i++) {
                    System.out.println("Enter details for goal #" + (i + 1) + " for " + match.getHomeTeam().getTeamName());
                    System.out.print("Scorer: ");
                    String scorer = in.nextLine();
                    System.out.print("Assister (or type 'none'): ");
                    String assister = in.nextLine();
                    System.out.print("Minute: ");
                    int minute = in.nextInt();
                    in.nextLine(); // Clear the newline
                    
                    cont.addGoalToMatch(match, scorer, assister, minute);
                }

                for (int i = 0; i < awayScore; i++) {
                    System.out.println("Enter details for goal #" + (i + 1) + " for " + match.getAwayTeam().getTeamName());
                    System.out.print("Scorer: ");
                    String scorer = in.nextLine();
                    System.out.print("Assister (or type 'none'): ");
                    String assister = in.nextLine();
                    System.out.print("Minute: ");
                    int minute = in.nextInt();
                    in.nextLine(); // Clear the newline

                    cont.addGoalToMatch(match, scorer, assister, minute);
                }

                // Display recorded goals
                match.displayGoals();
            }
        }else{
            cont.simulateGroupMatches();
        }
    }
    public void registerCards(){
        System.out.println("To enter the cards manually, press 1");
        
        System.out.println("Which group do you want to enter cards for? (A or B)");
        String group = in.nextLine();

        Match[] matches = group.equalsIgnoreCase("A") ? cont.getGroupAMatches() : cont.getGroupBMatches();

        for (Match match : matches) {
            System.out.println("Enter the cards awarded in " + match.getHomeTeam().getTeamName() + " vs " + match.getAwayTeam().getTeamName());
            System.out.print("Home cards: ");
            int homeCards = in.nextInt();
            in.nextLine();

            System.out.print("Away cards: ");
            int awayCards = in.nextInt();
            in.nextLine();

            for(int i=0; i<homeCards;i++){
                System.out.println("Enter the details for card #"+(i+1)+" for "+match.getHomeTeam().getTeamName());
                System.out.println("Player who commited the foul: ");
                String player = in.nextLine();

                System.out.println("Card awarded (YELLOW or RED)");
                String cardType = in.nextLine().toUpperCase();

                System.out.print("Minute: ");
                int minute = in.nextInt();
                in.nextLine(); // Clear the newline

                cont.addCardToMatch(match, player, cardType, minute, true); //True is for home team
            }
            for(int i=0; i<awayCards;i++){
                System.out.println("Enter the details for card #"+(i+1)+" for "+match.getAwayTeam().getTeamName());
                System.out.println("Player who commited the foul: ");
                String player = in.nextLine();

                System.out.println("Card awarded (YELLOW or RED)");
                String cardType = in.nextLine().toUpperCase();

                System.out.print("Minute: ");
                int minute = in.nextInt();
                in.nextLine(); // Clear the newline

                cont.addCardToMatch(match, player, cardType, minute, false); //False is for away team
            }


        }
    }
    


    public static void main(String[] args) {
		Executable main = new Executable();
		main.run_one(flag);
        main.run_two();
	}

}
