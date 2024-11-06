package ui;

import java.lang.*;
import java.util.*;
import model.Controller;
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
            System.out.println("Sorry, we weren't able to find that team");
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
                                "10. Exit \n");
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
    }


    public static void main(String[] args) {
		Executable main = new Executable();
		main.run_one(flag);
        main.run_two();
	}

}
