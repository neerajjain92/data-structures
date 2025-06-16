import java.util.*;

public class sol{

static char FirstNotRepeatingCharacter(String s) {

    for(int i=0; i<s.length(); i++){
        char a = s.charAt(i);
		if(s.indexOf(a) == s.lastIndexOf(a)){
			return s.charAt(i);
		}
	}
    
    
return '_';}


public static void main(String args[]){
    String a = "abacabad";
    System.out.print("First not repeating character: "+FirstNotRepeatingCharacter(a));
    
}

}
