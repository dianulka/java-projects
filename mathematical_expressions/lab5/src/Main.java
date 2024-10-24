import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static void insideCircle(double[] array){ //tablica wsplczynnikow

        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(array[0],new Power(x,2))
                .add(array[1],new Power(y,2))
                .add(array[2],x)
                .add(array[3],y)
                .add(array[4]);

        for (int i=0; i<100;){
        double xv = 100*(Math.random()-.5);
        double yv = 100*(Math.random()-.5);
        x.setValue(xv);
        y.setValue(yv);
        double fv = circle.evaluate();
        if(fv < 0) {
            System.out.printf("Punkt numer %d to (%f,%f) \n",++i,xv,yv);
        }
    }}


    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

       /* Variable x2 = new Variable("x2");
        Variable y3 = new Variable("y");
        Node circle2 = new Sum()
                .add(new Power(x2,2))
                .add(new Power(y3,2))
                //.add(8,x)
                //.add(4,y);
                .add(-25);
      //  System.out.println(circle.toString());

        //System.out.println(circle.ewaluacja());

      //  List<Node.Point> punkciki = circle.insideCircle();

     // for (Node.Point punkt:punkciki){
      //    System.out.println("punkt to jest:"+punkt.x+" "+punkt.y);
     // }
/*

            Variable x = new Variable("x");
            Node exp = new Sum()
                    .add(2,new Power(x,3))
                    .add(new Power(x,2))
                    .add(-2,x)
                    .add(7);
            System.out.println(exp.toString());
            Node dx = exp.diff(x);
            System.out.println(dx.toString());
            dx=dx.simplify();
            System.out.println(dx.toString());

            Node exp5= new Sum()
                    .add(new Constant(5))
                    .add(0,x)
                    .add(10,new Prod(0,x))
                    .add(new Sum(new Constant(0),new Constant(1)));


        System.out.println(exp5.simplify().toString());

            Variable y=new Variable("y");
            Variable z=new Variable("z");
            Node exp2= new Sum()
                    .add(new Power(y,2))
                    .add(new Power(z,2))
                    .add(8,y)
                    .add(4,z)
                    .add(16);
        System.out.println("------------------");
        System.out.println(exp2.toString());
        Node dx2=exp2.diff(y);
        System.out.println(dx2);
        dx2=dx2.simplify();
        System.out.println(dx2.toString());

        Node dx3=exp2.diff(z);
        System.out.println(dx3.toString());
        dx3=dx3.simplify();
        System.out.println(dx3.toString());

        //insideCircle();


        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);

        double[] arr = {1.0, 1.0, 8.0, 4.0, 16.0};
        insideCircle(arr);

        Node exp5= new Sum()
                .add(new Constant(5))
                .add(0,x)
                .add(10,new Prod(0,x))
                .add(new Sum(new Constant(0),new Constant(1)))
                .add(10, new Constant(5));


        System.out.println(exp5.simplify().toString()); */
        /*Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(17);
        System.out.println(exp.toString());
        Node dx = exp.diff(x);
        System.out.println(dx.toString());
        dx=dx.simplify();
        System.out.println(dx.toString());*/




    }


}

