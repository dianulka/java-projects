import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod() {
    }

    Prod(Node n1) {
        args.add(n1);
    }

    Prod(double c) {
        //wywołaj konstruktor jednoargumentowy przekazując new Constant(c)
        this(new Constant(c));
    }

    Prod(Node n1, Node n2) {
        args.add(n1);
        args.add(n2);
    }

    Prod(double c, Node n) {
        //wywołaj konstruktor dwuargumentowy

        this(new Constant(c), n);
    }


    Prod mul(Node n) {
        args.add(n);
        return this;
    }

    Prod mul(double c) {
        Constant x = new Constant(c);
        args.add(x);
        return this;
    }


    @Override
    double evaluate() {
        double result = 1;
        // oblicz iloczyn czynników wołąjąc ich metodę evaluate
        for (Node arg : args) {
            result *= arg.evaluate();
        }
        return sign * result;
    }

    int getArgumentsCount() {
        return args.size();
    }


    public String toString() {
        StringBuilder b = new StringBuilder();
        if (sign < 0) b.append("-");

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
                b.append("*");
            }
            number++;
        }


        return b.toString();
    }


    Node diff(Variable var) {
        Sum r = new Sum();
        for (int i = 0; i < args.size(); i++) {
            Prod m = new Prod();
            for (int j = 0; j < args.size(); j++) {
                Node f = args.get(j);
                if (j == i) m.mul(f.diff(var));
                else m.mul(f);
            }
            r.add(m);
        }
        return r;
    }

    @Override
    boolean isZero() {
        for (Node arg : args) {
            if (arg.isZero()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Node simplify() {

        if (args.isEmpty()){
            return new Constant(0);
        }

        List<Node> argsSimplify = new ArrayList<>();
        double product = 1;

        Iterator<Node> iterator = args.iterator();

        while (iterator.hasNext()) {
            Node arg = iterator.next();
            Node argSimplify = arg.simplify();

            if (argSimplify instanceof Constant) {
                double value = argSimplify.evaluate();

                if (value == 0) {
                    return new Constant(0);
                }
                else if (value != 1) {
                    product *= value;
                    iterator.remove();
                }
            } else {
                argsSimplify.add(argSimplify);
            }
        }

        if (product != 1) {
            argsSimplify.add(new Constant(product));
        }


        if (argsSimplify.isEmpty()) {
            return new Constant(0);
        } else if (argsSimplify.size() == 1) {
            return argsSimplify.get(0);
        } else {
            Prod productSimplify = new Prod();
            for (Node arg : argsSimplify) {
                productSimplify.mul(arg);
            }
            return productSimplify;
        }
    }
}



