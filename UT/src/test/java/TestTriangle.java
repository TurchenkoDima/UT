import org.testng.Assert;
import org.testng.annotations.*;

public class TestTriangle {

    @DataProvider(name = "PositiveData")
    public Object[][] positiveData(){
        return new Object[][]{
                {"Ordynary",12,13,10,4},
                {"Ordynary",10,12,13,4},
                {"Ordynary",13,10,12,4},

                {"Rectangular",Math.sqrt(3*3+3*3),4,5,8},
                {"Rectangular",4,5,Math.sqrt(3*3+3*3),8},
                {"Rectangular",5,Math.sqrt(3*3+3*3),4,8},

                {"Rectangular and Isosceles",Math.sqrt(3*3+3*3),3,3,10},
                {"Rectangular and Isosceles",3,3,Math.sqrt(3*3+3*3),10},
                {"Rectangular and Isosceles",3,Math.sqrt(3*3+3*3),3,10},

                {"Isosceles",10,10,15,2},
                {"Isosceles",15,10,10,2},
                {"Isosceles",10,15,10,2},

                {"Equilateral",10,10,10,1},
                {"Equilateral",1,1,1,1},
                {"Equilateral",13,13,13,1}
        };
    }

    @Test(dataProvider = "PositiveData")
    public void checkTrianglePositiveTest(String test, double a, double b, double c, int exp){
        System.out.println(test+" triangle");
        Triangle triangle = new Triangle(a,b,c);
        Assert.assertTrue(triangle.checkTriangle());
    }

    @Test(dataProvider = "PositiveData")
    public void messagePositiveTest(String test, double a, double b, double c, int exp){
        System.out.println(test+" triangle");
        Triangle triangle = new Triangle(a,b,c);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.detectTriangle(),exp);
    }

    @Test(dataProvider = "PositiveData")
    public void squarePositiveTest(String test, double a, double b, double c, int exp){
        System.out.println(test+" triangle");
        Triangle triangle = new Triangle(a,b,c);
        Assert.assertEquals(triangle.getSquare(),square(a,b,c));
    }

    @DataProvider(name = "NegativeData")
    public Object[][] negativeData(){
        return new Object [][]{
                {"Zero side",0,6,55,"a<=0"},
                {"Zero side",7,0,7,"b<=0"},
                {"Zero side",54,12,0,"c<=0"},
                {"Zero side",0,0,10,"a<=0, b<=0"},
                {"Zero side",0,13,0,"a<=0, c<=0"},
                {"Zero side",92,0,0,"b<=0, c<=0"},
                {"Zero side",0,0,0,"a<=0, b<=0, c<=0"},

                {"Negative side",-2,1,1,"a<=0"},
                {"Negative side",1,-2,1,"b<=0"},
                {"Negative side",1,1,-2,"c<=0"},
                {"Negative side",-2,-2,6,"a<=0, b<=0"},
                {"Negative side",6,-2,-2,"b<=0, c<=0"},
                {"Negative side",-2,6,-2,"a<=0, c<=0"},
                {"Negative side",-2,-2,-2,"a<=0, b<=0, c<=0"},

                {"Zero and Negative side",-2,0,1,"a<=0, b<=0"},
                {"Zero and Negative side",-8,5,0,"a<=0, c<=0"},
                {"Zero and Negative side",0,-3,3,"a<=0, b<=0"},
                {"Zero and Negative side",7,-1,0,"b<=0, c<=0"},
                {"Zero and Negative side",7,0,-5,"b<=0, c<=0"},
                {"Zero and Negative side",0,77,-5,"a<=0, c<=0"},
                {"Zero and Negative side",-6,-77,0,"a<=0, b<=0, c<=0"},
                {"Zero and Negative side",-13,0,-5,"a<=0, b<=0, c<=0"},
                {"Zero and Negative side",0,-5,-5,"a<=0, b<=0, c<=0"},
                {"Zero and Negative side",0,0,-5,"a<=0, b<=0, c<=0"},
                {"Zero and Negative side",0,-8,0,"a<=0, b<=0, c<=0"},
                {"Zero and Negative side",-3,0,0,"a<=0, b<=0, c<=0"},

                {"Sum is equal to the side",1,2,3,"a+b<=c"},
                {"Sum is equal to the side",3,1,2,"b+c<=a"},
                {"Sum is equal to the side",2,3,1,"a+c<=b"},
                {"Sum is equal to the side",4,5,10,"a+b<=c"},
                {"Sum is equal to the side",10,4,5,"b+c<=a"},
                {"Sum is equal to the side",5,10,4,"a+c<=b"}
        };
    }

    @Test(dataProvider = "NegativeData")
    public void checkTriangleNegativeTest(String test, double a, double b, double c, String exp2){
        System.out.println(test+" triangle");
        Triangle triangle = new Triangle(a,b,c);
        Assert.assertFalse(triangle.checkTriangle());

    }

    @Test(dataProvider = "NegativeData")
    public void messageNegativeTest(String test, double a, double b, double c, String exp2){
        System.out.println(test+" triangle");
        Triangle triangle = new Triangle(a,b,c);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),exp2,"Message is wrong.");
    }

    @Test(dataProvider = "NegativeData", expectedExceptions = Exception.class)
    public void negativeSideExceptionTest(String test, double a, double b, double c, String exp2)throws Exception{
        System.out.println(test+" triangle");
        Triangle triangle = new Triangle(a,b,c);
        triangle.detectTriangle();
    }

    @Test(dataProvider = "NegativeData", expectedExceptions = Exception.class)
    public void squareExceptionTest(String test, double a, double b, double c, String exp) throws Exception{
        System.out.println(test+" triangle");
        Triangle triangle = new Triangle(a,b,c);
        triangle.getSquare();
    }

    private double square(double x, double y, double z){
        double p = (x+y+z)/2;
        return Math.sqrt(p*(p-x)*(p-y)*(p-z));
    }
}