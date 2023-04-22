package mvc.controller;

import mvc.models.Client;
import mvc.models.Server;
import mvc.view.ViewFrame;

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
    private static final int waitTime = 1000;
    private ViewFrame view;
    private String output;
    private int maxServiceTime = 20;
    private AtomicInteger var;
    private int minServiceTime = 2;
    private int minArrivalTime = 0;
    private int maxArrivalTime = 100;
    private int numberOfServers = 3;
    private int numberOfClients = 100;
    private int maxNrPeopleInQueue = 5;
    private int currentTime;
    private ArrayList<Client> clients;

    private BufferedWriter writer;

    private Scheduler scheduler;
    private Object lock;

    ///aici am lista de clienti
    ///aici generez clientii



    ///in scheduler pun clientii in QUEUE incerc

    ///aici afisez TOTUL in fisier txt



    public SimulationManager(int timeLimit, int minServiceTime, int maxServiceTime,
                             int minArrivalTime, int maxArrivalTime,
                            int numberOfServers, int numberOfClients, int maxNrPeopleInQueue, ViewFrame view)
    {
        this.lock = new Object();
        this.output = new String();
        this.view = view;

        clients = new ArrayList<>();
        currentTime = 0;

        this.maxNrPeopleInQueue = maxNrPeopleInQueue;
        this.timeLimit = timeLimit;

        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;

        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;

        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;

        generateNClients(numberOfClients);///imi creez cei N clienti
        ///afisezNclients

        scheduler = new Scheduler(numberOfServers, maxNrPeopleInQueue, lock);///imi creez Scheduler-ul

        try{
            File file = new File( "simulation.txt");

            writer = new BufferedWriter(new FileWriter(file.getName()) );
        }
        catch(IOException e){
            e.printStackTrace();
        }

        ///imi afisez Clientii generati
        String string = new String();
        string += "Time simulation: " + timeLimit + " ; nr clients: " + numberOfClients;
        string += " ; nr servers: " + numberOfServers + "\n";
        string += "Time min arrival: " + minArrivalTime + " ; time max arrival: " + maxArrivalTime + "\n";
        string += "Time min service: " + minServiceTime + " ; time max service: " + maxServiceTime + "\n";
        string += "Max number of people in a queue: " + maxNrPeopleInQueue + "\n";
        scrieFile(string);

        string = "Clients:\nId, Arrival Time, Service Time\n";
        for(Client v : clients)
        {
            string += v.getId() + " " + v.getArrival() + " " + v.getService() + "\n";
        }
        scrieFile(string);
    }


    public void generateNClients(int N)
    {
        Random rand = new Random();
        int arrivalTime,serviceTime;
        for (int i = 0; i < N; i++)
        {
            arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            serviceTime = rand.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime;

            clients.add(new Client(i + 1, serviceTime, arrivalTime));
        }
    }


    public void scrieFile(String string)///functie de scris in file
    {
        try {
            writer.write(string + "\n");
        } catch (IOException e) {
            System.out.println("ERROARE SCRIERE");
            throw new RuntimeException(e);
        }

        // System.out.println(string);///in caz ca vreau si consola
    }
    public void run()
    {
        while( currentTime <= timeLimit)///cat timp mai am timp de simulat
        {
            ///adaug clientii in queues
            for (int i = 0; i < clients.size(); i++)///am erori daca nu fac asa
            {
                if (currentTime >= clients.get(i).getArrival()) {///daca au aparut in realitate
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
            output = "";
            output += "Time: " + currentTime + "\n";
            scrieFile("Time: " + currentTime);
            String string = new String();

            output += "Waiting clients: ";
            string += "Waiting clients: ";

            ///afisez clientii care au ramas in asteptare
            ///daca au ramas in asteptare
            for (Client c : clients)
            {
                output += "(" + c.getId() + "," + c.getArrival() + "," + c.getService() + ") ";
                string += "(" + c.getId() + "," + c.getArrival() + "," + c.getService() + ") ";
            }

            string += "\n";
            output += "\n";
            ///afisez clientii pe care ii am in queues
            for (int i = 0; i < numberOfServers; i++)
            {
                output +=  "Queue: " + (i + 1) + " ";
                string += "Queue: " + (i + 1) + " ";
                Server server = scheduler.getServers().get(i);
                int size = server.getQueueSize();


                if (size == 0)
                {
                    output += "closed";
                    string += "closed";
                }
                else
                {
                    for (Client v : server.getClients())
                    {
                        output += "(" + v.getId() + "," + v.getArrival() + "," + v.getService() + ") ";
                        string += "(" + v.getId() + "," + v.getArrival() + "," + v.getService() + ") ";
                    }
                }
                output += "\n";
                string += "\n";
            }


            view.setOutputTextArea(output);
            scrieFile(string);

            try {///astept o secunda pentru simulare reala
                this.sleep(waitTime);
            }catch (InterruptedException e) {
                System.out.println("ERROR SLEEPING 1 SECOND IN SIMULATION MANAGER");
                throw new RuntimeException(e);
            }

            ///le zic la thread-uri ca isi pot face treaba
            ///safe mode ca sa fiu sigur ca nu sterg din ce afisez in timp ce afisez
            synchronized(lock)
            {
                lock.notifyAll();
            }
            currentTime++;
        }

        ///inchid fisier
        try {
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }


        scheduler.stop();///imi opresc thread-urile
        interrupt();///opresc simulatorul
    }

}
