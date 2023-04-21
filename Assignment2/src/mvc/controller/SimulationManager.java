package mvc.controller;

import mvc.models.Client;
import mvc.models.Server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager extends Thread{
    //ii THREAD

    private int timeLimit = 100;
    private int maxProcessingTime = 20;
    private int minProcessingTime = 2;
    private int minArrivalTime = 0;
    private int maxArrivalTime = 100;
    private int numberOfServers = 3;
    private int numberOfClients = 100;
    private int currentTime;

    private ArrayList<Client> clients;

    private BufferedWriter writer;

    private Scheduler scheduler;

    ///aici am lista de clienti
    ///aici generez clientii



    ///in scheduler pun clientii in QUEUE incerc

    ///aici afisez TOTUL in fisier txt



    public SimulationManager(int timeLimit, int minProcessingTime, int maxProcessingTime,
                             int minArrivalTime, int maxArrivalTime,
                            int numberOfServers, int numberOfClients)
    {
        clients = new ArrayList<>();
        currentTime = 0;
        //currentTime.set(0);

        this.timeLimit = timeLimit;

        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;

        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;

        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;

        generateNClients(numberOfClients);
        ///afisezNclients



        scheduler = new Scheduler(numberOfServers);

        try{
            File file = new File( "test.txt");

            writer = new BufferedWriter(new FileWriter(file.getName()) );
        }
        catch(IOException e){
            e.printStackTrace();
        }


        String string = new String();
        for(Client v : clients)
        {
            string += v.getId() + " " + v.getArrival() + " " + v.getService() + "\n";
        }
        scrieFile(string);


//        try {
//            writer.flush();
//            writer.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }

    }


    public void generateNClients(int N)
    {
        Random rand = new Random();
        for (int i = 0; i < N; i++)
        {
            int arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            int serviceTime = rand.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;

            clients.add(new Client(i + 1, serviceTime, arrivalTime));
        }
    }


    public void scrieFile(String string)
    {
        try {
            writer.write(string + "\n");
        } catch (IOException e) {
            System.out.println("ERROARE SCRIERE");
            throw new RuntimeException(e);
        }

        System.out.println(string);///in caz ca vreau si consola
    }
    public void run()
    {
        //scrieFile("Time: " + currentTime + "\n");

        while( currentTime < timeLimit)
        {

            /*
            maybe peak hour calculated here
             */
            ///adaug in queue clientii


            scrieFile("Time: " + currentTime + "\n");

            ///afisez tot ce am in queues;
            String string = new String();

            string += "Waiting clients: ";
            for (int i = 0; i < clients.size(); i++)///am erori daca nu fac asa
            {
                if (currentTime >= clients.get(i).getArrival()) {
                    Server rezultat = scheduler.getServers().get(0);
                    for (Server obj : scheduler.getServers())///aleg dupa timpul ramas in queue minim
                    {
                        if (rezultat.getRemainingTime() > obj.getRemainingTime())
                        {
                            rezultat = obj;
                        }
                    }
                    if (rezultat.addClient(clients.get(i)) == 1)///daca a fost adaugat cu succes
                    {
                        clients.remove(clients.get(i));
                        i--;
                    }
                }
            }

            ///daca au ramas in asteptare
            for (Client c : clients)
            {
                string += "(" + c.getId() + "," + c.getArrival() + "," + c.getService() + ") ";
            }

//            for (int i = 0; i < clients.size(); i++)
//            {
//                if (currentTime == clients.get(i).getArrival())
//                {
//                    ///il adaug in queue-ul liber
//                    ///momentan queue 1
//                    Server server = scheduler.getServers().get(0);
//                    server.addClient(clients.get(i));
//                    clients.remove(clients.get(i));
//                }
//                else
//                {
//                    string += "(" + clients.get(i).getId() + "," + clients.get(i).getArrival() + "," + clients.get(i).getService() + ")";
//                }
//            }

            string += "\n";
            for (int i = 0; i < numberOfServers; i++)
            {
                string += "Queue: " + (i + 1) + " ";
                Server server = scheduler.getServers().get(i);
                int size = server.getQueueSize();


                if (size == 0)
                {
                    string += "closed";
                }
                else
                {
                    for (Client v : server.getClients())
                    {
                        string += "(" + v.getId() + "," + v.getArrival() + "," + v.getService() + ") ";
                    }
                }
                string += "\n";
            }

            scrieFile(string);

            try {
                this.sleep(10);
            }catch (InterruptedException e) {
                System.out.println("ERROR SLEEPING 1 SECOND IN SIMULATION MANAGER");
                throw new RuntimeException(e);
            }

            currentTime++;
            //System.out.println(currentTime);
        }

        ///inchid fisier
        try {
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }


        scheduler.stop();
        interrupt();
    }

}
