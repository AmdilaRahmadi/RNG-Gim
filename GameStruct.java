package DecisionGame;

class GameStruct {
    GameObject Start = null, Point = null, Left = null, Right = null;
    
    public void Sign(String n, int v){
        GameObject temp = new GameObject(n, v);
        this.Start = temp;
    }

    public void addOp(String s, char l, char r, int vl, int vr){   
        Right = new GameObject(r, vr);
        Left = new GameObject(l, vl);
        GameObject Start = getNode(s);
        Start.leftNode = Left;
        Start.rightNode = Right;
    }

    public GameObject getNode(String name){
        findNode(this.Start, name);
        GameObject hasil = this.Point;
        this.Point = null;
        return hasil;
    }

    public void findNode(GameObject setar, String name){
        if(setar == null){
            return;
        }
        if(setar.name.equalsIgnoreCase(name)){
            this.Point = setar; return;
        }
        findNode(setar.leftNode, name);
        findNode(setar.rightNode, name);
    }

}
