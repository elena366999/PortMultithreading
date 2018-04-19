package by.epam.training.bin;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ship extends Thread {
    public static final Logger LOG = Logger.getLogger(Ship.class);
    private int amountContainer;
    public static final int MAX_SHIP_CAPACITY = 5;
    private Dock dock;
    private Pier pier;
    private int id;
    private List<Container> shipContainers;

    Ship(int amountContainer, Dock dock, int id) {
        if (amountContainer <= MAX_SHIP_CAPACITY && amountContainer > 0) {
            this.amountContainer = amountContainer;
            this.dock = dock;
            this.id = id;
            this.shipContainers = new CopyOnWriteArrayList<Container>();
            addCargo();
        }else{
            LOG.error("Attempting to create an object with invalid parameters");
            throw new IllegalArgumentException();
        }

    }


    private void addCargo() {
        Container[] containers = new Container[MAX_SHIP_CAPACITY];
        for (int i = 0; i < amountContainer; i++) {
            containers[i] = new Container();
            shipContainers.add(containers[i]);
        }
    }

    boolean noAvailableContainers(){
        return this.amountContainer == 0 && Dock.getContainersInDock().size() == 0;
    }

    public int getShipId() {
        return id;
    }


    public void unloadShip(){
        for (int i = 0; i < amountContainer; i++){
            Container item = shipContainers.remove(0);
            if (dock.addContainerToDock(item)) {
                System.out.println("Ship " + id + " unloaded container #"
                        + item.getContainerId());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    LOG.error( e.getMessage());
                }
            } else {
                System.out.println("No available space in dock!");
                return;
            }
        }
        amountContainer=0;
        System.out.println("Containers on ship # " +id + ": " + shipContainers.size());
        noAvailableContainers();
    }

    public void loadShip(){
        if (Dock.getContainersInDock().size() >= MAX_SHIP_CAPACITY) {
            for (int i = 0; i < MAX_SHIP_CAPACITY; i++) {
                Container item = dock.removeContainerFromDock();
                if (item == null) {
                    System.out.println("No containers available in dock!");
                    return;
                }
                if (shipContainers.add(item)) {
                    System.out.println("Ship " + id + " loaded container # "
                            + item.getContainerId());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        LOG.error( e.getMessage());
                    }
                } else {
                    System.out.println("No available space in ship storage!");
                    return;
                }
            }
            System.out.println("Ship # "+ this.getShipId() + " is loading");
            System.out.println("Containers on ship# " + id+ ": "+ shipContainers.size());
            noAvailableContainers();
        } else {
            try {
                Thread.sleep(500 * MAX_SHIP_CAPACITY);
            } catch (InterruptedException e) {
                LOG.error( e.getMessage());
            }
            System.out.println("Ship # "+ id + " is loading");
            noAvailableContainers();
        }
    }

    @Override
    public void run() {

            System.out.println("Ship # "+ id + " (" +amountContainer + " containers)" + " is waiting for permission");
            this.pier = dock.getPier();
            System.out.println("Ship # " + id + " (" +amountContainer + " containers)" + " got permission" + "(Pier # " +pier.getPierNumber() + ")");

            while(!noAvailableContainers()){
                if(amountContainer>0 && Dock.MAX_DOCK_CAPACITY - Dock.getContainersInDock().size()>=amountContainer) {
                    unloadShip();
                } else if (this.amountContainer == 0) {
                   loadShip();
                }
                break;
            }
        System.out.println("Ship # "+ id + " is released");
        dock.returnResource(pier);
    }
}
