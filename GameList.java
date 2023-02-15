package DecisionGame;

class GameList {
    GameObject Head = null, Tail = null, Point = null;

    public void addList(String over, int s1, int s2, int s3){
        GameObject New = new GameObject(over, s1, s2, s3);          
        if(Head == null){                               
            Head = Tail = New;
            Head.prevNode = null;
            Tail.nextNode = null;            
        }                                                   
        else{
            Point = Head;
            while(Point != null){
                if(New.value < Point.value && Point == Head){
                    New.nextNode = Point;
                    Point.prevNode = New;
                    Head = New;
                    return;
                } else if (New.value < Point.value){
                    New.nextNode = Point;
                    New.prevNode = Point.prevNode;
                    New.prevNode.nextNode = New;
                    Point.prevNode = New;
                    return;
                } else if(Point == Tail){
                    Point.nextNode = New;
                    New.prevNode = Point;
                    Tail = New;
                    return;
                } else Point = Point.nextNode;
            }
        } 
    }

    public void Cetak(){
        int num = 1;
        Point = Tail;
        if(Head == null){ System.out.println("\nLeaderboard Still Empty\n"); }
        else{
            System.out.println("Recent Player: ");
            while(Point != null){
                if(Point.prevNode != null){   
                    System.out.println(num + ". Name: " + Point.name + " | Score: " + Point.value + 
                    " | Level: " + Point.level + " | Number of reset: " + Point.numres);
                }
                else{
                    System.out.println(num + ". Name: " + Point.name + " | Score: " + Point.value + 
                    " | Level: " + Point.level + " | Number of reset: " + Point.numres + "\n");
                }
                Point = Point.prevNode;
                num++;
            }
            System.out.println(Tail.name + "(" + Tail.value + ")" + " IS THE CHAMPION\n");
        }
    }

}
