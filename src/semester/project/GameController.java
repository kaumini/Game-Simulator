package semester.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameController {
    public void playGame(){
        Grid grid = new Grid();
        
        Random rand1 = new Random();
        int count = rand1.nextInt(4)+1;
        boolean gameover = false;
        boolean isanywarriormoving = true;
        
        ArrayList <Warrior> w = new ArrayList<>();
        ArrayList <Lotus> lotus = new ArrayList<>();
        ArrayList <Rubberfish> rubberfish = new ArrayList<>();
        ArrayList <Killerfish> killerfish = new ArrayList<>();
        
        GameObject positioning = new GameObject();
        Treasure treasure = new Treasure();
        
        System.out.println("positioning objects");
        positioning.poisitionWarriors(grid, count, w);
        positioning.positionKillerFish(grid, killerfish);
        positioning.positionRubberFish(grid, rubberfish);
        positioning.positionInnocentFish(grid);
        positioning.positionLotus(grid, lotus);
        System.out.println();
        
        
        System.out.println("movingwarriors");
        outerloop:
        while(!gameover&&isanywarriormoving){
            isanywarriormoving = false;
            System.out.println("Next Round!!!");
            for(Warrior W : w){
                
                int[] view;
                if (W.getLiveStatus()){
                    
                    if(W.getFins()){
                        isanywarriormoving = true;
                        
                        if (W instanceof SuperWarrior){
                        view = ((Binocular)W).seelotus(grid, lotus);
                        }else{
                            view = ((NormalWarrior) W).randomSelect(grid);
                        }
                        W.swim(view, grid, W.getName());
                        
                        if (Arrays.equals(W.getPosition(), treasure.getPosition())){
                            treasure.reachedTreasure();
                            W.win();
                            break outerloop;
                        }else{
                            for(Lotus L: lotus){
                                if(L.getNoOfPetals()>0){
                                    if ((L.getLocation()[0]==(W.getPosition()[0]))&&(L.getLocation()[1]==(W.getPosition()[1]))){
                                        L.getPlucked();
                                        if(!W.getImmortality()){
                                            W.becomeImmortal();
                                        }
                                    }
                                }
                            }
                            for(Killerfish kf: killerfish){
                                if(kf.seeWarrior(W.getPosition())){
                                    if(!W.getImmortality()){
                                        kf.eatWarrior();
                                        W.die();
                                    }
                                }
                            }
                            for(Rubberfish rf: rubberfish){
                                if(rf.seeWarrior(W.getPosition())){
                                    rf.pullFin();
                                    W.losefin();
                                }
                            }
                        }
                        System.out.println();
                    }else{
                        System.out.println(W.getName()+" stays at  "+W.getPosition()[0]+"    "+W.getPosition()[1]);
                        System.out.println();
                    }
                }    
            }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
            if (isanywarriormoving == false){
                System.out.println("No Warrior Wins");
            }
            
        }
        System.out.println("GameOver");
    }
}
