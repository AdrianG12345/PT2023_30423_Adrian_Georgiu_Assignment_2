package mvc.models;

public class Client {
    private int arrival;
    private int service;
    private int id;



    public Client(int id, int serviceTime,int arrivalTime)
    {
        this.arrival = arrivalTime;
        this.service = serviceTime;
        this.id = id;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
