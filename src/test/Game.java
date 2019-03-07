
package test;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {
    public static void main(String[] args) {
        GameController gamecontroller = new GameController();
        gamecontroller.playGame();
    }
}

/*--------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------*/

class GameController {
    public void playGame(){
        Grid grid = new Grid();
        
        Random rand1 = new Random();
        int count = rand1.nextInt(4)+1;
        
        ArrayList <Inhabitants> inhabitants = new ArrayList<>();
        ArrayList <Lotus> lotus = new ArrayList<>();
        
        Treasure treasure = new Treasure();
        grid.addObserver(5, 5, treasure);
        
        
        boolean placed;
        System.out.println("---------------positioning warriors---------------");
        String[] names = {"A","B","C","D"};
        Random rand2 = new Random();
        System.out.println("No of SuperWarriors = "+ count);
        for (int i=0;i<count;i++){
            placed = false;
            while (!placed){
                int[] borders = new int[]{0,10};
                int coordinate1 = rand2.nextInt(11);
                int coordinate2 = borders[new Random().nextInt(borders.length)];
                int[][] positions =new int[][]{{coordinate1,coordinate2},{coordinate2,coordinate1}};
                int[] position = positions[new Random().nextInt(positions.length)];
                if (grid.checkWarGrid(position[0], position[1])){
                    System.out.println("SuperWarrior"+names[i]+" position "+position[0]+" "+position[1]);
                    SuperWarrior swobject = new SuperWarrior("SuperWarrior"+names[i],position[0], position[1], grid , lotus, treasure );
                    inhabitants.add(swobject);
                    grid.setWarrior(swobject, position[0], position[1]);
                    treasure.addobserver(swobject);
                    placed = true;
                }
            }
        }
        for (int j=0;j<4-count;j++){
            placed = false;
            while (!placed){
                int[] borders = new int[]{0,10};
                int coordinate1 = rand2.nextInt(11);
                int coordinate2 = borders[new Random().nextInt(borders.length)];
                int[][] positions =new int[][]{{coordinate1,coordinate2},{coordinate2,coordinate1}};
                int[] position = positions[new Random().nextInt(positions.length)];
                if (grid.checkWarGrid(position[0], position[1])){
                    System.out.println("NormalWarrior"+names[count+j]+" position "+position[0]+" "+position[1]);
                    NormalWarrior nwobject = new NormalWarrior("NormalWarrior"+names[count+j], position[0], position[1], grid, treasure );
                    inhabitants.add(nwobject);
                    grid.setWarrior(nwobject, position[0], position[1]);
                    treasure.addobserver(nwobject);
                    placed = true;
                }
            }
        }
        
        
        System.out.println("---------------positioning fish---------------");
        for (int f1=0;f1<2;f1++){
            placed = false;
            while (!placed){
                int coordinate1 = new Random().nextInt(11);
                int coordinate2 = new Random().nextInt(11);
                if (grid.checkObservers(coordinate1, coordinate2)&&grid.checkWarGrid(coordinate1, coordinate2)){
                    System.out.println("Killerfish"+f1+" position "+coordinate1+ " "+coordinate2);
                    Killerfish kfobject = new Killerfish(coordinate1, coordinate2, grid);
                    inhabitants.add(kfobject);
                    grid.addObserver(coordinate1, coordinate2, kfobject);
                    placed = true;
                }   
            }
        }
        for (int f1=0;f1<2;f1++){
            placed = false;
            while (!placed){
                int coordinate1 = new Random().nextInt(11);
                int coordinate2 = new Random().nextInt(11);
                if (grid.checkObservers(coordinate1, coordinate2)&&grid.checkWarGrid(coordinate1, coordinate2)){
                    System.out.println("Rubberfish"+f1+" position "+coordinate1+ " "+coordinate2);
                    Rubberfish rubberfishobject = new Rubberfish(coordinate1, coordinate2);
                    inhabitants.add(rubberfishobject);
                    grid.addObserver(coordinate1, coordinate2, rubberfishobject);
                    placed = true;
                }   
            }
        }
        for (int f1=0;f1<2;f1++){
            placed = false;
            while (!placed){
                int coordinate1 = new Random().nextInt(11);
                int coordinate2 = new Random().nextInt(11);
                if (grid.checkObservers(coordinate1, coordinate2)&&grid.checkWarGrid(coordinate1, coordinate2)){
                    System.out.println("Innocentfish"+f1+" position "+coordinate1+ " "+coordinate2);
                    Innocentfish inf = new Innocentfish(coordinate1, coordinate2);
                    inhabitants.add(inf);
                    grid.addObserver(coordinate1, coordinate2, inf);
                    placed = true;
                }   
            }
        }
        String[] lotusnames = {"LotusA","LotusB","LotusC","LotusD","LotusE"};
        for (int l1=0;l1<5;l1++){
            placed = false;
            while (!placed){
                int coordinate1 = new Random().nextInt(11);
                int coordinate2 = new Random().nextInt(11);
                if (grid.checkWarGrid(coordinate1, coordinate2)&&grid.checkObservers(coordinate1, coordinate2)){
                    System.out.println("Lotus"+l1+" position "+coordinate1+ " "+coordinate2);
                    Lotus lobject = new Lotus(coordinate1,coordinate2,lotusnames[l1]);
                    inhabitants.add(lobject);
                    lotus.add(lobject);
                    grid.addObserver(coordinate1, coordinate2, lobject);
                    placed = true;
                }
            }
        }
        inhabitants.add(treasure);
        
        
        System.out.println();
        
        System.out.println("---------------movingwarriors---------------");
        Warrior warrior1 = (Warrior) inhabitants.get(0);
        Warrior warrior2 = (Warrior) inhabitants.get(1);
        Warrior warrior3 = (Warrior) inhabitants.get(2);
        Warrior warrior4 = (Warrior) inhabitants.get(3);
        
        Thread warrior01 = new Thread((Runnable) warrior1);
        Thread warrior02 = new Thread((Runnable) warrior2);
        Thread warrior03 = new Thread((Runnable) warrior3);
        Thread warrior04 = new Thread((Runnable) warrior4);
        
        warrior01.start();
        warrior02.start();
        warrior03.start();
        warrior04.start();
    }
}

/*------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------*/

class Grid {
    private final GridLocation gridlocations[][] = new GridLocation[11][11];
    public Grid(){
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                int[] location= {i,j};
                GridLocation gridlocation = new GridLocation(location);
                gridlocations[i][j] = gridlocation;
            }
        }
    }
    public void setWarrior(Warrior w, int x, int y){
        this.gridlocations[x][y].setWarrior(w);
    }
    
    public void addObserver(int x, int y, observer ob){ 
        this.gridlocations[x][y].setobserver(ob);
    }
    
    public void removeWarrior(Warrior w, int[] coordinate){
        this.gridlocations[coordinate[0]][coordinate[1]].removeWarrior(w);  
    }
    
    public boolean checkWarGrid(int x,int y){
        return this.gridlocations[x][y].isnoWaravailable();
    }
    
    public boolean checkObservers(int x, int y){
        return this.gridlocations[x][y].isnoObservers();
    }
    
    public boolean checkLotus(int x, int y){
        return this.gridlocations[x][y].isLotusavailable();
    }
    
    public void moveObject(int p, int q, int r, int s, Warrior warrior){
        this.gridlocations[p][q].removeWarrior(warrior);
        this.gridlocations[r][s].addWarrior(warrior);
    }
}

/*--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------*/

class GridLocation {
    private final int[] location;
    private final ArrayList<observer> observerlist = new ArrayList<>();
    private final ArrayList<Warrior> warriorrlist = new ArrayList<>();
    public GridLocation(int[] location){
        this.location = location;
    }
    public void setWarrior(Warrior w){
        warriorrlist.add(w);
    }
    
    public void setobserver(observer ob){
        this.observerlist.add(ob);
    }
    
    public void addWarrior(Warrior w){
        synchronized(this){
            warriorrlist.add(w);
            if(!observerlist.isEmpty()){
                notify(w,location);
                if (observerlist.get(0) instanceof Killerfish){
                    this.removeWarrior(w);
                }
            }
        }
    }
    
    public void removeWarrior(Warrior w){
        synchronized(this){
            warriorrlist.remove(w); 
        }
    }
    
    public boolean isnoWaravailable(){
        if (warriorrlist.isEmpty()){
            return true;
        }return false;
    }
    
    public boolean isnoObservers(){
        if (observerlist.isEmpty()){
            return true;
        }return false;
    }
    
    public boolean isLotusavailable(){
        if (!observerlist.isEmpty()){
            if(observerlist.get(0) instanceof  Lotus){
                return true;
            }
        }return false;
    }

    public void notify(Warrior warrior, int[] coordinate1){
        for (observer obs: observerlist) {
            obs.update(warrior, coordinate1);
        }
    }  
}

/*-----------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------*/

interface Inhabitants{
    
}

/*-----------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------*/

abstract class Warrior implements Inhabitants {
    public static int COUNT = 0;
    final private String name;
    private boolean alive;
    private boolean fins;
    protected boolean immortal;
    protected int coordinate1[] = new int[2];
    protected int newcoordinate[] = new int[2];
    protected boolean gameover=false;
    protected static int howmanywarriorsmoving=0;
    public Warrior(String n, int warx, int wary){
        this.name = n;
        this.alive = true;
        this.fins = true;
        this.immortal = false;
        this.coordinate1[0] = warx;
        this.coordinate1[1] = wary;   
        ++howmanywarriorsmoving; 
    }
    
    protected abstract void swim(int[] selection, Grid g2, String n);
    
    protected abstract void eat();
    
    protected abstract void sleep();
    
    public void becomeImmortal(){
        this.immortal=true;
        System.out.println("----> "+this.getName()+" became immortal");
    }
    public void losefin(){
        this.fins = false;
        System.out.println("----> "+this.getName()+" lost fin");
        System.out.println(this.getName()+" stays at  "+this.getNewPosition()[0]+"    "+this.getNewPosition()[1]);
        --howmanywarriorsmoving;
    }
    public void die(Grid grid){
        this.alive = false;
        grid.removeWarrior(this, coordinate1);
        System.out.println("----> "+this.getName()+" died");
        --howmanywarriorsmoving;
    }
    public void win(){
        gameover=true;
        System.out.println("----> "+this.name+" won!!!");
    }
    public void pluckLotus(){
        System.out.println("----> "+this.getName()+" plucked lotus");
    }
    public int[] getPosition(){
        return this.coordinate1;
    }
    public int[] getNewPosition(){
        return this.newcoordinate;
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
    public int[] getLocation(){
        return this.coordinate1;
    }
    public int getCount(){
        return COUNT;
    }     
    public int gethowmanywarriorsmoving(){
        return Warrior.howmanywarriorsmoving;
    }
    public void update(){
        this.gameover = true;
    }
}

/*---------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------*/

class NormalWarrior extends Warrior implements Runnable{
    int k;
    private final Grid grid;
    private final Treasure treasure;
    public NormalWarrior(String n, int warx, int wary, Grid grid,Treasure treasure) {
        super(n, warx, wary);
        this.grid = grid;
        this.treasure = treasure;
        COUNT++;
    }
    
    @Override
    public synchronized void run() {
        
        int[] view;
        outerloop:
        while(this.treasure.getStatus()&&this.getFins()&&this.getLiveStatus()&&!gameover){

            view = ((NormalWarrior) this).randomSelect(grid);
            this.swim(view, grid, this.getName());
            
            try {
                this.wait(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(NormalWarrior.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (howmanywarriorsmoving == 0){
                System.out.println("No Warrior Wins");
                break;
            }
        }      
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
        System.out.println(n+" moved to  "+selection[0]+"    "+selection[1]);
        this.newcoordinate=selection;
        g2.moveObject(this.coordinate1[0], this.coordinate1[1], selection[0], selection[1], this);
        this.coordinate1=selection;
    }
}

/*-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------*/

class SuperWarrior extends Warrior implements Binocular, Runnable{
    private final Grid grid;
    private final ArrayList<Lotus> lotus;
    private final Treasure treasure;
    public SuperWarrior(String n, int warx, int wary, Grid grid, ArrayList<Lotus> lotus, Treasure treasure) {
        super(n,warx, wary);
        this.grid = grid;
        this.lotus = lotus;
        this.treasure = treasure;
        COUNT++;
    }
    
    @Override
    public synchronized void run() {
        int[] view;
        while(this.treasure.getStatus()&&this.getFins()&&this.getLiveStatus()&&!gameover){
             
            view = ((Binocular)this).seelotus(grid, lotus);
            this.swim(view, grid, this.getName());

            try {
                this.wait(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(SuperWarrior.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            if (howmanywarriorsmoving == 0){
                System.out.println("No Warrior Wins");
                break;
            }
        }
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
                if(g2.checkLotus(validcoordinate[0], validcoordinate[1])){
                    System.out.println("----> "+this.getName()+" saw lotus ");
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
        System.out.println(n+" moved to  "+view[0]+"    "+view[1]);
        this.newcoordinate=view;
        g2.moveObject(this.coordinate1[0], this.coordinate1[1], view[0], view[1], this);
        this.coordinate1=view;
    }
}

/*----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------*/

interface Binocular {
    public int[] seelotus(Grid g2,ArrayList<Lotus> l);
}

/*----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------*/

interface observer {
    public void update(Warrior warrior, int[] coordinate1);
}

/*-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------*/

abstract class Fish implements Inhabitants, observer{
    private final int coordinate[] = new int[2];
    public Fish(int coordinate1, int coordinate2){
        this.coordinate[0] = coordinate1;
        this.coordinate[1] = coordinate2;
    }
    public void swim(Grid g2){
        int[] selection;
        int[][] availablecoordinates ={{coordinate[0]+1,coordinate[1]},{coordinate[0],coordinate[1]+1},{coordinate[0]-1,coordinate[1]},{coordinate[0],coordinate[1]-1}};
        do{
            do{
                selection = availablecoordinates[new Random().nextInt(4)];
            }while (selection[0]<0 || selection[0]>10 || selection[1]<0 || selection[1]>10);
        }while(!(g2.checkWarGrid(selection[0], selection[1])));
    }
    
    public boolean seeWarrior(int[] location){
        if (Arrays.equals(location, coordinate)){
            return true;
        }else{
            return false;
        }
    }
    public int[] getLocation(){
        return this.coordinate;
    }
}

/*-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------*/

class Killerfish extends Fish{
    private final Grid grid;
    public Killerfish(int coordinate1, int coordinate2, Grid grid) {
        super(coordinate1, coordinate2);
        this.grid = grid;
    }
    
    
    @Override
    public void update(Warrior warrior, int[] coordinate1) {
        if(this.seeWarrior(coordinate1))
            if(!warrior.getImmortality()){
                this.eatWarrior(warrior,grid);
            }
    }
    
    public void eatWarrior(Warrior w, Grid grid){
        System.out.println("----> Killerfish ate warrior");
        w.die(grid);
    }
}

/*-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------*/

class Rubberfish extends Fish{
    public Rubberfish(int coordinate1, int coordinate2) {
        super(coordinate1,coordinate2);
    }
    
    @Override
    public void update(Warrior warrior, int[] coordinate1) {
        if(this.seeWarrior(coordinate1)){
            this.pullFin(warrior);
        }
    }
    
    public void pullFin(Warrior w){
        System.out.println("----> Fish pulled fins");
        w.losefin();
    }
}

/*----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------*/

class Innocentfish extends Fish{

    public Innocentfish(int coordinate1,int coordinate2) {
        super(coordinate1, coordinate2);
    }    

    @Override
    public void update(Warrior warrior, int[] coordinate1) {
    }
}

/*-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------*/

class Lotus implements Inhabitants, observer{
    private int petals;
    private final String name;
    private final int[] locate = new int[2];
    public Lotus(int x,int y, String n){
        this.petals = 100;
        this.locate[0] = x;
        this.locate[1] = y;
        this.name = n;
    }
    
    @Override
    public void update(Warrior warrior, int[] coordinate1) {
        if(Arrays.equals(coordinate1, this.locate)){
            
            this.getPlucked(warrior);
        }
    }
    
    public void getPlucked(Warrior W){
        this.petals=this.petals-1;
        W.pluckLotus();
        System.out.println("----> "+this.name+" got plucked. No of petals = "+petals);
        if(!W.getImmortality()){
            W.becomeImmortal();
        }
    }
    public int[] getLocation(){
        return locate;
    }
    public int getNoOfPetals(){
        return this.petals;
    }
}

/*---------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------*/

class Treasure implements observer, Inhabitants{
    private final int[] locate = new int[2];
    private boolean treasureavailable;
    private final ArrayList<Warrior> observerlist = new ArrayList<>();
    public Treasure(){
        this.locate[0] = 5;
        this.locate[1] = 5;
        this.treasureavailable = true;
    }
    
    @Override
    public void update(Warrior warrior, int[] coordinate1) {
        if(Arrays.equals(coordinate1, this.locate)){
            this.reachedTreasure(warrior);
        }
    }
    
    public void reachedTreasure(Warrior warrior){
        System.out.println("----> Treasure found");
        this.treasureavailable = false;
        warrior.win();
        this.notifywarriors();
        System.out.println("---------------Game Over!!!---------------");
        
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();
        this.writetofile(warrior.getName(), df.format(date));
    }
    public boolean getStatus(){
        return this.treasureavailable;
    }
    public int[] getPosition(){
        return this.locate;
    }
    public void addobserver(Warrior w){
        this.observerlist.add(w);
    }
    public void notifywarriors(){
        for (Warrior warrior : this.observerlist) {
            warrior.update();
        }
    }
    
    public void writetofile(String winner, String time) {
        try {
            String filelocation = "output.txt";
            
            File file = new File(filelocation);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("Winner : "+winner+"              \n"+"Win time : "+time);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Treasure.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
