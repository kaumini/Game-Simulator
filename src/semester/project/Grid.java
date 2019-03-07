
package semester.project;

import java.util.ArrayList;


public class Grid{
    private int warcoordinate[][] = new int[11][11];        
    private int fishcoordinate[][] = new int[11][11];   
    private int lotuscoordinate[][] = new int[11][11];
    private ArrayList<int[]> kfishcoordinate = new ArrayList<>();
    private ArrayList<int[]> rfishcoordinate = new ArrayList<>();
    public Grid(){
    }
    public void setWarrior(int x, int y){
        this.warcoordinate[x][y] = 1;
    }
    
    public void setKillerFish(int x, int y){
        int[] position = {x,y};
        this.fishcoordinate[x][y] = 1;
        this.kfishcoordinate.add(position);
    }
    
    public void setRubberFish(int x, int y){
        int[] position = {x,y};
        this.fishcoordinate[x][y] = 1;
        this.rfishcoordinate.add(position);
    }
    public void setInnocentFish(int x, int y){
        this.fishcoordinate[x][y] = 1;
    }
    
    public void setLotus(int x, int y){
        this.lotuscoordinate[x][y] = 1;
    }
    
    public void removeWarrior(int[] coordinate){
        this.warcoordinate[coordinate[0]][coordinate[1]]=0;
    }
    
    public boolean checkWarGrid(int x,int y){
        if (warcoordinate[x][y] != 1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean checkFishGrid(int x, int y){
        if (fishcoordinate[x][y] != 1){
            return true;
        }else{
            return false;
        }
    }
    
    
    public boolean checkLotusGrid(int x, int y){
        if (lotuscoordinate[x][y] != 1){
            return false;
        }else{
            return true;
        }
    }
    
    public int getWarCoordinate(int x, int y){
        return this.warcoordinate[x][y];
    }
    public int getLotusCoordinate(int x, int y){
        return this.lotuscoordinate[x][y];
    }
    public int getFishCoordinate(int x, int y){
        return this.fishcoordinate[x][y];
    }
    public void moveObject(int p, int q, int r, int s){
        this.warcoordinate[p][q] = 0;
        this.warcoordinate[r][s] = 1;
    }
    
}
