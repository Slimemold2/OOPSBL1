package adventure.location;

//Certain kind of location;
//Has a cost per ride and fun points that can be earned per ride;
public class FunRide extends Location{
    private double COST;
    private int FUN_POINTS;

    //Constructor, takes and sets name for location and also cost+fun points per ride
    public FunRide(String name, double cost, int funPoints){
        super(name);
        this.COST=cost;
        this.FUN_POINTS=funPoints;
    }

    //Getter functions
    public double getCost(){return this.COST;}

    public int getFunPoints(){return this.FUN_POINTS;}

    //Returns string of location with additionally cost and fun points to be earned per ride
    public String toString(){
        String str=super.toString()+" or ride\nA ride cost "+this.COST+" \u20ac and you will receive "+this.FUN_POINTS+" fun points";
        return str;
    }
}