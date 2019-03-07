
package semester.project;

import java.util.ArrayList;
import java.util.Random;

public abstract class Warrior {
    public static int COUNT = 0;
    final private String name;
    private boolean alive;
    private boolean fins;
    protected boolean immortal;
    protected int coordinate1[] = new int[2];
    
    public Warrior(String n, int warx, int wary){
        this.name = n;
        this.alive = true;
        this.fins = true;
        this.immortal = false;
        this.coordinate1[0] = warx;
        this.coordinate1[1] = wary;
    }
    
    protected abstract void swim(int[] selection, Grid g2, String n);
    
    protected abstract void eat();
    
    protected abstract void sleep();
    
    public void becomeImmortal(){
        this.immortal=true;
        System.out.println("warrior became immortal");
    }
    public void losefin(){
        this.fins = false;
        System.out.println("Warrior lost fin");
    }
    public void die(){
        this.alive = false;
        System.out.println("Warrior died");
    }
    public void win(){
        System.out.println(this.name+" won!!!");
    }
    public void stop(){
        
    }
    public int[] getPosition(){
        return this.coordinate1;
    }
    public boolean getImmortality(){
        return this.immortal;
    }
    public boolean getFins(){
        return this.fins;
    }
    public boolean getLiveStatus(){
        return this.alive;
    }
    public String getName(){
        return this.name;
    }
    
    public int getCount(){
        return COUNT;
    }            
}

class NormalWarrior extends Warrior{
    int k;
    public NormalWarrior(String n, int warx, int wary) {
        super(n, warx, wary);
        COUNT++;
    }
    
    @Override
    public void sleep(){
    }
    
    @Override
    public void eat(){
    }
    public int[] randomSelect(Grid g2){
        int[] selection;
        int[][] availablecoordinates ={{coordinate1[0]+1,coordinate1[1]},{coordinate1[0],coordinate1[1]+1},{coordinate1[0]-1,coordinate1[1]},{coordinate1[0],coordinate1[1]-1}};
        do{
            do{
                selection = availablecoordinates[new Random().nextInt(4)];
            }while (selection[0]<0 || selection[0]>10 || selection[1]<0 || selection[1]>10);
        }while(!(g2.checkWarGrid(selection[0], selection[1])));
        return selection;
    }
    @Override
    public void swim(int[] selection, Grid g2, String n){
        g2.moveObject(this.coordinate1[0], this.coordinate1[1], selection[0], selection[1]);
        this.coordinate1=selection;
        System.out.println(n+" moved to  "+coordinate1[0]+"    "+coordinate1[1]);
        
    }
    
}

class SuperWarrior extends Warrior implements Binocular{
    
    public SuperWarrior(String n, int warx, int wary) {
        super(n,warx, wary);
        COUNT++;
    }
    @Override
    public int[] seelotus(Grid g2, ArrayList<Lotus> l){
        ArrayList <int[]> validcoordinates =new ArrayList<>();
        int[][] availablecoordinates = {{coordinate1[0]+1,coordinate1[1]},{coordinate1[0],coordinate1[1]+1},{coordinate1[0]-1,coordinate1[1]},{coordinate1[0],coordinate1[1]-1}};
        for(int i=0;i<4;i++){
            if((availablecoordinates[i][0]>-1)&&(availablecoordinates[i][0]<11)&&(availablecoordinates[i][1]>-1)&&(availablecoordinates[i][1]<11)){
                if(g2.checkWarGrid(availablecoordinates[i][0], availablecoordinates[i][1])){
                    validcoordinates.add(availablecoordinates[i]);
                }
            }
        }if(!this.immortal){
            for (int[] validcoordinate : validcoordinates) {
                if(g2.checkLotusGrid(validcoordinate[0], validcoordinate[1])){
                    return validcoordinate;
                }
            }return validcoordinates.get(new Random().nextInt(validcoordinates.size()));
        }return validcoordinates.get(new Random().nextInt(validcoordinates.size()));
    }
    
    @Override
    public void sleep(){
    }
    @Override
    public void eat(){
    }
    @Override
    public void swim(int[] view, Grid g2, String n){
        g2.moveObject(this.coordinate1[0], this.coordinate1[1], view[0], view[1]);
        this.coordinate1=view;
        System.out.println(n+" moved to  "+this.coordinate1[0]+"    "+this.coordinate1[1]);
    }
    
    
}