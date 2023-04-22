package mvc;

import mvc.controller.Controller;
import mvc.view.ViewFrame;

import java.lang.constant.Constable;

public class Main {
    public static void main(String[] args)
    {
//        int timeLimit = 200;
//
//        int minProcessingTime = 3;
//        int maxProcessingTime = 9;
//
//        int minArrivalTime = 10;
//        int maxArrivalTime = 100;
//
//        int numberOfServers = 20;
//
//        int numberOfClients = 1000;
//
//        int maxNrPeopleInQueue = 10;

        /*
        SimulationManager simulation = new SimulationManager(timeLimit, minProcessingTime, maxProcessingTime, minArrivalTime, maxArrivalTime,
                        numberOfServers,numberOfClients, maxNrPeopleInQueue);
        simulation.start();*/

        ViewFrame view = new ViewFrame();
        Controller controller = new Controller(view);
    }
}
