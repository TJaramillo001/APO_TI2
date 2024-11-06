package model;

public class Preload extends Controller {
    private Controller cont;

    public Preload() {
        cont.preloadTeams();
        cont.preloadReferees();
    }


    /**
     * Description : Preloads a set of referees into the system if not already done.
     */
    
}
