public class Cos extends Node {

    Node arg;

    Cos(){}
    Cos(Node argument){
        this.arg=argument;
    }
    @Override
    public double evaluate() {
        return Math.cos(arg.evaluate());
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if (sign < 0) {
            b.append("-(");
            b.append("cos(" + arg.toString() + ")");
            b.append(")");
            return b.toString();
        }
        b.append("cos(" + arg.toString() + ")");
        return b.toString();
    }

    @Override
    boolean isZero() {
        if (arg.evaluate()==0){
            return false;
        }
        double x=arg.evaluate();
        return (x%(Math.PI/2))==0;
    }

    @Override
    public Node diff(Variable var) {
        Prod r=new Prod((-1),new Sin(arg));
        r.mul(arg.diff(var));
        return r;
    }
}
