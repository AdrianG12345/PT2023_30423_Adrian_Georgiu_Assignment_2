package mvc.controller;

import mvc.models.Client;
import mvc.models.Server;

import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Server> servers;


    public Scheduler(int nrQueues)
    {
        ///creez queues
        //aici imi fac lista de queues
        ///manage uez queues
        servers = new ArrayList<>();

        for (int i = 0; i < nrQueues; i++)
        {
            Server server = new Server( Integer.toString(i) );
            servers.add(server);

            servers.get(i).start();

        }

    }


    public void addClient(Client client)
    {
        int poz = 0;
        int min = 10000000;
        for (int i = 0; i < servers.size(); i++)
        {
           // if (servers.get(i).)
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
