int firstDuplicate(int[] a) {
    HashSet<Integer> hs = new HashSet<Integer>();
    for(int i:a){
        if(!hs.contains(i))hs.add(i);
        else return i;
    }
    
return -1;
}

