/*
 * OOP - WS1819 - SBL 1
 * Vorname Nachname (Matrikelnr.)
 * Vorname Nachname (Matrikelnr.)
 */
package adventure.game;
import adventure.location.*;
import adventure.game.Player;
import java.util.Scanner;

public class Game {
    public static Scanner input= new Scanner(System.in);
    public static void main(String[] argv){
        // Create Locations
        FunRide rollerCoaster = new FunRide("Roller Coaster", 2.5, 5);
        Facility restroom = new Facility("Restroom", 0.5, 1);
        FunRide bumperCar = new FunRide("Bumper Car", 1, 2);
        Facility kiosk = new Facility("Kiosk", 2, 5);
        FunRide wildWaterChannel = new FunRide("Wild-Water Channel", 2.5, 5);
        Facility restaurant = new Facility("Restaurant", 10, 20);
        FunRide carousel = new FunRide("Carousel", 1, 2);
        FunRide freefallTower = new FunRide("Freefall Tower", 1, 2);
        Location entrance = new Location("Entrance");
        Location parkingLot = new Location("Parking Lot");

        // Create Paths
        rollerCoaster.createPath("up", restroom);
        restroom.createPath("left", bumperCar);
        bumperCar.createPath("down", kiosk);
        bumperCar.createPath("left", wildWaterChannel);
        wildWaterChannel.createPath("down", restaurant);
        restaurant.createPath("down", carousel);
        kiosk.createPath("left", carousel);
        restaurant.createPath("left", freefallTower);
        freefallTower.createPath("down", entrance);
        carousel.createPath("left", entrance);
        entrance.createPath("down", parkingLot);


        //initialize player and describe situation
        Player p1=new Player(rollerCoaster);
        System.out.println("You're in a theme park, it's getting dark.\nYou want to go to your car.\nOn the way there" +
                "you want to have as much fun as possible.\nBut you have only limited energy and money left.");

        //game round
        //do until Parking Lot is reached
        while (!p1.getCurrentLocation().getName().equals("Parking Lot")){
            //current state of player
            System.out.println(p1.toString());
            //take command
            String command="";
            //until valid entry
            while(!(command.equals("ride") || command.equals("rest")||command.equals("up") || command.equals("down") || command.equals("left") || command.equals("right"))) {
                System.out.print("> ");
                //always immediately change to lower case
                command = input.nextLine().toLowerCase();
            }
            //use location or walk direction
            if (command.equals("ride") || command.equals("rest")) {
                p1.stay();
            } else if (command.equals("up") || command.equals("down") || command.equals("left") || command.equals("right")) {
                p1.walk(command);
            }


            //Check if energy<0 => lost
            if(p1.getEnergy()<0){
                System.out.println("Game over. You collapse exhausted and the park inspector must carry you out of the park.\n" +
                        "You lose all your fun points! You have "+p1.getMoney()+" \u20ac.");
                p1.gameOver(parkingLot);
            }

            //Check if Parking Lot was reached
            if(p1.getCurrentLocation().getName().equals("Parking Lot")){
                System.out.println("You are here now: Parking lot\nCongratulations, you made it. You have collected" +
                        " "+p1.getFunPoints()+" fun points and have "+p1.getMoney()+" \u20ac.");
            }

        }


    }

}
