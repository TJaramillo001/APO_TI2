package ui;

import java.lang.*;
import java.util.*;
import model.*;

public class Executable {
    private Scanner in;
	private Controller cont;
	private static boolean flag;

	private Executable() {
		in = new Scanner(System.in);
		cont = new Controller();
	}

    public void run(boolean flag){
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
                                "7. Exit \n");
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

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6: 

                    break;
                case 7:
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
            String selection=in.nextLine().toUpperCase();
            switch (selection){
                case "1":
                    playerPosition=PlayerPosition.GOALKEEPER;
                break;
                case "2":
                    playerPosition=PlayerPosition.DEFENDER;
                break;
                case "3":
                    playerPosition=PlayerPosition.MIDFIELDER;
                break;
                case "4":
                    playerPosition=PlayerPosition.FORWARD;
                break;
                default:
                    System.out.println("Sorry. Please enter a valid position");
                break;

            }
        }
    }


    public static void main(String[] args) {
		Executable main = new Executable();
		main.run(flag);
	}

}
