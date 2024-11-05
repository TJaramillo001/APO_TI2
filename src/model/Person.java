package model;

public abstract class Person {
    protected String name;
    protected String country;
    
    /**
     * Description : This is the constructor class which defines all people within the tournament. 
     * @param String name : Corresponds to a name of a person
     * @param String country : Corresponds to this person's country of origin
     */
    public Person(String name, String country) {
        this.name = name;
        this.country = country;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    
}
