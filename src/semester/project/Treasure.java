
package semester.project;


public class Treasure{
    int[] locate = new int[2];
    public Treasure(){
        this.locate[0] = 5;
        this.locate[1] = 5;
    }
    public void reachedTreasure(){
        System.out.println("Treasure found");
    }
    
    public int[] getPosition(){
        return this.locate;
    }
}