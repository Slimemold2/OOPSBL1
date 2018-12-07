package adventure.game;
import java.util.Random;
import adventure.location.*;

//Class for the Player;
//Has attributes money, energy and fun points;
//Furthermore has an attribute for the current location and a random number generator;
public class Player{
    public static Random randomGenerator=new Random(10);
    private Location currentLocation;
    private double money;
    private int energy;
    private int funPoints;

    //Constructor, currentLocation is given as parameter;
    //enery is randomly set between 60 and 200;
    //money is randomly set between 0.00 and 50.00;
    public Player(Location startLocation){
        this.currentLocation=startLocation;
        this.energy=randomGenerator.nextInt(141)+60;
        int euro=randomGenerator.nextInt(51);
        int cent=randomGenerator.nextInt(100);
        if(euro!=50){
            this.money=euro+(cent/100);
        }
        else{this.money=euro;}
    }

    //Getter methods
    public double getMoney(){return this.money;}

    public int getEnergy(){return this.energy;}

    public int getFunPoints(){return this.funPoints;}

    //not in the task but helpful to check whether game is over or not in the main method in Game.java
    public Location getCurrentLocation(){return this.currentLocation;}

    //Returns string with all important current information like location, energy and money left and fun points earned.
    public String toString(){
        String str=this.currentLocation.getName()+"\nYou have "+this.getEnergy()+" Energy and "+this.getMoney()+" \u20ac." +
                " You already earned "+getFunPoints()+" fun points.";
        return str;
    }

    //Lets the player walk in the wished direction, effectively changing the current location and reducing
    //the energy points by 10.
    //! NOT catching invalid input bc of task description...
    public void walk(String direction){
        this.currentLocation=this.currentLocation.getNeighboringLocation(direction);
        this.energy=this.energy-10;
    }

    //Uses the current location, either takes a ride if location is FunRide or rests if location is Facility;
    //Only if enough money is available;
    //Usage of subclasses is necessary!
    public void stay(){
        //check whether current location is FunRide or Facility
        if(this.currentLocation.getClass().toString().equals("class adventure.location.FunRide")) {
            //define as subclass so methods from FunRide that are not contained in Location can be used
            FunRide loc=((FunRide) this.currentLocation);
            if(this.money>loc.getCost()){
                this.money=this.money-loc.getCost();
                this.funPoints=this.funPoints+loc.getFunPoints();
            }
        }
        else if(this.currentLocation.getClass().toString().equals("class adventure.location.Facility")){
            Facility loc=((Facility) this.currentLocation);
            if(this.money>loc.getCost()){
                this.money=this.money-loc.getCost();
                this.energy=this.energy+loc.getEnergyPoints();
            }
        }
    }

    //Set the current location to the passed location;
    //Reduce fun points to zero;
    public void gameOver(Location location){
        this.currentLocation=location;
        this.funPoints=0;
    }
}