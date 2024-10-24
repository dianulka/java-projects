public class Wyklad {
    public static void printTriangle(int n){
        n=4;
        for (int i=1; i<=n; i++){
            for (int j=0; j<i; i++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
