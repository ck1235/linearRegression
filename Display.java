/**
 * The class  <b>Assignment</b> is used to
 * test our LinearRegression class. 
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class Assignment {


	/** 
     * Random generator 
     */
     private static java.util.Random generator = new java.util.Random();

    /** 
     * In this first method, we are simply using sample points that are
     * on a straight plane. We will use the plane z= x + 2x.
     * In his method, 
     *    1) we create an instance of LinearRegression.
     *    2) we add 2,000 samples from the plane z= x + 2x as follows:
     *         add the sample [(i, 2i), 5i] for 0<=i<=999
     *         add the sample [(2i, i), 4i] for 0<=i<=999
     *  3) we iterate gradient descent 10,000, printing out the
     * current hypothesis and the current cost every 1,000 
     * iterations, using a step alpha of 0.000000003
     */
    private static void setPlane(){

        LinearRegression object;
        object = new LinearRegression(2, 2000);

        double[] x1, x2;
        x1 = new double[2];
        x2 = new double[2];


        for (double i=0.0; i<1000.0; i++){
            x1[0] = i;
            x1[1] = 2*i;
            x2[0] = 2*i;
            x2[1] = i;

            object.addSample(x1, 5*i);
            object.addSample(x2, 4*i);
        }

        for (int i=0; i<11; i++){
            System.out.println("Current hypothesis: " + object.currentHypothesis());
            System.out.println("Current cost: " + object.currentCost());
            object.gradientDescent(0.000000003, 1000); 
        }

    }

     /** 
     * In this second method, we will select a plane at random.
     *    1) we select a line z = ax + by + c, with a, b and c 
     * randomly selected between -100 and +100 
     *    2) we add 5000 samples randomly selected on the plane
     * with x and y both randomly selected between 50 and 4000. 
     * For each sample we add a "noise" 
     * randomly selected between -20 and +20 (that is, for
     * each randomly selected x and y we add the sample 
     *[ (x,y), ax+by+c+noise).
     * where "noise" is randomly selected between -20 and 20
     *  4) we iterate gradient descent (find a number of iterations,
     * and a step alpha that seems to work, regularly printing
     * the target,  the current hypothesis and the current cost)
     */

 private static void randomPlane(){

          LinearRegression plane;
          plane = new LinearRegression(2,5000);

          double a,b,c, noise, x1, x2, z;
          double[] x;
          x = new double[2];

          a = 200*generator.nextDouble() - 100;
          b = 200*generator.nextDouble() - 100;
          c = 200*generator.nextDouble() - 100;

          for (int i=0; i<5000; i++){
               x1 = 3950*generator.nextDouble() + 50;
               x2 = 3950*generator.nextDouble() + 50;
               
               x[0] = x1;
               x[1] = x2;

               noise = 40*generator.nextDouble() - 20;

               z = a*x1 + b*x2 + c + noise;

               plane.addSample(x,z);
          }

          for (int i=0; i<10; i++){
               System.out.println("Current hypothesis: " + plane.currentHypothesis());
               System.out.println("Current cost: " + plane.currentCost());
               plane.gradientDescent(0.00000001, 2000);
        }

  }

	/** 
     * In this third method, we will follow the same approach
     * that the one followed in the method  randomPlane, but
     * this time we will have a variable number of dimensions,
     * specified by the parameter "dimension". We will
     * create 5000 samples of "dimension" dimension, where each
     * dimension will be ranmly selected between  -100 and +100,
     * and a randomly selected noise between -20 and +20 will be
     * added to the result.We will then iterate gradient descent 
     * (find a number of iterations,
     * and a step alpha that seems to work, regularly printing
     * the target,  the current hypothesis and the current cost)
     *
     * @param dimension the number of features
     */
	private static void randomDimension(int dimension){

          int n = dimension;

          LinearRegression object;
          object = new LinearRegression(n, 5000);

          double noise, r;
          r = 0.0;

          double[] x, t;
          x = new double[n];
          t = new double[n];

          noise = 40*generator.nextDouble() - 20;

          for (int i=0; i<n; i++){
               t[i] = 200*generator.nextDouble() - 100;
          }

          for (int i=0; i<n; i++){
               x[i] = 3950*generator.nextDouble() + 50;
          }

          for (int i=0; i<n; i++){
               r = r + t[i]*x[i];
          }

          r = r + noise;
          
          object.addSample(x,r);

          String str;
          str = "";

          for (int i=0; i<50; i++){
              System.out.println("Current hypothesis: " + object.currentHypothesis());
              System.out.println("Current cost: " + object.currentCost());
              for (int j=0; j<n; j++){
                str = str + t[j] + "x_" + (j+1) + " + ";
              }
              System.out.println("Aiming for: " + str.substring(0, str.length()-2));
              System.out.println();
              object.gradientDescent(0.00000001, 2000);
        }

     }


	public static void main(String[] args) {

		System.out.println("randomDimension");
		randomDimension(50);

	}

}