package by.epam.training.bin;

import java.util.Random;

public class Container {

    private int containerId;

    Container() {
        this.containerId = (new Random().nextInt(100))+1;
    }

    int getContainerId() {
        return containerId;
    }
}
