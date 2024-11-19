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
    private boolean semis;
    /**
     * Description: Initializes the Executable class. Initializes scanner and controller class
     */
	private Executable() {
		in = new Scanner(System.in);
		cont = new Controller();
        semis=false;
	}
    /**
     * Description: Considered as the menu. The run method is what the user primarily interacts with
     * @param boolean flag: Initialized as false. Used to cycle the loop for as long as the program runs. When switched to true, a Systenm.exit method is used
     */
    public void run_register(boolean flag){
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
    public void run_group(){
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
                                "9. Advance to the semifinals\n"+
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
                case 9:
                    if(cont.verifyTwo()){
                        flag=true;
                        semis=true;
                    }
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
                cont.showGameScores();
            }

        }else{
            cont.simulateGroupMatches();
            cont.showGameScores();
        }        
    }

    public void registerCards(){
        
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
    public void run_semifinals(){
        boolean flag=false;
        int select;
        boolean created = false;

        while (!flag) {
            System.out.println("\n\nWelcome to the Semi Finals:\n");
			System.out.println( "Please select one of the following:\n" + 
                                "1. Consult Matches\n" + 
                                "2. Register Scores for Match 1\n" + 
                                "3. Register Scores for Match 2 \n" + 
                                "4. Register Cards \n"+
                                "5. Advance to the Finals \n"+
                                "10. Exit \n\n" +

                                "15. Show Team Information \n" + 
                                "20. Show Player Information \n" + 
                                "25. Show Referee Information \n");
            select=in.nextInt();
            in.nextLine();
            
            switch (select) {
                case 1:
                    if(!created){
                        cont.createAndShowSemis();
                        created = true;
                    } else {
                        showSemis();
                    }
                break;
                case 2:
                    registerSemifinalScore();
                break;
                case 3:
                    registerSemifinalScore();
                break;
                case 4:
                    registerSemiCards();
                break;
                case 5:
                    if(cont.verifyFinals()){
                        flag = true;
                    } else {
                        System.out.println("Please enter the scores before attempting to advance to the Finals");
                    }
                    break;
                case 10:
                    System.out.println("Thank you for using the application!");
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
    public void showSemis(){
        System.out.println("\n\n\nMatch 1: "+cont.getSemiOneHomeTeam()+" vs "+cont.getSemiOneAwayTeam() + " on "+cont.getSemiDate());
        System.out.println("Match 2: "+cont.getSemiTwoHomeTeam()+" vs "+cont.getSemiTwoAwayTeam() + " on "+cont.getSemiDate());

    }

    private boolean which = true;
    private boolean scored = false;
    public void registerSemifinalScore(){
        if(!scored){    
            if(which){
                System.out.println("Please enter the score for "+cont.getSemiOneHomeTeam()+ " vs "+cont.getSemiOneAwayTeam());
                System.out.println("Home Score:");
                int homeScore = in.nextInt();
                in.nextLine();

                System.out.println("Away Score:");
                int awayScore = in.nextInt();
                in.nextLine();
                while(homeScore==awayScore){
                    System.out.println("Teams cannot tie in the semifinals. Please enter the scores again: ");
                    System.out.println("Home Score:");
                    homeScore = in.nextInt();
                    in.nextLine();

                    System.out.println("Away Score:");
                    awayScore = in.nextInt();
                    in.nextLine();
                }

                for (int i = 0; i < homeScore; i++) {
                    System.out.println("Enter details for goal #" + (i + 1) + " for " + cont.getSemiOneHomeTeam());
                    System.out.print("Scorer: ");
                    String scorer = in.nextLine();
                    System.out.print("Assister (or type 'none'): ");
                    String assister = in.nextLine();
                    System.out.print("Minute: ");
                    int minute = in.nextInt();
                    in.nextLine(); // Clear the newline
                    
                    cont.goalMiddleman(true, scorer, assister, minute);
                }

                for (int i = 0; i < awayScore; i++) {
                    System.out.println("Enter details for goal #" + (i + 1) + " for " + cont.getSemiOneAwayTeam());
                    System.out.print("Scorer: ");
                    String scorer = in.nextLine();
                    System.out.print("Assister (or type 'none'): ");
                    String assister = in.nextLine();
                    System.out.print("Minute: ");
                    int minute = in.nextInt();
                    in.nextLine(); // Clear the newline

                    cont.goalMiddleman(true, scorer, assister, minute);
                }
                which=false;
                cont.calculateWinner(1 ,homeScore, awayScore);
            } else {
                System.out.println("Please enter the score for "+cont.getSemiTwoHomeTeam()+ " vs "+cont.getSemiTwoAwayTeam());
                System.out.println("Home Score:");
                int homeScore = in.nextInt();
                in.nextLine();

                System.out.println("Away Score:");
                int awayScore = in.nextInt();
                in.nextLine();

                while(homeScore==awayScore){
                    System.out.println("Teams cannot tie in the semifinals. Please enter the scores again: ");
                    System.out.println("Home Score:");
                    homeScore = in.nextInt();
                    in.nextLine();

                    System.out.println("Away Score:");
                    awayScore = in.nextInt();
                    in.nextLine();
                }
                


                for (int i = 0; i < homeScore; i++) {
                    System.out.println("Enter details for goal #" + (i + 1) + " for " + cont.getSemiTwoHomeTeam());
                    System.out.print("Scorer: ");
                    String scorer = in.nextLine();
                    System.out.print("Assister (or type 'none'): ");
                    String assister = in.nextLine();
                    System.out.print("Minute: ");
                    int minute = in.nextInt();
                    in.nextLine(); // Clear the newline
                    
                    cont.goalMiddleman(false, scorer, assister, minute);
                }

                for (int i = 0; i < awayScore; i++) {
                    System.out.println("Enter details for goal #" + (i + 1) + " for " + cont.getSemiTwoAwayTeam());
                    System.out.print("Scorer: ");
                    String scorer = in.nextLine();
                    System.out.print("Assister (or type 'none'): ");
                    String assister = in.nextLine();
                    System.out.print("Minute: ");
                    int minute = in.nextInt();
                    in.nextLine(); // Clear the newline

                    cont.goalMiddleman(false, scorer, assister, minute);
                }
                cont.calculateWinner(2 ,homeScore, awayScore);
            }

        } else {
            System.out.println("Sorry, you have already input both scores.");
        }
    }
    public void registerSemiCards(){
        System.out.println("Enter the cards awarded in " + cont.getSemiOneHomeTeam() + " vs " + cont.getSemiOneAwayTeam());
        System.out.print("Home cards: ");
        int homeCards = in.nextInt();
        in.nextLine();

        System.out.print("Away cards: ");
        int awayCards = in.nextInt();
            in.nextLine();

            for(int i=0; i<homeCards;i++){
                System.out.println("Enter the details for card #"+(i+1)+" for "+cont.getSemiOneHomeTeam());
                System.out.println("Player who commited the foul: ");
                String player = in.nextLine();

                System.out.println("Card awarded (YELLOW or RED)");
                String cardType = in.nextLine().toUpperCase();

                System.out.print("Minute: ");
                int minute = in.nextInt();
                in.nextLine(); // Clear the newline

                cont.cardMiddleman(true, player, cardType, minute, true); //True is for home team
            }
            for(int i=0; i<awayCards;i++){
                System.out.println("Enter the details for card #"+(i+1)+" for "+cont.getSemiOneAwayTeam());
                System.out.println("Player who commited the foul: ");
                String player = in.nextLine();

                System.out.println("Card awarded (YELLOW or RED)");
                String cardType = in.nextLine().toUpperCase();

                System.out.print("Minute: ");
                int minute = in.nextInt();
                in.nextLine(); // Clear the newline

                cont.cardMiddleman(false, player, cardType, minute, true); //False is for away team
            }
            
        //Second Game
        System.out.println("Enter the cards awarded in " + cont.getSemiTwoHomeTeam() + " vs " + cont.getSemiTwoAwayTeam());
        System.out.print("Home cards: ");
        homeCards = in.nextInt();
        in.nextLine();

        System.out.print("Away cards: ");
        awayCards = in.nextInt();
        in.nextLine();

        for(int i=0; i<homeCards;i++){
            System.out.println("Enter the details for card #"+(i+1)+" for "+cont.getSemiTwoHomeTeam());
            System.out.println("Player who commited the foul: ");
            String player = in.nextLine();

            System.out.println("Card awarded (YELLOW or RED)");
            String cardType = in.nextLine().toUpperCase();

            System.out.print("Minute: ");
            int minute = in.nextInt();
            in.nextLine(); // Clear the newline

            cont.cardMiddleman(true, player, cardType, minute, false); //True is for home team
        }
        for(int i=0; i<awayCards;i++){
        
            System.out.println("Enter the details for card #"+(i+1)+" for "+cont.getSemiTwoAwayTeam());
            System.out.println("Player who commited the foul: ");
            String player = in.nextLine();

            System.out.println("Card awarded (YELLOW or RED)");
            String cardType = in.nextLine().toUpperCase();
  
            System.out.print("Minute: ");        
            int minute = in.nextInt();
            in.nextLine(); // Clear the newline

            cont.cardMiddleman(false, player, cardType, minute, false); //False is for away team    
        }
    }

    public void run_finals(){
        boolean flag=false;
        int select;
        cont.createFinals();
        
        while (!flag) {
            System.out.println("\n\nWelcome to the Finals:\n");
			System.out.println( "Please select one of the following:\n" + 
                                "1. Consult Match\n" + 
                                "2. Consult Referees\n" + 
                                "3. Register Scores\n" + 
                                "4. Register Cards \n"+
                                "5. Advance to the prize ceremony \n"+
                                "10. Exit \n\n" +

                                "15. Show Team Information \n" + 
                                "20. Show Player Information \n" + 
                                "25. Show Referee Information \n");
            select=in.nextInt();
            in.nextLine();
            
            switch (select) {
                case 1:
                    cont.showFinals();
                break;
                case 2:
                    cont.showFinalsReferees();
                break;
                case 3:
                    registerFinalScore();
                break;
                case 4:
                    registerFinalCards();
                break;
                case 5:
                    if(cont.verifyWinner()){
                        flag = true;
                    } else {
                        System.out.println("Please enter the scores before attempting to advance to the Prize ceremony");
                    }
                break;
                case 10:
                    System.out.println("Thank you for using the application!");
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

    private boolean finals=false;
    public void registerFinalScore(){
        if(!finals){
            System.out.println("Please enter the score for "+cont.getFinalsHomeTeam()+ " vs "+cont.getFinalsAwayTeam());
            System.out.println("Home Score:");
            int homeScore = in.nextInt();
            in.nextLine();

            System.out.println("Away Score:");
            int awayScore = in.nextInt();
            in.nextLine();
            while(homeScore==awayScore){
                System.out.println("Teams cannot tie in the finals. Please enter the scores again: ");
                System.out.println("Home Score:");
                homeScore = in.nextInt();
                in.nextLine();

                System.out.println("Away Score:");
                awayScore = in.nextInt();
                in.nextLine();
            }


            for (int i = 0; i < homeScore; i++) {
                System.out.println("Enter details for goal #" + (i + 1) + " for " + cont.getFinalsHomeTeam());
                System.out.print("Scorer: ");
                String scorer = in.nextLine();
                System.out.print("Assister (or type 'none'): ");
                String assister = in.nextLine();
                System.out.print("Minute: ");
                int minute = in.nextInt();
                in.nextLine(); // Clear the newline
                    
                cont.finalsMiddleman(scorer, assister, minute);
            }

            for (int i = 0; i < awayScore; i++) {
                System.out.println("Enter details for goal #" + (i + 1) + " for " + cont.getFinalsAwayTeam());
                System.out.print("Scorer: ");
                String scorer = in.nextLine();
                System.out.print("Assister (or type 'none'): ");
                String assister = in.nextLine();
                System.out.print("Minute: ");
                int minute = in.nextInt();
                in.nextLine(); // Clear the newline

                cont.finalsMiddleman(scorer, assister, minute);
            }
            cont.calculateWinner(3, homeScore, awayScore);
            finals = true;
        } else {
            System.out.println("The scores have already been set. There is already a winner.");
        }
    }

    public void registerFinalCards(){
        System.out.println("Please enter the cards awarded in " + cont.getFinalsHomeTeam() + " vs " + cont.getFinalsAwayTeam());
        System.out.println("Home Cards:");
        int homeCards = in.nextInt();
        in.nextLine();

        System.out.println("Away Cards:");
        int awayCards = in.nextInt();
        in.nextLine();

        for(int i=0; i<homeCards;i++){
            System.out.println("Enter the details for card #"+(i+1)+" for "+cont.getFinalsHomeTeam());
            System.out.println("Player who commited the foul: ");
            String player = in.nextLine();

            System.out.println("Card awarded (YELLOW or RED)");
            String cardType = in.nextLine().toUpperCase();

            System.out.print("Minute: ");
            int minute = in.nextInt();
            in.nextLine(); // Clear the newline

            cont.cardFMiddleman(player, cardType, minute, true); //True is for home team
        }
        for(int i=0; i<awayCards;i++){
            System.out.println("Enter the details for card #"+(i+1)+" for "+cont.getFinalsAwayTeam());
            System.out.println("Player who commited the foul: ");
            String player = in.nextLine();

            System.out.println("Card awarded (YELLOW or RED)");
            String cardType = in.nextLine().toUpperCase();

            System.out.print("Minute: ");
            int minute = in.nextInt();
            in.nextLine(); // Clear the newline

            cont.cardFMiddleman(player, cardType, minute, false); //False is for away team
        }
    }

    public void run_prizeCeremony(){
        boolean flag=false;
        int select;
        
        while (!flag) {
            System.out.println("\n\nWelcome to the Prize Ceremony:\n");
			System.out.println( "Please select one of the following:\n" + 
                                "1. Consult Golden Boot Winner\n" + 
                                "2. Consult FairPlay\n" + 
                                "3. Consult Team Efficiency\n" + 
                                "4. Consult Player Efficiency \n"+
                                "5. Consult Referee Cards per Match Officiated \n"+
                                "10. Exit \n\n" 

                                // "15. Show Team Information \n" + 
                                // "20. Show Player Information \n" + 
                                // "25. Show Referee Information \n"
            );
            select=in.nextInt();
            in.nextLine();
            
            switch (select) {
                case 1:
                    cont.getGoldenBoot();
                break;
                case 2:
                    cont.getFairPlay(); 
                break;
                case 3:
                    calculateTeamEfficiency();
                break;
                case 4:
                    calculatePlayerEfficiency();
                break;
                case 5:
                    consultRefereeIndicators();
                break;
                case 10:
                    System.out.println("Thank you for using the application!");
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

    public void calculateTeamEfficiency(){
        System.out.println("Which team would you like to consult?\nHere are the available options:");
        cont.showAllTeams();
        String countrySelected = in.nextLine();
        boolean flag=cont.teamInfo(countrySelected);

        if(!flag){
            System.out.println("Sorry, we weren't able to find that team");
        } else {
            cont.calculateTeamEfficiency(countrySelected);
        }
    }

    public void calculatePlayerEfficiency(){
        System.out.println("Which player would you like to consult?");
        String playerSelected = in.nextLine();
        boolean flag=cont.playerInfo(playerSelected);
        
        if(!flag){
            System.out.println("Sorry, we weren't able to find that Player");
        } else {
            cont.calculatePlayerEfficiency(playerSelected);
        }

    }

    public void consultRefereeIndicators(){
        System.out.println("\nPlease enter the name of the referee you would like to consult");
        System.out.println("Here are the following options:\n");
        cont.showAllRefs();
        
        String refName=in.nextLine();
        boolean flag=cont.refereeInfo(refName);
        if(!flag){
            System.out.println("Sorry, we weren't able to find that referee");
        } else {
            cont.consultRefereeIndicators(refName);
        }
    }
    


    public static void main(String[] args) {
		Executable main = new Executable();
		main.run_register(flag);
        main.run_group();
        main.run_semifinals();
        main.run_finals();
        main.run_prizeCeremony();
	}

}
