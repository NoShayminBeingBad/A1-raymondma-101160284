package org.example;

import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.Semaphore;

public class ServerOutput {

    public boolean locked;
    public String buffer;
    public Semaphore semaphore;

    public ServerOutput(){
        this.locked = true;
        this.buffer = "";
        this.semaphore = new Semaphore(1);
    }

    public String awaitOutput(){
        System.out.println("Waiting for Output...");
        while (true){
            if (!locked && semaphore.tryAcquire()){
                locked = true;
                String r = buffer;
                buffer = "";
                semaphore.release();
                return r;
            }
        }
    }

    public void println(String out){
        print(out + "\n");
    }

    public void print(String out){
        this.buffer += out;
    }

    public void flush(){
        return;
    }

}
