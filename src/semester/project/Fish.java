
package semester.project;
import java.util.Arrays;


public abstract class Fish{
    private final int coordinatex;
    private final int coordinatey;
    public Fish(int coordinate1, int coordinate2){
        this.coordinatex = coordinate1;
        this.coordinatey = coordinate2;
    }
    public void swim(){
    }
    
    public boolean seeWarrior(int[] location){
        int[] coordinates = {this.coordinatex, this.coordinatey};
        if (Arrays.equals(location, coordinates)){
            return true;
        }else{
            return false;
        }
    }
}

class Killerfish extends Fish{

    public Killerfish(int coordinate1, int coordinate2) {
        super(coordinate1, coordinate2);
    }
    
    public void eatWarrior(){
        System.out.println("Killerfish ate warrior");
    }
}
class Rubberfish extends Fish{
    public Rubberfish(int coordinate1, int coordinate2) {
        super(coordinate1,coordinate2);
    }
    public void pullFin(){
        System.out.println("Fish pulled fins");
    }
}

class Innocentfish extends Fish{

    public Innocentfish(int coordinate1,int coordinate2) {
        super(coordinate1, coordinate2);
    }
}