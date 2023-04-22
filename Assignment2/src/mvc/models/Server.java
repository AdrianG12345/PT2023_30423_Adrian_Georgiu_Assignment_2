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
    private boolean continueExecution;
    private Object lock;
    private int waiting = 1;

    private int maxSize;///max number of CLients in this queue;

    public volatile int counter = 0;

    ///la synchronized un singur thread are acces la un moment dat la acea secventa de cod
    //-> poate duce la deadlock

    public Server(String name, int maxSize, Object lock)
    {
        this.lock = lock;
        this.name = name;
        clients = new LinkedList<>();

        this.maxSize = maxSize;
        remainingTime = 0;

    }


    public void run() {
        while (running)///poate trebuie sa schimb
        {
            if (clients.size() > 0)
            {
                try
                {
                    Client clientCurrent = clients.peek();///head Element
                    int serviceTime = clientCurrent.getService();

                    this.sleep(1000 * serviceTime);

                    remainingTime -= serviceTime;
                    clients.poll();///scot headElement
                    synchronized (lock)
                    {
                        lock.wait();
                    }

                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public int addClient(Client client)
    {
        if (clients.size() < maxSize)
        {
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
