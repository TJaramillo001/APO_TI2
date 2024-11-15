package model;
import model.*;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private int roundCount; 
    /*  This number is used to keep track of each stage of the code. 
        if == 0, then we are at the registration stage
        if == 1, registration state has finished, group stage has begun
        if == 2, group stage is over, semi finals begin
        if == 3, semifinals are over, finals begin
        if == 4, tournament has concluded, prizes are awarded.
     */
    private Team[] teams;
    private Player[] players;
    private Referee[] referees;
    private int teamCount;
    private int playerCount;
    private int refereeCount;
    protected boolean preloadTeam;
    protected boolean preloadRef;
    private Random rand;
    private Group[] groups;
    private boolean generated;
    private Match[] groupAMatches;
    private Match[] groupBMatches;
    private boolean assigned;



    public Controller() {

        this.teams= new Team[8]; //Only 8 teams allowed to be divided in 2 groups of 4
        this.players = new Player[160]; //20 players per team: 20*8=160 total players.
        this.referees = new Referee[12]; // 12 referees allowed in tournament. 4 Central, 8 Assistants.
        this.teamCount = 0;
        this.playerCount = 0;
        this.refereeCount = 0;
        this.preloadTeam=false;
        this.preloadRef=false;
        this.groups = new Group[2];
        this.groups[0] = new Group("Group A"); // Group A
        this.groups[1] = new Group("Group B"); // Group B
        this.generated=false;
        this.groupAMatches = new Match[6];  // 6 matches in group stage
        this.groupBMatches = new Match[6];  // 6 matches in group stage
        this.assigned=false;
    }
    //Player related methods
    /**
     * Description : This method receives all player information needed for registration and then uses this data to generate a new player
     * @param String playerName : Corresponds to the name of the player to-be registered
     * @param String playerNum : Corresponds to the player jersey number
     * @param PlayerPosition playerPosition : Corresponds to the position in which this player plays
     * @param String country : Corresponds to the country of origin this player plays in
     * @param String teamName : Corresponds to the team name in which this player will be a part of.
     */
    public void registerPlayer(String playerName, int playerNum, PlayerPosition playerPosition, String country, String teamName){
        Team teamToAdd = findTeam(teamName);
        if (teamToAdd!=null){
        
            Player newPlayer = new Player(playerName, playerNum, playerPosition, country, 0, 0, 0, 0, 0);
            players[playerCount++] = newPlayer;
            teamToAdd.addPlayer(newPlayer);

            System.out.println("Player successfully added to "+teamToAdd.getTeamName());
        
        } else{
            System.out.println("Sorry, that team was not found.");
        }
    }
    /**
     * Description: Determines the player position based on the provided integer value. This method assigns a specific PlayerPosition enumeration value 
     * @param int check : An integer representing the player's position
     * @return : The PlayerPosition value associated with the specified integer or null if the integer does not match a valid selection.
     */
    public PlayerPosition selectPosition(int check){
        PlayerPosition position=null;
        switch (check) {
            case 1:
                position=PlayerPosition.GOALKEEPER;
                break;
            case 2: 
                position=PlayerPosition.DEFENDER;
                break;
            case 3:
                position=PlayerPosition.MIDFIELDER;
                break;
            case 4:
                position=PlayerPosition.FORWARD;
                break;
            default:
                position = null;
                break;
        }
        return position;
    }
     /**
     * Description: Searches and displays information for a player by name if found.
     * @param String playerName : The name of the player to search for.
     * @return boolean : true if the player was found and information was displayed; false otherwise.
     */
    public boolean playerInfo(String playerName){
        boolean flag=false;
        for(Player player:players){
            if(player!=null&& player.getName().equalsIgnoreCase(playerName)){
                System.out.println( "\nPlayer Name: " + player.getName() + "\n" +
                                    "Country: " + player.getCountry() + "\n" +
                                    "Jersey Number: " + player.getPlayerNum() + "\n" +
                                    "Player Position: "+player.getPlayerPosition().toString()+ "\n\n" +
                                    "Goals Scored: "+player.getGoalsScored() + "\n" +
                                    "Assists: "+player.getAssists() + "\n" +
                                    "Yellow Cards: "+player.getYellowCards() + "\n" +
                                    "Red Cards: "+player.getRedCards() + "\n" +
                                    "Matches Played: "+player.getMatchesPlayed());
                flag=true;
            }
        }
        return flag;
    }
    
     /**
     * Description: Registers a new team with specified name, country, and coach, initializing all team stats to zero.
     * @param String teamName : The name of the team to register.
     * @param String country : The country this team represents.
     * @param String coachName : The name of the team's head coach.
     */
    public void registerTeam(String teamName, String country, String coachName){
        teams[teamCount] = new Team(teamName, country, coachName, 0, 0, 0, 0, 0, 0, 0, 0);
        teamCount++;
    }

    /**
     * Description : Searches for and returns a team based on the specified team name.
     *
     * @param String name : The name of the team to search for.
     * @return Team : The team with the matching name, or null if not found.
     */

    public Team findTeam(String name){
        for (int i=0; i<teamCount; i++){
            if(teams[i].getTeamName().equalsIgnoreCase(name)){
                return teams[i];
            }
        }
        return null;
    }
    public Player findPlayer(String playerName) {

        for (Team team : teams) {
            if (team != null) {
                for (Player player : team.getPlayers()) { // Assuming `getPlayers()` returns a list of players
                    if (player != null) {
                        
                        if (player.getName().equalsIgnoreCase(playerName)) {
                            return player; // Return the player if the name matches
                        }
                    } 
                }
            } 
        }
        
        // If no player is found, return null or print a message
        System.out.println("Player not found: " + playerName);
        return null;
    }
    
    
    
    /**
     * Description : Displays the names of all registered teams in the console.
     */

    public void showAllTeams(){
        for (int i = 0; i < teams.length; i++) {
            if(teams[i]!=null){
                System.out.println("- "+teams[i].getTeamName());
            }
           
        }
    }
    /**
    * Description : Displays detailed information about a selected team, including its name, country, coach, players, and match statistics.
    * @param String selection : Name of the team to retrieve information for.
    * @return boolean : True if the team is found and information is displayed; false otherwise.
    */

    public boolean teamInfo(String selection) {
        for (Team team : teams) {
            if (team != null && team.getTeamName().equalsIgnoreCase(selection)) {
                System.out.println("\nTeam Name: " + team.getTeamName() + "\n" +
                                   "Country: " + team.getCountry() + "\n" +
                                   "Head Coach: " + team.getCoachName() + "\n\n" +
                                   "Players:\n" + team.getPlayerNames() + "\n\n" +
                                   "Matches Played: "+team.getMatchesPlayed() + "\n" +
                                   "Matches Drawed: "+team.getMatchesDraw() + "\n" +
                                   "Matches Won: "+team.getMatchesWon() + "\n" +
                                   "Matches Lost: "+team.getMatchesLost()+ "\n" +
                                   "Goals For: "+team.getGoalsFor()+ "\n" +
                                   "Goals Against: "+team.getGoalsAgainst()+ "\n" +
                                   "Yellows: "+team.getYellowCards()+ "\n" +
                                   "Reds: "+team.getRedCards()+ "\n");
                return true;
            } 
        }
        return false;
    }
    
    /**
    * Description : Registers a referee by creating a new Referee object with specified details and adds it to the referees array.
    * @param String refName : Name of the referee to be registered.
    * @param String refID : Unique identification number for the referee.
    * @param RefereeType refType : Type of referee, either central or assistant.
    * @param String country : Country of origin of the referee.
    */
    public void registerReferee(String refName, String refID, RefereeType refType, String country){
        referees[refereeCount] = new Referee(refName, refID, refType, country, 0, 0, 0);
        System.out.println("Referee successfully registered");
        refereeCount++;
    }
    /**
     * Description : Selects and returns the RefereeType based on the provided position string.
     * @param String check : Referee position input, either "CENTRAL" or "ASSISTANT".
     * @return RefereeType returnRef : Selected RefereeType if valid, or null if invalid.
     */

    public RefereeType selectPosition(String check){
        RefereeType returnRef = null;
        switch(check){
            case "CENTRAL":
                returnRef=RefereeType.CENTRAL;
                break;
            case "ASSISTANT":
                returnRef=RefereeType.ASSISTANT;
                break;
            default:
                System.out.println("Invalid position, please try again");
        }
        return returnRef;
    }
    /**
     * Description : Displays the names of all registered referees.
     */

    public void showAllRefs(){
        for (int i = 0; i < referees.length; i++) {
            if(referees[i]!=null){
                System.out.println("- "+referees[i].getName());
            }
           
        }
    }
    /**
     * Description : Retrieves and displays information about a specified referee.
     * @param String refName : The name of the referee to look up.
     * @return boolean : Indicates whether the referee was found and their information displayed.
     */
    public boolean refereeInfo(String refName){
        boolean flag = false;
        for(Referee refs: referees){
            if(refName.equalsIgnoreCase(refs.getName())&&refs!=null){
                System.out.println( "\nReferee Name: "+refs.getName() + "\n" +
                                    "Referee ID: "+refs.getRefID()+ "\n" +
                                    "Referee Country: "+refs.getCountry()+ "\n" +
                                    "Referee Position: "+refs.getRefType().toString()+ "\n" + "\n" +
                                    "Matches Officiated: "+refs.getMatchesOfficiated()+ "\n" +
                                    "Yellows Given: "+refs.getYellowsGiven()+ "\n" +
                                    "Red Givens: "+refs.getRedsGiven());
                flag=true;
                return flag;
            }
        }
        return flag;
    }
    public void preloadReferees(){
        if(!preloadRef){
             // Preload 4 Central Referees
            registerReferee("Juan Martinez", "REF001", RefereeType.CENTRAL, "Argentina");
            registerReferee("Carlos Perez", "REF002", RefereeType.CENTRAL, "Brazil");
            registerReferee("Miguel Sanchez", "REF003", RefereeType.CENTRAL, "Colombia");
            registerReferee("Pedro Gomez", "REF004", RefereeType.CENTRAL, "Chile");

            // Preload 8 Assistant Referees
            registerReferee("Alejandro Ruiz", "REF005", RefereeType.ASSISTANT, "Peru");
            registerReferee("Diego Ortega", "REF006", RefereeType.ASSISTANT, "Ecuador");
            registerReferee("Fernando Rios", "REF007", RefereeType.ASSISTANT, "Venezuela");
            registerReferee("Jorge Silva", "REF008", RefereeType.ASSISTANT, "Uruguay");
            registerReferee("Luis Fernandez", "REF009", RefereeType.ASSISTANT, "Paraguay");
            registerReferee("Ricardo Lopez", "REF010", RefereeType.ASSISTANT, "Bolivia");
            registerReferee("Sebastian Ramos", "REF011", RefereeType.ASSISTANT, "Mexico");
            registerReferee("Andres Nunez", "REF012", RefereeType.ASSISTANT, "Costa Rica");

            System.out.println("\n\n\n\n\nAll 12 referees have been added.");
            preloadRef=true;
        } else{
            System.out.println("Sorry, referees have already been preloaded");
        }
       
    }
    /**
     * Description : Preloads teams and their respective players into the system if not already done.
     */
    public void preloadTeams() {
        if(!preloadTeam){
            registerTeam("Argentina", "Argentina", "Lionel Scaloni");
            registerTeam("Colombia", "Colombia", "Nestor Lorenzo");
            registerTeam("Brazil", "Brazil", "Fernando Diniz");
            registerTeam("Chile", "Chile", "Eduardo Berizzo");
            registerTeam("Uruguay", "Uruguay", "Marcelo Bielsa");
            registerTeam("USA", "USA", "Gregg Berhalter");
            registerTeam("Peru", "Peru", "Juan Reynoso");
            registerTeam("Venezuela", "Venezuela", "Fernando Batista");
        
            // Adding players to Argentina's national team
            registerPlayer("Lionel Messi", 10, PlayerPosition.FORWARD, "Argentina", "Argentina");
            registerPlayer("Emiliano Martinez", 1, PlayerPosition.GOALKEEPER, "Argentina", "Argentina");
            registerPlayer("Angel Di Maria", 11, PlayerPosition.MIDFIELDER, "Argentina", "Argentina");
            registerPlayer("Nicolas Otamendi", 19, PlayerPosition.DEFENDER, "Argentina", "Argentina");
            registerPlayer("Lautaro Martinez", 22, PlayerPosition.FORWARD, "Argentina", "Argentina");
            registerPlayer("Rodrigo De Paul", 7, PlayerPosition.MIDFIELDER, "Argentina", "Argentina");
            registerPlayer("Leandro Paredes", 5, PlayerPosition.MIDFIELDER, "Argentina", "Argentina");
            registerPlayer("Paulo Dybala", 21, PlayerPosition.FORWARD, "Argentina", "Argentina");
            registerPlayer("Juan Foyth", 2, PlayerPosition.DEFENDER, "Argentina", "Argentina");
            registerPlayer("Gonzalo Montiel", 4, PlayerPosition.DEFENDER, "Argentina", "Argentina");
            registerPlayer("Enzo Fernandez", 8, PlayerPosition.MIDFIELDER, "Argentina", "Argentina");
            registerPlayer("Alexis Mac Allister", 14, PlayerPosition.MIDFIELDER, "Argentina", "Argentina");
            registerPlayer("Julian Alvarez", 9, PlayerPosition.FORWARD, "Argentina", "Argentina");
            registerPlayer("Marcos Acuña", 3, PlayerPosition.DEFENDER, "Argentina", "Argentina");
            registerPlayer("Lucas Martinez", 6, PlayerPosition.DEFENDER, "Argentina", "Argentina");
            registerPlayer("German Pezzella", 16, PlayerPosition.DEFENDER, "Argentina", "Argentina");
            registerPlayer("Giovani Lo Celso", 18, PlayerPosition.MIDFIELDER, "Argentina", "Argentina");
            registerPlayer("Joaquin Correa", 13, PlayerPosition.FORWARD, "Argentina", "Argentina");
            registerPlayer("Cristian Romero", 17, PlayerPosition.DEFENDER, "Argentina", "Argentina");
            registerPlayer("Nicolas Gonzalez", 15, PlayerPosition.FORWARD, "Argentina", "Argentina");
        
            // Adding players to Colombia's national team
            registerPlayer("James Rodriguez", 10, PlayerPosition.MIDFIELDER, "Colombia", "Colombia");
            registerPlayer("David Ospina", 1, PlayerPosition.GOALKEEPER, "Colombia", "Colombia");
            registerPlayer("Juan Cuadrado", 11, PlayerPosition.MIDFIELDER, "Colombia", "Colombia");
            registerPlayer("Yerry Mina", 13, PlayerPosition.DEFENDER, "Colombia", "Colombia");
            registerPlayer("Luis Diaz", 14, PlayerPosition.FORWARD, "Colombia", "Colombia");
            registerPlayer("Duvan Zapata", 9, PlayerPosition.FORWARD, "Colombia", "Colombia");
            registerPlayer("Wilmar Barrios", 5, PlayerPosition.MIDFIELDER, "Colombia", "Colombia");
            registerPlayer("Davinson Sanchez", 23, PlayerPosition.DEFENDER, "Colombia", "Colombia");
            registerPlayer("Radamel Falcao", 3, PlayerPosition.FORWARD, "Colombia", "Colombia");
            registerPlayer("Alfredo Morelos", 7, PlayerPosition.FORWARD, "Colombia", "Colombia");
            registerPlayer("Johan Mojica", 17, PlayerPosition.DEFENDER, "Colombia", "Colombia");
            registerPlayer("Stefan Medina", 2, PlayerPosition.DEFENDER, "Colombia", "Colombia");
            registerPlayer("Carlos Cuesta", 4, PlayerPosition.DEFENDER, "Colombia", "Colombia");
            registerPlayer("Mateus Uribe", 6, PlayerPosition.MIDFIELDER, "Colombia", "Colombia");
            registerPlayer("Jefferson Lerma", 8, PlayerPosition.MIDFIELDER, "Colombia", "Colombia");
            registerPlayer("Miguel Borja", 18, PlayerPosition.FORWARD, "Colombia", "Colombia");
            registerPlayer("Sebastian Villa", 20, PlayerPosition.FORWARD, "Colombia", "Colombia");
            registerPlayer("Daniel Munoz", 12, PlayerPosition.DEFENDER, "Colombia", "Colombia");
            registerPlayer("Frank Fabra", 16, PlayerPosition.DEFENDER, "Colombia", "Colombia");
            registerPlayer("Camilo Vargas", 22, PlayerPosition.GOALKEEPER, "Colombia", "Colombia");
        
            // Adding players to Brazil's national team
            registerPlayer("Neymar Jr", 10, PlayerPosition.FORWARD, "Brazil", "Brazil");
            registerPlayer("Alisson Becker", 1, PlayerPosition.GOALKEEPER, "Brazil", "Brazil");
            registerPlayer("Casemiro", 5, PlayerPosition.MIDFIELDER, "Brazil", "Brazil");
            registerPlayer("Thiago Silva", 3, PlayerPosition.DEFENDER, "Brazil", "Brazil");
            registerPlayer("Vinicius Jr", 11, PlayerPosition.FORWARD, "Brazil", "Brazil");
            registerPlayer("Marquinhos", 4, PlayerPosition.DEFENDER, "Brazil", "Brazil");
            registerPlayer("Richarlison", 9, PlayerPosition.FORWARD, "Brazil", "Brazil");
            registerPlayer("Fred", 8, PlayerPosition.MIDFIELDER, "Brazil", "Brazil");
            registerPlayer("Gabriel Jesus", 21, PlayerPosition.FORWARD, "Brazil", "Brazil");
            registerPlayer("Lucas Paqueta", 17, PlayerPosition.MIDFIELDER, "Brazil", "Brazil");
            registerPlayer("Danilo", 2, PlayerPosition.DEFENDER, "Brazil", "Brazil");
            registerPlayer("Raphinha", 19, PlayerPosition.FORWARD, "Brazil", "Brazil");
            registerPlayer("Eder Militao", 13, PlayerPosition.DEFENDER, "Brazil", "Brazil");
            registerPlayer("Philippe Coutinho", 7, PlayerPosition.MIDFIELDER, "Brazil", "Brazil");
            registerPlayer("Renan Lodi", 6, PlayerPosition.DEFENDER, "Brazil", "Brazil");
            registerPlayer("Rodrygo", 18, PlayerPosition.FORWARD, "Brazil", "Brazil");
            registerPlayer("Alex Telles", 16, PlayerPosition.DEFENDER, "Brazil", "Brazil");
            registerPlayer("Bruno Guimaraes", 14, PlayerPosition.MIDFIELDER, "Brazil", "Brazil");
            registerPlayer("Weverton", 12, PlayerPosition.GOALKEEPER, "Brazil", "Brazil");
            registerPlayer("Everton Ribeiro", 20, PlayerPosition.MIDFIELDER, "Brazil", "Brazil");
            
            // Adding 20 players to Chile's national team
            registerPlayer("Claudio Bravo", 1, PlayerPosition.GOALKEEPER, "Chile", "Chile");
            registerPlayer("Gary Medel", 17, PlayerPosition.DEFENDER, "Chile", "Chile");
            registerPlayer("Arturo Vidal", 8, PlayerPosition.MIDFIELDER, "Chile", "Chile");
            registerPlayer("Alexis Sanchez", 7, PlayerPosition.FORWARD, "Chile", "Chile");
            registerPlayer("Ben Brereton", 22, PlayerPosition.FORWARD, "Chile", "Chile");
            registerPlayer("Charles Aranguiz", 20, PlayerPosition.MIDFIELDER, "Chile", "Chile");
            registerPlayer("Mauricio Isla", 4, PlayerPosition.DEFENDER, "Chile", "Chile");
            registerPlayer("Guillermo Maripan", 3, PlayerPosition.DEFENDER, "Chile", "Chile");
            registerPlayer("Diego Valdes", 15, PlayerPosition.MIDFIELDER, "Chile", "Chile");
            registerPlayer("Paulo Diaz", 5, PlayerPosition.DEFENDER, "Chile", "Chile");
            registerPlayer("Eduardo Vargas", 11, PlayerPosition.FORWARD, "Chile", "Chile");
            registerPlayer("Jean Meneses", 14, PlayerPosition.FORWARD, "Chile", "Chile");
            registerPlayer("Francisco Sierralta", 6, PlayerPosition.DEFENDER, "Chile", "Chile");
            registerPlayer("Erick Pulgar", 13, PlayerPosition.MIDFIELDER, "Chile", "Chile");
            registerPlayer("Gabriel Suazo", 2, PlayerPosition.DEFENDER, "Chile", "Chile");
            registerPlayer("Tomas Alarcon", 18, PlayerPosition.MIDFIELDER, "Chile", "Chile");
            registerPlayer("Sebastian Vegas", 12, PlayerPosition.DEFENDER, "Chile", "Chile");
            registerPlayer("Victor Davila", 10, PlayerPosition.FORWARD, "Chile", "Chile");
            registerPlayer("Bryan Cortes", 23, PlayerPosition.GOALKEEPER, "Chile", "Chile");
            registerPlayer("Felipe Mora", 9, PlayerPosition.FORWARD, "Chile", "Chile");

            // Adding 20 players to Uruguay's national team
            registerPlayer("Fernando Muslera", 1, PlayerPosition.GOALKEEPER, "Uruguay", "Uruguay");
            registerPlayer("Diego Godin", 3, PlayerPosition.DEFENDER, "Uruguay", "Uruguay");
            registerPlayer("Federico Valverde", 15, PlayerPosition.MIDFIELDER, "Uruguay", "Uruguay");
            registerPlayer("Luis Suarez", 9, PlayerPosition.FORWARD, "Uruguay", "Uruguay");
            registerPlayer("Darwin Nunez", 11, PlayerPosition.FORWARD, "Uruguay", "Uruguay");
            registerPlayer("Rodrigo Bentancur", 6, PlayerPosition.MIDFIELDER, "Uruguay", "Uruguay");
            registerPlayer("Jose Gimenez", 2, PlayerPosition.DEFENDER, "Uruguay", "Uruguay");
            registerPlayer("Ronald Araujo", 4, PlayerPosition.DEFENDER, "Uruguay", "Uruguay");
            registerPlayer("Matias Vecino", 5, PlayerPosition.MIDFIELDER, "Uruguay", "Uruguay");
            registerPlayer("Edinson Cavani", 21, PlayerPosition.FORWARD, "Uruguay", "Uruguay");
            registerPlayer("Sebastian Coates", 19, PlayerPosition.DEFENDER, "Uruguay", "Uruguay");
            registerPlayer("Nicolas De La Cruz", 10, PlayerPosition.MIDFIELDER, "Uruguay", "Uruguay");
            registerPlayer("Lucas Torreira", 14, PlayerPosition.MIDFIELDER, "Uruguay", "Uruguay");
            registerPlayer("Maximiliano Gomez", 17, PlayerPosition.FORWARD, "Uruguay", "Uruguay");
            registerPlayer("Facundo Pellistri", 18, PlayerPosition.FORWARD, "Uruguay", "Uruguay");
            registerPlayer("Martin Caceres", 13, PlayerPosition.DEFENDER, "Uruguay", "Uruguay");
            registerPlayer("Sergio Rochet", 23, PlayerPosition.GOALKEEPER, "Uruguay", "Uruguay");
            registerPlayer("Agustin Canobbio", 16, PlayerPosition.MIDFIELDER, "Uruguay", "Uruguay");
            registerPlayer("Matias Vina", 8, PlayerPosition.DEFENDER, "Uruguay", "Uruguay");
            registerPlayer("Giorgian De Arrascaeta", 7, PlayerPosition.MIDFIELDER, "Uruguay", "Uruguay");

            // Adding 20 players to USA's national team
            registerPlayer("Matt Turner", 1, PlayerPosition.GOALKEEPER, "USA", "USA");
            registerPlayer("Walker Zimmerman", 4, PlayerPosition.DEFENDER, "USA", "USA");
            registerPlayer("Tyler Adams", 8, PlayerPosition.MIDFIELDER, "USA", "USA");
            registerPlayer("Christian Pulisic", 10, PlayerPosition.FORWARD, "USA", "USA");
            registerPlayer("Gio Reyna", 7, PlayerPosition.MIDFIELDER, "USA", "USA");
            registerPlayer("Weston McKennie", 14, PlayerPosition.MIDFIELDER, "USA", "USA");
            registerPlayer("Sergino Dest", 2, PlayerPosition.DEFENDER, "USA", "USA");
            registerPlayer("Brenden Aaronson", 11, PlayerPosition.MIDFIELDER, "USA", "USA");
            registerPlayer("Timothy Weah", 9, PlayerPosition.FORWARD, "USA", "USA");
            registerPlayer("Antonee Robinson", 5, PlayerPosition.DEFENDER, "USA", "USA");
            registerPlayer("Miles Robinson", 3, PlayerPosition.DEFENDER, "USA", "USA");
            registerPlayer("Yunus Musah", 6, PlayerPosition.MIDFIELDER, "USA", "USA");
            registerPlayer("Ricardo Pepi", 19, PlayerPosition.FORWARD, "USA", "USA");
            registerPlayer("Sean Johnson", 23, PlayerPosition.GOALKEEPER, "USA", "USA");
            registerPlayer("Jordan Morris", 13, PlayerPosition.FORWARD, "USA", "USA");
            registerPlayer("Chris Richards", 15, PlayerPosition.DEFENDER, "USA", "USA");
            registerPlayer("Kellyn Acosta", 12, PlayerPosition.MIDFIELDER, "USA", "USA");
            registerPlayer("DeAndre Yedlin", 17, PlayerPosition.DEFENDER, "USA", "USA");
            registerPlayer("Paul Arriola", 16, PlayerPosition.FORWARD, "USA", "USA");
            registerPlayer("Zack Steffen", 18, PlayerPosition.GOALKEEPER, "USA", "USA");

            // Adding 20 players to Peru's national team
            registerPlayer("Pedro Gallese", 1, PlayerPosition.GOALKEEPER, "Peru", "Peru");
            registerPlayer("Luis Advincula", 17, PlayerPosition.DEFENDER, "Peru", "Peru");
            registerPlayer("Renato Tapia", 13, PlayerPosition.MIDFIELDER, "Peru", "Peru");
            registerPlayer("Paolo Guerrero", 9, PlayerPosition.FORWARD, "Peru", "Peru");
            registerPlayer("Andre Carrillo", 18, PlayerPosition.FORWARD, "Peru", "Peru");
            registerPlayer("Christian Cueva", 10, PlayerPosition.MIDFIELDER, "Peru", "Peru");
            registerPlayer("Edison Flores", 20, PlayerPosition.MIDFIELDER, "Peru", "Peru");
            registerPlayer("Carlos Zambrano", 5, PlayerPosition.DEFENDER, "Peru", "Peru");
            registerPlayer("Miguel Araujo", 4, PlayerPosition.DEFENDER, "Peru", "Peru");
            registerPlayer("Alexander Callens", 22, PlayerPosition.DEFENDER, "Peru", "Peru");
            registerPlayer("Yoshimar Yotun", 19, PlayerPosition.MIDFIELDER, "Peru", "Peru");
            registerPlayer("Gianluca Lapadula", 14, PlayerPosition.FORWARD, "Peru", "Peru");
            registerPlayer("Luis Abram", 6, PlayerPosition.DEFENDER, "Peru", "Peru");
            registerPlayer("Rafael Tapia", 21, PlayerPosition.GOALKEEPER, "Peru", "Peru");
            registerPlayer("Pedro Aquino", 23, PlayerPosition.MIDFIELDER, "Peru", "Peru");
            registerPlayer("Marcos Lopez", 16, PlayerPosition.DEFENDER, "Peru", "Peru");
            registerPlayer("Carlos Zambrano", 5, PlayerPosition.DEFENDER, "Peru", "Peru");
            registerPlayer("Luis Advincula", 17, PlayerPosition.DEFENDER, "Peru", "Peru");
            registerPlayer("Yoshimar Yotun", 19, PlayerPosition.MIDFIELDER, "Peru", "Peru");
            registerPlayer("Jose Carvallo", 13, PlayerPosition.GOALKEEPER, "Peru", "Peru");
            
            // Adding 20 players to Venezuela's national team
            registerPlayer("Wuilker Farinez", 1, PlayerPosition.GOALKEEPER, "Venezuela", "Venezuela");
            registerPlayer("Yordan Osorio", 2, PlayerPosition.DEFENDER, "Venezuela", "Venezuela");
            registerPlayer("Mikel Villanueva", 4, PlayerPosition.DEFENDER, "Venezuela", "Venezuela");
            registerPlayer("Yangel Herrera", 6, PlayerPosition.MIDFIELDER, "Venezuela", "Venezuela");
            registerPlayer("Salomon Rondon", 9, PlayerPosition.FORWARD, "Venezuela", "Venezuela");
            registerPlayer("Jefferson Savarino", 10, PlayerPosition.FORWARD, "Venezuela", "Venezuela");
            registerPlayer("Tomas Rincon", 8, PlayerPosition.MIDFIELDER, "Venezuela", "Venezuela");
            registerPlayer("Darwin Machis", 11, PlayerPosition.FORWARD, "Venezuela", "Venezuela");
            registerPlayer("Josef Martinez", 17, PlayerPosition.FORWARD, "Venezuela", "Venezuela");
            registerPlayer("Roberto Rosales", 16, PlayerPosition.DEFENDER, "Venezuela", "Venezuela");
            registerPlayer("Rolf Feltscher", 3, PlayerPosition.DEFENDER, "Venezuela", "Venezuela");
            registerPlayer("Cristian Casseres Jr.", 13, PlayerPosition.MIDFIELDER, "Venezuela", "Venezuela");
            registerPlayer("Jhon Chancellor", 5, PlayerPosition.DEFENDER, "Venezuela", "Venezuela");
            registerPlayer("Juanpi", 14, PlayerPosition.MIDFIELDER, "Venezuela", "Venezuela");
            registerPlayer("Ronald Hernandez", 20, PlayerPosition.DEFENDER, "Venezuela", "Venezuela");
            registerPlayer("Fernando Aristeguieta", 19, PlayerPosition.FORWARD, "Venezuela", "Venezuela");
            registerPlayer("Adrian Martinez", 15, PlayerPosition.DEFENDER, "Venezuela", "Venezuela");
            registerPlayer("Luis Mago", 12, PlayerPosition.DEFENDER, "Venezuela", "Venezuela");
            registerPlayer("Daniel Carrillo", 22, PlayerPosition.MIDFIELDER, "Venezuela", "Venezuela");
            registerPlayer("Alain Baroja", 23, PlayerPosition.GOALKEEPER, "Venezuela", "Venezuela");



            System.out.println("\n\n\n\n\n"+"Teams and players for Argentina, Colombia, Brazil, Chile, Uruguay, USA, Peru and Venezuela have been preloaded.");
            preloadTeam=true;
        } else{
            System.out.println("Sorry, the teams have already been preloaded");
        }
        
    }
    public boolean verifyAdvance(){
        boolean verify=false;
        boolean team= false;
        boolean player= false;
        boolean referee= false;
        if(teamCount==8){
            System.out.println("Teams all registered");
            team=true;
        } else{
            System.out.println("Please register 8 teams to continue to the next stage.\nYou currently have "+teamCount+" registered");

        }
        if(playerCount==160){
            System.out.println("Players all registered");
            player=true;
        } else{
            System.out.println("Please register 20 players to all teams to advance to the next stage.\nYou currently have "+playerCount+" registered");
        }
        if(refereeCount==12){
            System.out.println("Referees all registered");
            referee=true;
        } else{
            System.out.println("Please register all 12 referees to advance to the next stage");
        }
        if(team&&player&&referee){
            verify=true;
        }
        return verify;
    }

    static class CustomDate {
        int day;
        int month;
        int year;

        CustomDate(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        // Method to add days to the date
        public void addDays(int days) {
            day += days;

            // Days in each month
            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            while (day > daysInMonth[month - 1]) {
                day -= daysInMonth[month - 1];
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
        }

        // Method to format the date as dd-MM-yyyy
        @Override
        public String toString() {
            return String.format("%02d-%02d-%d", day, month, year);
        }
    }
    public void generateFixture() {
        
        if(!generated){
            // Now perform the randomization
            Random rand = new Random();
            for (int i = 0; i < teamCount; i++) {
                int draw = rand.nextInt(teamCount);
                Team temp = teams[i];
                teams[i] = teams[draw];
                teams[draw] = temp;
            }

            for (int i = 0; i < teamCount; i++) {
                if (i < 4) {
                    groups[0].addTeam(teams[i]); // Add to Group A
                } else {
                    groups[1].addTeam(teams[i]); // Add to Group B
                }
            }
            
            // Generate fixtures for each group
            boolean check=false;
            for (Group group : groups) {
                if(!check){
                    System.out.println("Group A");
                    check=true;
                }else{
                    System.out.println("Group B");
                }
                group.displayGroup();
                
            }
            // Generate matches for each group
            

            CustomDate initialDate = new CustomDate(15, 11, 2024);  // Starting date for the fixture
            CustomDate matchDate = new CustomDate(initialDate.day, initialDate.month, initialDate.year);  // The first match date

            // Track last match date for each team (initialize with the start date)
            CustomDate[] lastMatchDates = new CustomDate[teamCount];
            for (int i = 0; i < teamCount; i++) {
                lastMatchDates[i] = new CustomDate(initialDate.day, initialDate.month, initialDate.year);  // All teams start at the initial date
            }

            // Generate matches for Group 1
            int matchIndex = 0;  // Initialize matchIndex
            for (int i = 0; i < 4; i++) {
                for (int j = i + 1; j < 4; j++) {
                    // Ensure both teams have at least 2 days of rest
                    CustomDate team1LastMatch = lastMatchDates[i];
                    CustomDate team2LastMatch = lastMatchDates[j];

                    // Create a new match date based on the last matches
                    matchDate = new CustomDate(initialDate.day, initialDate.month, initialDate.year);
                    while (lessThanTwo(team1LastMatch, matchDate) || lessThanTwo(team2LastMatch, matchDate)) {
                        matchDate.addDays(1);
                    }

                    // Create match and update last match dates for both teams
                    groupAMatches[matchIndex++] = new Match(groups[0].getTeams()[i], groups[0].getTeams()[j], matchDate.toString());
                    lastMatchDates[i] = matchDate;
                    lastMatchDates[j] = matchDate;

                    // Move to the next match date (after 2 days)
                    matchDate.addDays(2);
                }
            }

            // Generate matches for Group 2 (same logic as for Group 1)
            matchIndex = 0;  // Initialize matchIndex
            for (int i = 0; i < 4; i++) {
                for (int j = i + 1; j < 4; j++) {
                    CustomDate team1LastMatch = lastMatchDates[i + 4];  // Teams in group 2 start at index 4
                    CustomDate team2LastMatch = lastMatchDates[j + 4];

                    matchDate = new CustomDate(initialDate.day, initialDate.month, initialDate.year);
                    while (lessThanTwo(team1LastMatch, matchDate) || lessThanTwo(team2LastMatch, matchDate)) {
                        matchDate.addDays(1);
                    }

                    groupBMatches[matchIndex++] = new Match(groups[1].getTeams()[i], groups[1].getTeams()[j], matchDate.toString());
                    lastMatchDates[i + 4] = matchDate;
                    lastMatchDates[j + 4] = matchDate;

                    matchDate.addDays(2);
                }
            }


            // Display generated matches
            System.out.println("\nMatches for Group A:");
            for (Match match : groupAMatches) {
                if (match != null) {
                    match.displayMatch();
                }
            }

            System.out.println("\nMatches for Group B:");
            for (Match match : groupBMatches) {
                if (match != null) {
                    match.displayMatch();
                }
        
            }
            generated=true;
        } else{
            System.out.println("The groups and fixture have already been generated. For information regarding this, check the menu");
        }
    }
    private boolean lessThanTwo(CustomDate lastMatchDate, CustomDate matchDate) {
        int dayDiff = (matchDate.year - lastMatchDate.year) * 365 + (matchDate.month - lastMatchDate.month) * 30 + (matchDate.day - lastMatchDate.day);
        return dayDiff <= 2;
    }

    public void consultFixture(){
        if(generated){
            boolean check=false;
            for (Group group : groups) {
                if(!check){
                    System.out.println("Group A");
                    check=true;
                }else{
                    System.out.println("Group B");
                }
                group.displayGroup();
            }
        } else{
            System.out.println("Sorry, the groups have not yet been generated.");
        }
    }

    public void consultMatches(){
        if(generated){
            System.out.println("\nMatches for Group A:");
            for (Match match : groupAMatches) {
                if (match != null) {
                    match.displayMatch();
                }
            }

            System.out.println("\nMatches for Group B:");
            for (Match match : groupBMatches) {
                if (match != null) {
                    match.displayMatch();
                }
        
            }
            } else{
            System.out.println("Sorry, the matches have not yet been generated.");
        }
    }
    public void assignReferee(){
        if(generated){
            if(!assigned){
                assignReferee(groupAMatches);
                assignReferee(groupBMatches);
            } else{
                System.out.println("Sorry, the referees have already been assigned to matches");
            }
            
        } else{
            System.out.println("Please generate the fixture before attempting to assign referees");
        }
    }

    public void assignReferee(Match[] groupMatch) {        
            for (Match match : groupMatch) {
                Referee centralRef = null;
                Referee[] assistantRefs = new Referee[2];
                int assistantCount = 0;
    
                shuffleReferees(referees);
        
                System.out.println("\nThe referees for:");
                match.displayMatch();
        
                for (Referee ref : referees) {
                    // Check if the referee is from a different country than either team
                    if (!ref.getCountry().equals(match.getHomeTeam().getCountry()) && !ref.getCountry().equals(match.getAwayTeam().getCountry())) {
        
                        // Assign central referee if not already assigned
                        if (ref.getRefType() == RefereeType.CENTRAL && centralRef == null) {
                            centralRef = ref;
                            
                        }
                        // Assign assistant referees if needed
                        else if (ref.getRefType() == RefereeType.ASSISTANT && assistantCount < 2) {
                            assistantRefs[assistantCount] = ref;
                            
                            assistantCount++;
                        }
                    }
                    // Stop searching if we have assigned the central and both assistants
                    if (centralRef != null && assistantCount == 2) {
                        System.out.println("\nCentral Referee: " + centralRef.getName() + "\n" +
                                               "Referee ID: " + centralRef.getRefID() + "\n" +
                                               "Referee Country: " + centralRef.getCountry() + "\n" +
                                               "Referee Position: " + centralRef.getRefType().toString() + "\n" +
                                               "Matches Officiated: " + centralRef.getMatchesOfficiated() + "\n" +
                                               "Yellows Given: " + centralRef.getYellowsGiven() + "\n" +
                                               "Reds Given: " + centralRef.getRedsGiven());
                        for(int j=0; j<assistantCount;j++){
                            System.out.println("\nAssistant Referee #" + (j + 1) + ": " + assistantRefs[j].getName() + "\n" +
                                               "Referee ID: " + assistantRefs[j].getRefID() + "\n" +
                                               "Referee Country: " + assistantRefs[j].getCountry() + "\n" +
                                               "Referee Position: " + assistantRefs[j].getRefType().toString() + "\n" +
                                               "Matches Officiated: " + assistantRefs[j].getMatchesOfficiated() + "\n" +
                                               "Yellows Given: " + assistantRefs[j].getYellowsGiven() + "\n" +
                                               "Reds Given: " + assistantRefs[j].getRedsGiven());
                        }
                        
                        break;
                    }
                }
        
                // Check if all referees have been assigned correctly
                if (centralRef == null || assistantCount < 2) {
                    System.out.println("There are not sufficient available referees for match: " + match);
                } else {
                    // Assign referees to the match if all are assigned
                    match.setCentralReferee(centralRef);
                    match.setAssistantReferees(assistantRefs);
                    assigned=true;
                }
            }
    }

    private void shuffleReferees(Referee[] referees) {
        Random random = new Random();
        for (int i = referees.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Referee temp = referees[index];
            referees[index] = referees[i];
            referees[i] = temp;
        }
    }
    // Function to simulate goals for a match
    public void simulateMatchGoals(Match match) {
        Random random = new Random();
        int homeScore = random.nextInt(5); // Randomize up to 4 goals for home
        int awayScore = random.nextInt(5); // Randomize up to 4 goals for away
    
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    
        // Simulate home team goals
        for (int i = 0; i < homeScore; i++) {
            Player scorer = match.getHomeTeam().getPlayers()[random.nextInt(match.getHomeTeam().getPlayers().length)];
            Player assister = random.nextBoolean() ? match.getHomeTeam().getPlayers()[random.nextInt(match.getHomeTeam().getPlayers().length)] : null;
            
            int minute = random.nextInt(91); // Random minute between 0 and 90
            
            addGoalToMatch(match, scorer.getName(), assister != null ? assister.getName() : "none", minute);
            incrementTotalGoalsForTeam(scorer, match); // Update the team's total goals
        }
    
        // Simulate away team goals
        for (int i = 0; i < awayScore; i++) {
            Player scorer = match.getAwayTeam().getPlayers()[random.nextInt(match.getAwayTeam().getPlayers().length)];
            Player assister = random.nextBoolean() ? match.getAwayTeam().getPlayers()[random.nextInt(match.getAwayTeam().getPlayers().length)] : null;
            int minute = random.nextInt(91); // Random minute between 0 and 90
            addGoalToMatch(match, scorer.getName(), assister != null ? assister.getName() : "none", minute);
            incrementTotalGoalsForTeam(scorer, match); // Update the team's total goals
        }
    }
    

    public void incrementTotalGoalsForTeam(Player scorer, Match match) {
        Team team1 = match.getHomeTeam();
        Team team2 = match.getAwayTeam();
        boolean check=false;
    
        for (Player player : match.getHomeTeam().getPlayers()) {
            if (player.equals(scorer)) {
                team1.incrementGoalsFor();
                team2.incrementGoalsAgainst();
                check=true;
                break;
            }
        }

        for (Player player : match.getAwayTeam().getPlayers()) {
            if (player.equals(scorer)) {
                team2.incrementGoalsFor();
                team1.incrementGoalsAgainst();
                check=true;
                break;
            }
        }
        

        if (!check) {
            // Handle case where scorer is not found in either team
            System.out.println("Error: Player " + scorer.getName() + " is not part of either team.");
            return;
        }
        
    }

    public void simulateGroupMatches() {
        for (Match match : groupAMatches) {
            simulateMatchGoals(match);
            updateMatchesPlayed(match.getHomeTeam());
            updateMatchesPlayed(match.getAwayTeam());
            updateMatchesOfficiated(match.getCentralReferee(), match.getAss1Referee(), match.getAss2Referee());
        }
        for (Match match : groupBMatches) {
            simulateMatchGoals(match);
            updateMatchesPlayed(match.getHomeTeam());
            updateMatchesPlayed(match.getAwayTeam());
            updateMatchesOfficiated(match.getCentralReferee(), match.getAss1Referee(), match.getAss2Referee());
        }
        
        System.out.println("Scores simulated");
    }

    
    // In Controller class
    public Match[] getGroupAMatches() {
        return groupAMatches;  
    }

    public Match[] getGroupBMatches() {
        return groupBMatches;  
    }
    

    public void addGoalToMatch(Match match, String scorerName, String assisterName, int minute) {
        Player scorer = findPlayer(scorerName);
        Player assister = assisterName.equalsIgnoreCase("none") ? null : findPlayer(assisterName);
    
        if (scorer != null) {
            match.addGoalDetail(scorer, assister, minute);
            scorer.incrementGoals();
            if (assister != null) {
                assister.incrementAssists();
            }
        } else {
            System.out.println("Player not found: " + scorerName);
        }
        
    }
    public void sendGoals(Match match, int homeScore, int awayScore){
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }
    
    public void showGameScores() {
        for (Match match : groupAMatches) {
            match.printScore();
            if (!match.isScoreProcessed()) {
                
        
                Team homeTeam = match.getHomeTeam();
                Team awayTeam = match.getAwayTeam();
        
                if (match.getHomeScore() > match.getAwayScore()) {
                    // Home team wins
                    homeTeam.incrementMatchesWon();
                    awayTeam.incrementMatchesLost();
                } else if (match.getAwayScore() > match.getHomeScore()) {
                    // Away team wins
                    awayTeam.incrementMatchesWon();
                    homeTeam.incrementMatchesLost();
                } else {
                    // Draw
                    homeTeam.incrementMatchesDraw();
                    awayTeam.incrementMatchesDraw();
                }
    
                // Mark the match as processed
                match.setScoreProcessed(true);
            }
        }
    
        for (Match match : groupBMatches) {
            match.printScore();
            if (!match.isScoreProcessed()) {
                
        
                Team homeTeam = match.getHomeTeam();
                Team awayTeam = match.getAwayTeam();
        
                if (match.getHomeScore() > match.getAwayScore()) {
                    homeTeam.incrementMatchesWon();
                    awayTeam.incrementMatchesLost();
                } else if (match.getAwayScore() > match.getHomeScore()) {
                    awayTeam.incrementMatchesWon();
                    homeTeam.incrementMatchesLost();
                } else {
                    homeTeam.incrementMatchesDraw();
                    awayTeam.incrementMatchesDraw();
                }
    
                // Mark the match as processed
                match.setScoreProcessed(true);
            }
        }
    }
    
    public void updateMatchesPlayed(Team team) {
        team.incrementMatchesPlayed();
        for (Player player : team.getPlayers()) {
            player.incrementMatchesPlayed();  
        }
    }
    public void updateMatchesOfficiated(Referee central, Referee ass1, Referee ass2){
        central.incrementMatchesOfficiated();
        ass1.incrementMatchesOfficiated();
        ass2.incrementMatchesOfficiated();
    }

    public void addCardToMatch(Match match, String playerName, String cardType, int minute, boolean which){
        Player player = findPlayer(playerName);
        Referee referee = match.getCentralReferee();
        CardType card=null;

        switch(cardType){
            case "YELLOW":
                card = CardType.YELLOW;
                player.incrementYellowCards();
                referee.incrementYellowCards();
                if(which){
                    match.getHomeTeam().incrementYellowCards();
                } else {
                    match.getAwayTeam().incrementYellowCards();
                }
            break;
            case "RED":
                card=CardType.RED;
                player.incrementRedCards();
                referee.incrementRedCards();
                if(which){
                    match.getHomeTeam().incrementRedCards();
                } else {
                    match.getAwayTeam().incrementRedCards();
                }
            break;
        }


        if(player!=null){
            match.addCardDetail(player, card, minute);
            
        }
    }
    private boolean standingsPrinted;
    private ArrayList<Team> semifinals = new ArrayList<>();
    public void printStandings(){
        for(Group group : groups){
            Team[] temp = group.getTeams();
            for(int i=0; i<temp.length;i++){
                for(int j=0;j<temp.length-1; j++){

                    int points1 = ((temp[j].getMatchesWon()*3)+(temp[j].getMatchesDraw()*1));
                    int points2 = ((temp[j+1].getMatchesWon()*3)+(temp[j+1].getMatchesDraw()*1));
                    
                    if (points2 > points1) {
                        Team holder = temp[j];   // Swap j and j + 1
                        temp[j] = temp[j + 1];
                        temp[j + 1] = holder;
                    } else if (points1 == points2) {
                        int goalDifference1 = temp[j].getGoalsFor() - temp[j].getGoalsAgainst();
                        int goalDifference2 = temp[j + 1].getGoalsFor() - temp[j + 1].getGoalsAgainst();
                        if (goalDifference2 > goalDifference1) {
                            Team holder = temp[j]; // Swap j and j + 1
                            temp[j] = temp[j + 1];
                            temp[j + 1] = holder;
                        }
                    }

                }
            }
            System.out.printf(  "%-20s %2s %2s %2s %2s %2s %2s %2s %2s%n", 
                            "Team", "PJ", "G", "E", "P", "GF", "GC", "DG", "Pts");
            int counter = 0;
            for (Team team : group.getTeams()){
                int points = (team.getMatchesWon()*3)+(team.getMatchesDraw()*1);
                int goalDifference = team.getGoalsFor() - team.getGoalsAgainst();
                System.out.printf(  "%-20s %2d %2d %2d %2d %2d %2d %2d %2d%n",
                                            team.getTeamName(),
                                            team.getMatchesPlayed(),
                                            team.getMatchesWon(),
                                            team.getMatchesDraw(),
                                            team.getMatchesLost(),
                                            team.getGoalsFor(),
                                            team.getGoalsAgainst(),
                                            goalDifference,
                                            points);
                if (counter!=2){
                    semifinals.add(team);
                    counter++;
                }
            }
            System.out.println("\n");
        }
        
        standingsPrinted=true;
    }
    public boolean verifyTwo(){
        if(standingsPrinted){
            return true;
        } else {
            System.out.println("Please verify that all matches have been played and that the standings have been updated before advancing to the semi finals.");
            return false;
        }

    }
    public void createAndShowSemis(){
        for(Team teams : semifinals){
            System.out.println(teams.getTeamName());
        }
    }

}
