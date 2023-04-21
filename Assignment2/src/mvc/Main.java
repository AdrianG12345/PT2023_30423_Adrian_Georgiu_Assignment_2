package mvc;

import mvc.controller.SimulationManager;

public class Main {
    public static void main(String[] args)
    {
        int timeLimit = 60;

        int minProcessingTime = 1;
        int maxProcessingTime = 7;

        int minArrivalTime = 2;
        int maxArrivalTime = 40;

        int numberOfServers = 5;

        int numberOfClients = 50;




        SimulationManager simulation = new SimulationManager(timeLimit, minProcessingTime, maxProcessingTime, minArrivalTime, maxArrivalTime,
                        numberOfServers,numberOfClients);
        simulation.start();




    }
}
