import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum() {
    }

    Sum(Node n1, Node n2) {
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n) {
        args.add(n);
        return this;
    }

    Sum add(double c) {
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c, n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {

        double result = 0;
        // oblicz sumę wartości zwróconych przez wywołanie evaluate skłądników sumy

        for (Node arg : args) {
            result += arg.evaluate();
        }

        return sign * result;
    }

    int getArgumentsCount() {
        return args.size();
    }

    public String toString() {
        StringBuilder b = new StringBuilder();


        if (sign < 0) b.append("-(");
        int number = 0;
        for (Node arg : args) {
            int argSign = arg.getSign();
            int cnt = arg.getArgumentsCount();
            boolean useBracket = false;
            if (argSign < 0) useBracket = true;
            String argString = arg.toString();
            if (useBracket) b.append("(");
            b.append(argString);
            if (useBracket) b.append(")");
            if (number < args.size() - 1) {
                b.append("+");
            }
            number++;
        }

        //zaimplementuj
        if (sign < 0) b.append(")");
        return b.toString();
    }

    Node diff(Variable var) {
        Sum r = new Sum();
        for (Node n : args) {
            r.add(n.diff(var));
        }
        return r;
    }

    @Override
    boolean isZero() {
        for (Node arg : args) {
            if (!arg.isZero()) {
                return false;
            }

        }
        return true;

    }
@Override
    public Node simplify() {
    if (args.isEmpty()){
        return new Constant(0);
    }

        List<Node> argsSimplify = new ArrayList<>();
        double sum = 0;
        Iterator<Node> iterator = args.iterator();

        while (iterator.hasNext()) {
            Node arg = iterator.next();
            Node argSimplify = arg.simplify();

            if (argSimplify instanceof Constant) {
                double value = argSimplify.evaluate();
                if (value == 0) {
                    iterator.remove();
                } else {
                    sum += value;
                    iterator.remove();
                }
            }
            else {
                argsSimplify.add(argSimplify);
            }
        }

        if (sum != 0) {
            argsSimplify.add(new Constant(sum));
        }

        if (argsSimplify.isEmpty()) {
            return new Constant(0);
        }
        else if (argsSimplify.size() == 1) {
            return argsSimplify.get(0);
        }
        else {
            Sum sumSimplify = new Sum();
            for (Node arg : argsSimplify) {
                sumSimplify.add(arg);
            }
            return sumSimplify;
        }

    }
}


