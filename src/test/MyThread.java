
package test;
public class MyThread extends Thread{
    public void run(){
        System.out.println("Running");
    }
    public static void main(String a[]){
        MyThread t = new MyThread();
        t.start();
        t.start();
    }
}