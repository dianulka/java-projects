import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test
    public void testMatrix() {
        int rows = 4;
        int cols = 5;
        Matrix matrix = new Matrix(rows, cols);

        assertEquals(rows, matrix.rows);
        assertEquals(cols, matrix.cols);

        assertEquals(rows * cols, matrix.data.length);

        for (double element : matrix.data) {
            assertEquals(0.0, element, 0.0001);
        }
    }

    @org.junit.Test
    public void testMatrix2dArray() {
        double[][] foo = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0},
                {6.0, 7.0, 8.0, 9.0}
        };
        Matrix matrix = new Matrix(foo);

        assertEquals(foo.length, matrix.rows); // foo.length=3

        int maxColumn = 0;
        for (int i = 0; i < foo.length; i++) {
            if (foo[i].length > maxColumn) {
                maxColumn = foo[i].length;
            }
        }
        assertEquals(maxColumn, matrix.cols);

        assertEquals(matrix.rows * matrix.cols, matrix.data.length);

        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                if (j < foo[i].length) {
                    assertEquals(foo[i][j], matrix.data[i * matrix.cols + j], 0.0001);
                } else {
                    assertEquals(0.0, matrix.data[i * matrix.cols + j], 0.0001);
                }
            }
        }
    }

    @org.junit.Test
    public void asArray() {
        double[][] foo = {
                {1.5, 3.0, 3.0},
                {4.0, 5.0, 7.3},
                {3.56, 4.0, 10.0}
        };
        Matrix matrix = new Matrix(foo);
        double[][] resultArray = matrix.asArray();

        assertArrayEquals(foo, resultArray);
    }


    @org.junit.Test
    public void get() {
        double[][] foo = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };
        Matrix matrix = new Matrix(foo);

        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                assertEquals(foo[i][j], matrix.get(i, j), 0.0001);
            }
        }
    }


    @org.junit.Test
    public void set() {
        {
            double[][] foo = {
                    {1.0, 2.0, 3.0},
                    {4.0, 5.0, 6.0},
                    {7.0, 8.0, 9.0}
            };
            Matrix matrix = new Matrix(foo);

            for (int i = 0; i < matrix.rows; i++) {
                for (int j = 0; j < matrix.cols; j++) {
                    var newValue = i * matrix.cols + j + 9.99;
                    matrix.set(i, j, newValue);
                    assertEquals(newValue, matrix.get(i, j), 0.0001);
                }
            }
        }
    }

    @org.junit.Test
    public void testToString() {
        double[][] inputArray = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        Matrix matrix = new Matrix(inputArray);

        String resultString = matrix.toString();
        //nawiasy policzyc iprzecinki

        int comma = resultString.length() - resultString.replace(",", "").length();
        int bracketOpen = resultString.length() - resultString.replace("[", "").length();
        int bracketClosed = resultString.length() - resultString.replace("]", "").length();


        assertEquals(matrix.rows*matrix.cols, comma);
        assertEquals(1+matrix.rows, bracketOpen);
        assertEquals(1+matrix.rows, bracketClosed);



    }

    @org.junit.Test
    public void reshape() {
        double[][] foo = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0}
        };
        Matrix matrix = new Matrix(foo);

        int newRows = 3;
        int newCols = 2;

        matrix.reshape(newRows, newCols);

        assertEquals(newRows, matrix.rows);
        assertEquals(newCols, matrix.cols);

        Matrix matrix2 = new Matrix(new double[][]{
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 7.0}
        });

        try {
            matrix.reshape(5, 6);
            fail("exception");
        } catch (RuntimeException e) {
        }


    }


    @org.junit.Test
    public void shape() {
        double[][] foo = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };
        Matrix matrix = new Matrix(foo);

        int[] expectedShape = {matrix.rows, matrix.cols};

        assertArrayEquals(expectedShape, matrix.shape());
    }


    @org.junit.Test
    public void add() {
        double[][] foo1 = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        double[][] foo2 = {
                {2.0, 4.0, 6.0},
                {1.0, 3.0, 5.0},
                {-1.0, 1.0, 2.0}
        };

        Matrix matrix1 = new Matrix(foo1);
        Matrix matrix2 = new Matrix(foo2);
        assertTrue(matrix1.rows == matrix2.rows && matrix1.cols == matrix2.cols);

        Matrix resultMatrix = matrix1.add(matrix2);

        double[][] expectedArray = {
                {3.0, 6.0, 9.0},
                {5.0, 8.0, 11.0},
                {6.0, 9.0, 11.0}
        };

        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix1.cols; j++) {
                assertEquals(expectedArray[i][j], resultMatrix.get(i, j), 0.0001);
            }
        }
    }

    @org.junit.Test
    public void sub() {
        double[][] foo1 = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        double[][] foo2 = {
                {2.0, 4.0, 6.0},
                {1.0, 3.0, 5.0},
                {0.0, 1.0, 2.0}
        };

        Matrix matrix1 = new Matrix(foo1);
        Matrix matrix2 = new Matrix(foo2);
        assertTrue(matrix1.rows == matrix2.rows && matrix1.cols == matrix2.cols);

        Matrix resultMatrix = matrix1.sub(matrix2);

        double[][] expectedArray = {
                {-1.0, -2.0, -3.0},
                {3.0, 2.0, 1.0},
                {7.0, 7.0, 7.0}
        };

        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix1.cols; j++) {
                assertEquals(expectedArray[i][j], resultMatrix.get(i, j), 0.0001);
            }
        }
    }

    @org.junit.Test
    public void mul() {
        double[][] foo1 = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        double[][] foo2 = {
                {2.0, 4.0, 6.0},
                {1.0, 3.0, 5.0},
                {0.0, 1.0, 2.0}
        };

        Matrix matrix1 = new Matrix(foo1);
        Matrix matrix2 = new Matrix(foo2);
        assertTrue(matrix1.rows == matrix2.rows && matrix1.cols == matrix2.cols);


        Matrix resultMatrix = matrix1.mul(matrix2);

        double[][] expectedArray = {
                {2.0, 8.0, 18.0},
                {4.0, 15.0, 30.0},
                {0.0, 8.0, 18.0}
        };

        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix1.cols; j++) {
                assertEquals(expectedArray[i][j], resultMatrix.get(i, j), 0.0001);
            }
        }

    }

    @org.junit.Test
    public void div() {
        double[][] foo1 = {
                {2.0, 4.0, 6.0},
                {8.0, 10.0, 12.0},
                {14.0, 16.0, 18.0}
        };

        double[][] foo2 = {
                {1.0, 2.0, 3.0},
                {2.0, 5.0, 6.0},
                {3.0, 4.0, 2.0}
        };

        Matrix matrix1 = new Matrix(foo1);
        Matrix matrix2 = new Matrix(foo2);

        Matrix resultMatrix = matrix1.div(matrix2);

        double[][] expectedArray = {
                {2.0, 2.0, 2.0},
                {4.0, 2.0, 2.0},
                {4.6667, 4.0, 9.0}
        };

        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix1.cols; j++) {
                assertEquals(expectedArray[i][j], resultMatrix.get(i, j), 0.0001);
            }
        }

        double[][] zeroArray = {
                {1.0, 2.0},
                {0.0, 0.0}
        };

        Matrix matrix3 = new Matrix(zeroArray);

        try {
            matrix1.div(matrix3);
            fail("exception");
        } catch (RuntimeException e) {

        }
    }


    @org.junit.Test
    public void dot() {
        double[][] foo1 = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        double[][] foo2 = {
                {9.0, 8.0, 7.0},
                {6.0, 5.0, 4.0},
                {3.0, 2.0, 1.0}
        };

        Matrix matrix1 = new Matrix(foo1);
        Matrix matrix2 = new Matrix(foo2);

        Matrix resultMatrix = matrix1.dot(matrix2);

        double[][] expectedArray = {
                {30.0, 24.0, 18.0},
                {84.0, 69.0, 54.0},
                {138.0, 114.0, 90.0}
        };

        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix2.cols; j++) {
                assertEquals(expectedArray[i][j], resultMatrix.get(i, j), 0.0001);
            }
        }

        double[][] arr = {
                {1.0, 2.0},
                {3.0, 4.0},

        };

        Matrix incompatible = new Matrix(arr);

        try {
            matrix1.dot(incompatible);
            fail("exception");
        } catch (RuntimeException e) {


        }

    }


    @org.junit.Test
    public void frobenius() {
        double[][] inputArray = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        Matrix matrix = new Matrix(inputArray);

        double expectedNorm = Math.sqrt(1.0 + 4.0 + 9.0 + 16.0 + 25.0 + 36.0 + 49.0 + 64.0 + 81.0);

        assertEquals(expectedNorm, matrix.frobenius(), 0.0001);
    }


    @org.junit.Test
    public void random() {

        int rows = 3;
        int cols = 4;
        Matrix randomMatrix = Matrix.random(rows, cols);

        assertEquals(rows, randomMatrix.rows);
        assertEquals(cols, randomMatrix.cols);

        for (int i = 0; i < randomMatrix.data.length; i++) {
            double value = randomMatrix.data[i];
            assertTrue(value >= 0.0 && value <= 1.0);
        }
    }


    @org.junit.Test
    public void eye() {
        int n = 4;
        Matrix identityMatrix = Matrix.eye(n);

        assertEquals(n, identityMatrix.rows);
        assertEquals(n, identityMatrix.cols);
        assertEquals (Math.sqrt(n),identityMatrix.frobenius(),0.001);

    }

    @org.junit.Test
    public void testAdd() {
        double[][] foo1 = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        Matrix matrix = new Matrix(foo1);
        double w = 10.0;

        matrix.add(w);

        double[][] expectedArray = {
                {11.0, 12.0, 13.0},
                {14.0, 15.0, 16.0},
                {17.0, 18.0, 19.0}
        };

        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                assertEquals(expectedArray[i][j], matrix.get(i, j), 0.0001);
            }
        }
    }


    @org.junit.Test
    public void testSub() {
        {
            double[][] foo1 = {
                    {1.0, 2.0, 3.0},
                    {4.0, 5.0, 6.0},
                    {7.0, 8.0, 9.0}
            };

            double[][] foo2 = {
                    {2.0, 4.0, 6.0},
                    {1.0, 3.0, 5.0},
                    {0.0, 1.0, 2.0}
            };

            Matrix matrix1 = new Matrix(foo1);
            Matrix matrix2 = new Matrix(foo2);

            Matrix resultMatrix = matrix1.sub(matrix2);

            double[][] expectedArray = {
                    {-1.0, -2.0, -3.0},
                    {3.0, 2.0, 1.0},
                    {7.0, 7.0, 7.0}
            };

            for (int i = 0; i < matrix1.rows; i++) {
                for (int j = 0; j < matrix1.cols; j++) {
                    assertEquals(expectedArray[i][j], resultMatrix.get(i, j), 0.0001);
                }
            }
        }
    }

    @org.junit.Test
    public void testMul() {
        double[][] foo = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        Matrix matrix = new Matrix(foo);
        double w = 2.0;

        matrix.mul(w);

        double[][] expectedArray = {
                {2.0, 4.0, 6.0},
                {8.0, 10.0, 12.0},
                {14.0, 16.0, 18.0}
        };

        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                assertEquals(expectedArray[i][j], matrix.get(i, j), 0.0001);
            }
        }
    }


    @org.junit.Test
    public void testDiv() {
        double[][] foo = {
                {2.0, 4.0, 6.0},
                {8.0, 10.0, 12.0},
                {14.0, 16.0, 18.0}
        };

        Matrix matrix = new Matrix(foo);
        double w = 2.0;

        matrix.div(w);

        double[][] expectedArray = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };

        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                assertEquals(expectedArray[i][j], matrix.get(i, j), 0.0001);
            }
        }

        double zero = 0.0;

        try {
            matrix.div(zero);
            fail("exception");
        } catch (RuntimeException e) {
        }
    }
}



//@org.junit.Test
//  public void det() {
// }

//  @org.junit.Test
// public void main() {
//}
