package org.example;

import java.util.concurrent.Semaphore;

public class ServerInput {

    public boolean locked;
    private String input;
    private ServerOutput so;
    public Semaphore semaphore;

    public ServerInput(ServerOutput so){
        this.input = "";
        this.so = so;
        this.semaphore = new Semaphore(1);
        this.locked = true;
    }

    public String nextLine(){
        System.out.println("Waiting for Input...");
        so.semaphore.release();
        so.locked = false;
        while (true){
            if (!locked && semaphore.tryAcquire()){
                locked = true;
                String r = input;
                input = "";
                semaphore.release();
                return r;
            }
            //System.out.println("send help");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int nextInt(){
        return Integer.parseInt(nextLine());
    }

    public void setInput(String in){
        while (true){
            if (semaphore.tryAcquire()){
                this.input = in;
                System.out.println("Input Received");
                locked = false;
                semaphore.release();
                return;
            }
        }
    }

    public String getInput(){
        while (true){
            if (semaphore.tryAcquire()){
                String in = this.input;
                semaphore.release();
                return in;
            }
        }
    }

}
