package mvc.controller;

import mvc.models.Client;
import mvc.models.Server;

import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Server> servers;


    public Scheduler(int nrQueues, int maxNrInQueue, Object lock)
    {
        ///creez queues
        //aici imi fac lista de queues
        ///manage uez queues
        servers = new ArrayList<>();

        for (int i = 0; i < nrQueues; i++)
        {
            Server server = new Server( Integer.toString(i), maxNrInQueue, lock);///imi fac threadurile
            servers.add(server);///le bag in lista

            servers.get(i).start();///il pornesc
        }
    }

    public ArrayList<Server> getServers() {
        return servers;
    }

    public void stop()
    {
        for (Server v : servers)
        {
            v.stopThread();
        }
    }

}
