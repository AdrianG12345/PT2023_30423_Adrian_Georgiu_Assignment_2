package mvc.models;

public class Task extends Thread{
    private String name;

    public volatile int counter = 0;

    ///la synchronized un singur thread are acces la un moment dat la acea secventa de cod
    //-> poate duce la deadlock

    public Task(String name)
    {
        this.name = name;
    }


    public void run(int x)
    {

        try {
            this.sleep(x * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread " + name + " has finished!");


    }
}
