package DecisionGame;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner key = new Scanner(System.in);
    static GameStruct Game = new GameStruct();
    static GameList Lead = new GameList();
    static int choose, enemy;
    static int value = 5, level = 1, reset = 7, resetcount = 0;
    static String Nama;

    public static void main(String[] args) {
        menu();
    }

    static void menu(){
        System.out.print("=== WELCOME ===\nChoose the menu\n1. Start\n2. Rules\n3. Leaderboard\n4. Quit\nChoice: "); 
        choose = key.nextInt();

        switch(choose){
            case 1 :
            play();
            break;

            case 2 :
            System.out.println  ("\nRules: \n1. At the begining, player has 5 points\n" +
                                "2. Player will face the enemy with a certain number of points it has\n" + 
                                "3. Players must choose one of two options in the form of a buff ( + or * ) or debuff ( - or / )\n" + 
                                "4. Players will face enemies with the selected buff or debuff\n" + 
                                "5. The rest of the player's points will be used to fight enemies at the next level with different buff or debuff options\n" + 
                                "6. If the player's points run out, the player will lose regardless of the number of levels passed\n" + 
                                "7. Player has 7 chances to reset their selection, if the chance becomes 0 players will lose" +
                                "8. Player will succeed if they reaches 21 levels\n" + 
                                "9. The champion will be determined by the number of points earned on the leaderboard\n" + 
                                "10. EXPLODE YOUR BRAIN\n");
            menu();
            break;

            case 3 :
            Lead.Cetak();
            menu();
            break;

            case 4 :
            System.out.println("BYE");
            System.exit(0);
            break;

            default :
            System.out.println("Just choose one option from number 1 to 4\n");
            menu();
        }
    }

    static void regist(){
        System.out.print("Insert Name: ");
        Nama = key.next();
        Game.Sign(Nama, value);
        System.out.println(Nama + " Added to Game");
    }

    static void play(){
        int choose;
        if(Game.Start == null) regist();
        Game.addOp(Nama, opgen(), opgen(), numgen2(), numgen2());
        enemy = numgen1();
        System.out.println("\nPlayer Name: " + Game.Start.name + "\nPoint: " + Game.Start.value + "\nEnemy Point: " + enemy);
        System.out.print("Left: " + Game.Left.ope);
        System.out.print(Game.Left.value);
        System.out.print(" | Right: " + Game.Right.ope);
        System.out.println(Game.Right.value);
        System.out.println("Number of reset: " + reset);
        System.out.print("Your Choice (1 for Left, 0 for Right, another key to restart): ");
        choose = key.nextInt();
        if(choose == 1){
            leftChoice();
        }
        else if(choose == 0){
            rightChoice();
        } 
        else {
            Reset();
        }
    }

    static int numgen1(){
        Random random = new Random();
        int Range = 0;
        while (true){ if(Range != -1) break; }
        Range = random.nextInt(5,34);
        return Range;
    }

    static int numgen2(){
        Random random = new Random();
        int Range = 0;
        while (true){ if(Range != -1) break; }
        Range = random.nextInt(2,8);
        return Range;
    }

    static char opgen(){
        Random random = new Random();
        int Range = 0;
        char[] op = {'-','+','*','+','-','/','*','+','/','*'};
        while (true){ if(Range != -1) break; }
        Range = random.nextInt(10);
        if(Range == 0) return op[Range];
        else if(Range == 1) return op[Range];
        else if(Range == 2) return op[Range];
        else if(Range == 3) return op[Range];
        else if(Range == 4) return op[Range];
        else if(Range == 5) return op[Range];
        else if(Range == 6) return op[Range];
        else if(Range == 7) return op[Range];
        else if(Range == 8) return op[Range];
        else return op[Range];
    }

    static void leftChoice(){
        if(Game.Left.ope == '-'){
            Game.Start.value = Game.Start.value - Game.Left.value;
            Game.Start.value = Game.Start.value - enemy;
            Condition();
        }
        else if(Game.Left.ope == '+'){
            Game.Start.value = Game.Start.value + Game.Left.value;
            Game.Start.value = Game.Start.value - enemy;
            Condition();
        }
        else if(Game.Left.ope == '/'){
            Game.Start.value = Game.Start.value / Game.Left.value;
            Game.Start.value = Game.Start.value - enemy;
            Condition();
        }
        else if(Game.Left.ope == '*'){
            Game.Start.value = Game.Start.value * Game.Left.value;
            Game.Start.value = Game.Start.value - enemy;
            Condition();
        }
    }

    static void rightChoice(){
        if(Game.Right.ope == '-'){
            Game.Start.value = Game.Start.value - Game.Right.value;
            Game.Start.value = Game.Start.value - enemy;
            Condition();
        }
        else if(Game.Right.ope == '+'){
            Game.Start.value = Game.Start.value + Game.Right.value;
            Game.Start.value = Game.Start.value - enemy;
            Condition();
        }
        else if(Game.Right.ope == '/'){
            Game.Start.value = Game.Start.value / Game.Right.value;
            Game.Start.value = Game.Start.value - enemy;
            Condition();
        }
        else if(Game.Right.ope == '*'){
            Game.Start.value = Game.Start.value * Game.Right.value;
            Game.Start.value = Game.Start.value - enemy;
            Condition();
        }
    }

    static void Reset(){
        reset--;
        resetcount++;
            if(reset != 0){
                play();
            } 
            else{
                System.out.println("Reset chance empty\ngame over");
                Game.Start.value = -100;
                isOver();
            }
    }

    static void Condition(){
        if(Game.Start.value > 0 && level <=20){
            level++;
            play();
        }
        else if(Game.Start.value > 0 && level > 20){
            System.out.println("You Reaches the Finish Line");
            isOver();
        }
        else {
            System.out.println("Game Over");
            isOver();
        }
    }

    static void isOver(){    
        Lead.addList(Game.Start.name, Game.Start.value ,level, resetcount);
        level = 1;
        reset = 7;
        resetcount = 0;
        Game.Start = null;
        menu(); 
    }

}
