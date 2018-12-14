package adventure.location;

//Certain kind of location;
//Has a cost per use and energy points that can be earned per use;
public class Facility extends Location{
    private double COST;
    private int ENERGY_POINTS;

    //Constructor, takes and sets name for location and also cost+energy points per use
    public Facility(String name, double cost, int energyPoints){
        super(name);
        this.COST=cost;
        this.ENERGY_POINTS=energyPoints;
    }

    //Getter functions
    public double getCost(){return this.COST;}

    public int getEnergyPoints(){return this.ENERGY_POINTS;}

    //Returns string of location with additionally cost and energy points to be earned per use
    public String toString(){
        String str=super.toString()+"or rest\nA rest cost "+this.COST+" \u20ac and you will receive "+this.ENERGY_POINTS+" energy points";
        return str;
    }
}