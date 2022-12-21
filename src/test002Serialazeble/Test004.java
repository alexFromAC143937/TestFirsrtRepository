package test002Serialazeble;

import java.io.*;

public class Test004 {
    public static void main(String[] args) {
        System.out.println("main start");
        Daemon daemon = new Daemon();
        daemon.start();

        System.out.println("main end");
    }
}

class Daemon extends Thread {

    @Override
    public void run() {
        System.out.println("Daemon0 starts");
        Daemon1 d = new Daemon1();
        d.setDaemon(true);   //// TODO If you need Daemon
        d.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Daemon0 ends");
    }

    class Daemon1 extends Thread {

        @Override
        public void run() {
            System.out.println("Daemon1 - starts");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try (FileOutputStream fo = new FileOutputStream(new File("d:\\12345678.txt"))) {
                fo.write(1);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon1 - ends");
        }
    }
}