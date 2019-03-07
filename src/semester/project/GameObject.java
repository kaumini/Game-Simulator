
package semester.project;

import java.util.ArrayList;
import java.util.Random;


public class GameObject{
    void poisitionWarriors(Grid g2, int amount, ArrayList<Warrior> w1){
        String[] names = {"A","B","C","D"};
        boolean placed;
        Random rand2 = new Random();
        System.out.println("No of SuperWarriors = "+ amount);
        for (int i=0;i<amount;i++){
            placed = false;
            while (!placed){
                int[] borders = new int[]{0,10};
                int coordinate1 = rand2.nextInt(11);
                int coordinate2 = borders[new Random().nextInt(borders.length)];
                int[][] positions =new int[][]{{coordinate1,coordinate2},{coordinate2,coordinate1}};
                int[] position = positions[new Random().nextInt(positions.length)];
                if (g2.checkWarGrid(position[0], position[1])){
                    System.out.println("SuperWarrior"+names[i]+" position "+position[0]+" "+position[1]);
                    g2.setWarrior(position[0], position[1]);
                    SuperWarrior swobject = new SuperWarrior("SuperWarrior"+names[i],position[0], position[1] );
                    w1.add(swobject);
                    placed = true;
                }
            }
        }
        for (int j=0;j<4-amount;j++){
            placed = false;
            while (!placed){
                int[] borders = new int[]{0,10};
                int coordinate1 = rand2.nextInt(11);
                int coordinate2 = borders[new Random().nextInt(borders.length)];
                int[][] positions =new int[][]{{coordinate1,coordinate2},{coordinate2,coordinate1}};
                int[] position = positions[new Random().nextInt(positions.length)];
                if (g2.checkWarGrid(position[0], position[1])){
                    System.out.println("NormalWarrior"+names[amount+j]+" position "+position[0]+" "+position[1]);
                    g2.setWarrior(position[0], position[1]);
                    NormalWarrior nwobject = new NormalWarrior("NormalWarrior"+names[amount+j], position[0], position[1] );
                    w1.add(nwobject);
                    placed = true;
                }
            }
        }
    }
    
    public void positionKillerFish(Grid g2, ArrayList<Killerfish> kf){
        for (int f1=0;f1<2;f1++){
            boolean placed = false;
            while (!placed){
                int coordinate1 = new Random().nextInt(11);
                int coordinate2 = new Random().nextInt(11);
                if (g2.checkFishGrid(coordinate1,coordinate2)&&g2.checkWarGrid(coordinate1, coordinate2)){
                    System.out.println("Killerfish"+f1+" position "+coordinate1+ " "+coordinate2);
                    g2.setKillerFish(coordinate1, coordinate2);
                    Killerfish kfobject = new Killerfish(coordinate1, coordinate2);
                    kf.add(kfobject);
                    placed = true;
                }   
            }
        }
    }
    public void positionRubberFish(Grid g2, ArrayList<Rubberfish> rf){
        for (int f1=0;f1<2;f1++){
            boolean placed = false;
            while (!placed){
                int coordinate1 = new Random().nextInt(11);
                int coordinate2 = new Random().nextInt(11);
                if (g2.checkFishGrid(coordinate1,coordinate2)&&g2.checkWarGrid(coordinate1, coordinate2)){
                    System.out.println("Rubberfish"+f1+" position "+coordinate1+ " "+coordinate2);
                    g2.setRubberFish(coordinate1, coordinate2);
                    Rubberfish rubberfishobject = new Rubberfish(coordinate1, coordinate2);
                    rf.add(rubberfishobject);
                    placed = true;
                }   
            }
        }
    }
    public void positionInnocentFish(Grid g2){
        for (int f1=0;f1<2;f1++){
            boolean placed = false;
            while (!placed){
                int coordinate1 = new Random().nextInt(11);
                int coordinate2 = new Random().nextInt(11);
                if (g2.checkFishGrid(coordinate1,coordinate2)&&g2.checkWarGrid(coordinate1, coordinate2)){
                    System.out.println("Innocentfish"+f1+" position "+coordinate1+ " "+coordinate2);
                    g2.setInnocentFish(coordinate1, coordinate2);
                    Innocentfish inf = new Innocentfish(coordinate1, coordinate2);
                    placed = true;
                }   
            }
        }
    }
    public void positionLotus(Grid g2, ArrayList<Lotus> l){
        String[] names = {"LotusA","LotusB","LotusC","LotusD","LotusE"};
        for (int l1=0;l1<5;l1++){
            boolean placed = false;
            while (!placed){
                int coordinate1 = new Random().nextInt(11);
                int coordinate2 = new Random().nextInt(11);
                if (g2.checkFishGrid(coordinate1,coordinate2)&&g2.checkWarGrid(coordinate1, coordinate2)&&!g2.checkLotusGrid(coordinate1, coordinate2)){
                    System.out.println("Lotus"+l1+" position "+coordinate1+ " "+coordinate2);
                    g2.setLotus(coordinate1, coordinate2);
                    Lotus lobject = new Lotus(coordinate1,coordinate2,names[l1]);
                    l.add(lobject);
                    placed = true;
                }
            }
        }
    }
}