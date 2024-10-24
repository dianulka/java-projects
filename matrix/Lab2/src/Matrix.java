import java.util.Random;

import static java.lang.Math.abs;

public class Matrix {
    double[]data;
    int rows;
    int cols;
    //...
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }
    //...
    Matrix(double[][] d){
        int maxColumn=0;
        for (int i =0; i<d.length; i++) {
            if (d[i].length > maxColumn) {
                maxColumn = d[i].length;
            }
        }

        //int length=d.length;
        this.rows=d.length;
        this.cols=maxColumn;
        this.data=new double[rows*cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j<d[i].length)
                    data[i * cols + j] = d[i][j];
            }
        }
    }
   double[][] asArray() {

       double[][] arr = new double[rows][cols];
       for (int i = 0; i < rows; i++) {
           for (int j = 0; j < cols; j++) {
               arr[i][j] = data[i * cols + j];
   //            System.out.println(arr[i][j]);
           }
       }
       return arr;
   }

   double get(int r,int c){
        return data[r*cols+c];
   }

    void set (int r,int c, double value){
        data[r * cols + c] = value;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < rows; i++) {
            buf.append("[");
            for (int j = 0; j < cols; j++) {
                buf.append(data[i * cols + j]+", ");
            }
            buf.append("]");
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));

        rows = newRows;
        cols = newCols;
    }

    int[] shape(){
        int[] x= new int [2];
        x[0]=rows;
        x[1]=cols;

        return x;

    }
    Matrix add(Matrix m) {
        Matrix result= new Matrix(rows,cols);

        if(rows!=m.rows && cols!=m.cols)
            throw new RuntimeException(String.format("%d x %d and %d x %d can't be added",rows,cols,m.rows,m.cols));


        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++) {
                result.set(i,j,this.get(i,j)+m.get(i,j));
            }
        }
        return result;
    }

    Matrix sub(Matrix m){
        Matrix result= new Matrix(rows,cols);
        if(rows!=m.rows && cols!=m.cols)
            throw new RuntimeException(String.format("%d x %d and %d x %d can't be subtracted",rows,cols,m.rows,m.cols));

        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++) {
                result.set(i,j,this.get(i,j)-m.get(i,j));
            }
        }
        return result;
    }


    Matrix mul(Matrix m){
        if(rows!=m.rows && cols!=m.cols)
            throw new RuntimeException(String.format("%d x %d and %d x %d can't be multiplied",rows,cols,m.rows,m.cols));

        Matrix result= new Matrix(rows,cols);

        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++) {
                result.set(i,j,this.get(i,j)*m.get(i,j));
            }
        }
        return result;

    }

    Matrix div(Matrix m){
        if(rows!=m.rows && cols!=m.cols)
            throw new RuntimeException(String.format("%d x %d and %d x %d can't be divided",rows,cols,m.rows,m.cols));

        Matrix result= new Matrix(rows,cols);
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++) {
                if (m.get(i,j) !=0){
                result.set(i,j,this.get(i,j)/m.get(i,j));
            }
                else{
                    throw new RuntimeException(String.format("can't divide by 0"));
                }

                }
        }
        return result;
    }
    Matrix dot(Matrix m){
        if (this.cols != m.rows) {
            throw new RuntimeException(String.format("Wrong dimensions"));
        }
        Matrix result=new Matrix(this.rows,m.cols);

        for (int i=0; i<this.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                double value = 0;
                for (int k=0; k<this.cols; k++){
                    value+=this.get(i,k)*m.get(k,j);
                }
                result.set(i,j,value);

        }

    }
    return result;
   }
    double frobenius(){
        //wystarczy chodzic po tbalicy
        double sum=0;
        for (int i=0; i<data.length; i++){
            sum+= Math.pow(data[i],2);
        }

        sum = Math.sqrt(sum);
        return sum;
    }

    void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print((data[i * cols + j] +" "));
            }
            System.out.println();
        }
    }

    public static Matrix random(int rows, int cols) {
        Matrix m = new Matrix(rows, cols);
        Random r = new Random();

        for (int i = 0; i < m.data.length; i++) {
            m.data[i] = r.nextDouble();
        }
        return m;
    }

    public static Matrix eye(int n){
        Matrix m=new Matrix(n,n);

        for(int i=0; i< m.rows;i++) {
            for (int j=0; j<m.cols;j++){
                if (i==j){
                    m.set(i,j,1);
                }
            }
        }

        return m;
    }

    Matrix add(double w){

        for (int i=0; i<data.length;i++){
            data[i]+=w;
        }
        return this;
    }

    Matrix sub(double w){
        for (int i=0; i<data.length;i++){
            data[i]-=w;
        }
        return this;
    }
    Matrix mul(double w) {
        for (int i = 0; i < data.length; i++) {
            data[i] *= w;
        }
        return this;
    }

    Matrix div(double w){
        if (w==0){
            throw new RuntimeException("You can't divide by 0");
        }
        for (int i = 0; i < data.length; i++) {
            data[i] /= w;
        }
        return this;

    }


      //  @opcjonalne
    //Oblicz wyznacznik sprowadzając wpierw macierz do postaci trójkątnej za pomocą eliminacji Gaussa
    double det() {
        double value;
        double det;
        if(this.rows!=this.cols){
            throw new RuntimeException("Matrix must be square");
        }
        if (this.rows==1){
            return this.get(0,0);
        }

        if (this.rows==2) {
            det = this.get(0,0)* this.get(1,1) - this.get(0,1)*this.get(1,0);
            return det;
        }

        for (int i = 0; i < rows - 1; i++) {
            for (int k = i + 1; k < rows; k++) {
                double t = this.get(k, i) / this.get(i, i);
                for (int j = 0; j < rows; j++) {
                    value = this.get(k, j) - t * this.get(i, j);
                    this.set(k, j, value);
                }

            }

        }
       // this.print();
        det=1;
        for(int i=0; i< this.rows;i++) {
            for (int j=0; j<this.cols;j++){
                if (i==j){
                    det*= this.get(i,j);
                }
            }
        }
        //System.out.println(det);
        return det;


    }

    public static void main(String[] args) {

      //  double[][] foo = new double[][] {
       //         new double[] { 1, 4, 3},
      //          new double[] { 10, 8, 6},
      //          new double[] { 4, 16, 3}
      //  };

        double[][] foo2 = new double[][]{
                new double[] {10,5,16,0},
                new double[] {17,6,0,1},
               new double[] {7,9,10,7},
                new double[] {8,7,17,9}

       };

      //  Matrix matrix= new Matrix(foo2);
       // System.out.println(matrix.det());
        Matrix m=new Matrix(foo2);
      //  matrix.add(5).print();
       // m.print();
        //double [][] arr = new double [100][100];
        //m.reshape(12,2);
       // m.print();

        //int [] wymiary = new int[3];
       // wymiary=m.shape();
        //for (int i=0; i<wymiary.length;i++)
        //    System.out.println(wymiary[i]);
       // arr=m.asArray();
       // String s;
        m.toString();
      System.out.println(m);
        //System.out.println(s);


     //   Matrix x=new Matrix(10,8);
      //  x= random(10,8);
       // x.print();

        //eye(4).print();

       /* System.out.println(foo.length); //2
        System.out.println(foo[0].length); //3
        System.out.println(foo[2].length); //4
        for (int i=0; i< foo.length;i++){
            for (int j=0; j<foo[i].length; j++) {
                System.out.print(foo[i][j]);
            }
            System.out.println();*/
        }
    }





