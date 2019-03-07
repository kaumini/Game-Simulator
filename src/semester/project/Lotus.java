
package semester.project;


public class Lotus{
    private int petals;
    private final String name;
    private final int[] locate = new int[2];
    public Lotus(int x,int y, String n){
        this.petals = 100;
        this.locate[0] = x;
        this.locate[1] = y;
        this.name = n;
    }
    public void getPlucked(){
        this.petals=this.petals-1;
        System.out.println(this.name+" got plucked. No of petals = "+petals);
    }
    public int[] getLocation(){
        return locate;
    }
    public int getNoOfPetals(){
        return this.petals;
    }
}
