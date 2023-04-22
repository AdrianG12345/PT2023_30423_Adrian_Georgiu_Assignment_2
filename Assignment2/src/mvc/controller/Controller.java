package mvc.controller;

import mvc.view.ViewFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private ViewFrame view;
    public Controller(ViewFrame view)
    {
        this.view = view;
        this.view.addSimulateListener(new SimulateListener());
    }

    class SimulateListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            ///aici fac simulation manager
            int timeLimit = view.getTimeTextField();

            int minServiceTime = view.getMinServiceTextField();
            int maxServiceTime = view.getMaxServiceTextField();

            int minArrivalTime = view.getMinArrivalTextField();
            int maxArrivalTime = view.getMaxArrivalTextField();

            int numberOfServers = view.getNrQueuesTextField();

            int numberOfClients = view.getNrClientsTextField();

            int maxNrPeopleInQueue = view.getMaxInQTextField();
            System.out.println("S-a apasat buton");
            SimulationManager simulation = new SimulationManager(timeLimit, minServiceTime, maxServiceTime, minArrivalTime, maxArrivalTime,
                        numberOfServers,numberOfClients, maxNrPeopleInQueue, view);
            simulation.start();




        }
    }
}
