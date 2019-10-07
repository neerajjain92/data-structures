import java.util.*;

public class sol{

static int[][] RotateImage(int[][] a) {
    
    int l=a.length,i,j;
    int[][] ans = new int[l][l];
    
    for(i=0;i<l;i++)
    for(j=l-1;j>=0;j--){
        ans[i][l-j-1] = a[j][i];}

return ans;}



public static void main(String args[]){
    int[][] a= {{1,2,3},{4,5,6},{7,8,9}};
    int[][] ans=RotateImage(a);
    for(int i=0;i<a.length;i++){
        for(int j=0;j<a[i].length;j++)System.out.print(ans[i][j]+" ");
        System.out.println();
    }
    
    
}

}
