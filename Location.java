package adventure.location;

//Basic class for the locations, has the attributes name and neighbouring locations;
public class Location{
    private String name;
    private Location leftLocation;
    private Location rightLocation;
    private Location upLocation;
    private Location downLocation;

    //Constructor, sets name;
    public Location(String name){
        this.name=name;
    }

    //Getter function for name;
    public String getName(){
        return this.name;
    }

    //Returns the location in the wished direction;
    public Location getNeighboringLocation(String direction){
        if(direction.equals("up")){
            return this.upLocation;
        }
        else if(direction.equals("down")){
            return this.downLocation;
        }
        else if(direction.equals("left")){
            return this.leftLocation;
        }
        else if(direction.equals("right")){
            return this.rightLocation;
        }
        //warn if direction is not valid; return current location
        else{
            System.out.println("Please enter valid direction! (up, down, left or right)");
            return this;
        }
    }

    //Returns name and possible directions to go;
    public String toString(){
        String str=this.name+". You can go: ";
        if(getNeighboringLocation("up")!=null){
            str=str+"up ";
        }
        if(getNeighboringLocation("down")!=null){
            str=str+"down ";
        }
        if(getNeighboringLocation("left")!=null){
            str=str+"left ";
        }
        if(getNeighboringLocation("right")!=null){
            str=str+"right ";
        }
        return str;
    }

    //Locations are viewed as equal if both are locations and the name is equal;
    public boolean equals(Object obj){
        if (obj==null){
            return false;
        }
        if(obj==this){
            return true;
        }
        if(!obj.getClass().equals(this.getClass())){
            return false;
        }
        Location loc=(Location) obj;
        return (this.name==loc.name);
    }

    //creates a path between two locations:
    //This is done by setting the wished location as location in the wished direction;
    //Both locations get the connection at the same time, means if for example an upLocation is set
    //also a downLocation will be set at the location to be connected;
    public void createPath(String direction, Location location){
        if(direction.equals("up")){
            this.upLocation=location;
            location.downLocation=this;
        }
        else if(direction.equals("down")){
            this.downLocation=location;
            location.upLocation=this;
        }
        else if(direction.equals("left")){
            this.leftLocation=location;
            location.rightLocation=this;
        }
        else if(direction.equals("right")){
            this.rightLocation=location;
            location.leftLocation=this;
        }

        //catch invalid input
        else{
            System.out.println("Enter valid direction!");
        }
    }
}