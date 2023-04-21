package mvc.models;

import java.sql.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class Server extends Thread{
    private String name;
    private volatile boolean running = true;
    private volatile int remainingTime;
    private Queue<Client> clients;

    private int maxSize;///max number of CLients in this queue;

    public volatile int counter = 0;

    ///la synchronized un singur thread are acces la un moment dat la acea secventa de cod
    //-> poate duce la deadlock

    public Server(String name)
    {
        this.name = name;
        clients = new LinkedList<>();

        maxSize = 10000;
        remainingTime = 0;


    }


    public void run() {
        while (running)///poate trebuie sa schimb
        {
            if (clients.size() > 0) {
                try {
                    Client clientCurrent = clients.peek();///head Element
                    int serviceTime = clientCurrent.getService();
                    // System.out.println("Thread: " + name + " " + serviceTime);

                    for (int i = 0; i < serviceTime; i++)
                    {
                        this.sleep(10);
                        remainingTime--;
                    }


                    clients.poll();///scot headElement


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


            // System.out.println("Thread " + name + " has finished!");


        }
    }

    public int addClient(Client client)
    {
        if (clients.size() < maxSize) {
            clients.add(client);
            remainingTime += client.getService();
            return 1;
        }

        return 0;
    }

    public int getQueueSize()
    {
        return clients.size();
    }

    public Queue<Client> getClients() {
        return clients;
    }

    public void stopThread() {
        running = false;
        interrupt();
    }

    public int getRemainingTime()
    {
        return remainingTime;
    }
}
