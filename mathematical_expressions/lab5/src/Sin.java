public class Sin extends Node {
    Node arg;

    Sin(){}
    Sin(Node argument){
        this.arg=argument;
    }
@Override
    public double evaluate() {
        return Math.sin(arg.evaluate());
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if (sign < 0) {
            b.append("-(");
            b.append("sin(" + arg.toString() + ")");
            b.append(")");
            return b.toString();
        }
        b.append("sin(" + arg.toString() + ")");
        return b.toString();
    }

    @Override
    boolean isZero() {
        double x=arg.evaluate();
        return (x%(Math.PI))==0;
    }

    @Override
    public Node diff(Variable var) {
        Prod r =  new Prod(new Cos(arg),arg.diff(var));
        return r;
    }


}
