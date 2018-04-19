package by.epam.training.bin;

//import org.apache.log4j.Logger;

public class Main {
    public static void main(String... args){
        Dock dock = Dock.createDock(Dock.PIER_NUMBER, 20);
        System.out.println("Initial amount of containers in dock: "+ Dock.getContainersInDock().size());
       // Random random =  new Random();

        new Ship(10,dock, 1).start();
        new Ship(0, dock, 2).start();
        new Ship(2, dock, 3).start();
        new Ship(1, dock, 4).start();
        new Ship(4, dock, 5).start();
    }
}





