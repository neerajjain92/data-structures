char firstNotRepeatingCharacter(String s) {

    for(int i=0; i<s.length(); i++){
        char a = s.charAt(i);
		if(s.indexOf(a) == s.lastIndexOf(a)){
			return s.charAt(i);
		}
	}
    
    
return '_';}

