import java.util.*;

public class sol{

static int FirstDuplicate(int[] a) {
    HashSet<Integer> hs = new HashSet<Integer>();
    for(int i:a){
        if(!hs.contains(i))hs.add(i);
        else return i;
    }
    
return -1;
}

public static void main(String args[]){
    int[] a={2, 1, 3, 5, 3, 2};
    System.out.print("First duplicate: "+FirstDuplicate(a));
    
}

}
