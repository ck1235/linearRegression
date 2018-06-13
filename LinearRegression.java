/**
 * The class  <b>LinearRegression</b> implements gradient
 * descent for multiple variables
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class LinearRegression{


	/** 
     * Number of features (usually "n" in literature) 
     */
	private int nbreOfFeatures;

	/** 
     * Number of samples (usually "m" in literature) 
     */
	private int nbreOfSamples;


	/** 
     * the nbreOfFeatures X nbreOfSamples Matrix of samples
     */
	private double[][] samplesMatrix;

	/** 
     * the nbreOfSamples Matrix of samples target values
     */
	private double[] samplesValues;

	/** 
     * the nbreOfFeatures Matrix theta of current hypthesis function
     */
	private double[] theta;


	/** 
     * number of samples received so far
     */
	private int currentNbreOfSamples;

	/** 
     * a place holder for theta during descent calculation
     */
	private double[] tempTheta;


	/** 
     * counts how many iterations have been performed
     */
	private int iteration;


	/** 
     * Object's contructor. The constructor initializes the instance
     * variables. The starting hypthesis is theta[i]=0.0 for all i
     * 
     * @param n the number of features that we will have
     * @param m the number of samples that we will have
	 *
     */
 	public LinearRegression(int n, int m){

		nbreOfFeatures = n+1;

		nbreOfSamples = m;

		samplesMatrix = new double[nbreOfSamples][nbreOfFeatures]; //[m][n+1];

		samplesValues = new double[nbreOfSamples]; //[m];
		
		theta = new double[nbreOfFeatures]; //[n+1];
		
		currentNbreOfSamples = 0;
		
		tempTheta = new double[nbreOfFeatures]; // [n+1];
		
		iteration = 0;

	}

	/** 
     * Add a new sample to samplesMatrix and samplesValues
     * 
     * @param x the vectors of samples
     * @param y the coresponding expected value
     *
	 */

	public void addSample(double[] x, double y){

		double[] tmp;
		tmp = new double[x.length+1];
		tmp[0] = 1.0;

		for (int i=1; i<tmp.length; i++){
			tmp[i] = x[i-1];
		}

		samplesMatrix[currentNbreOfSamples] = tmp;
		samplesValues[currentNbreOfSamples] = y;
		currentNbreOfSamples++;

	}

	/** 
     * Returns the current hypothesis for the value of the input
     * @param x the input vector for which we want the current hypothesis
     * 
	 * @return h(x)
	 */

	private double hypothesis(double[] x){

		double sum = theta[0]*1.0;

		for (int i=1; i<x.length; i++){
			sum = sum + theta[i]*x[i];
		}

		return sum;

	}

	/** 
     * Returns a string representation of hypthesis function
     * 
	 * @return the string "theta0 x_0 + theta1 x_1 + .. thetan x_n"
	 */

	public String currentHypothesis(){

		String str = "";
		String result = "";

		for (int i=0; i<theta.length; i++){
			str = str + theta[i] + "x_" + i + " + ";
		}

		return str.substring(0, str.length()-2);

	}

	/** 
     * Returns the current cost
     * 
	 * @return the current value of the cost function
	 */

	public double currentCost(){

		double sum, value;
		sum = 0.0;

		for (int i=0; i<nbreOfSamples; i++){
			value = hypothesis(samplesMatrix[i]) - samplesValues[i];
			//System.out.println(value);
			sum = sum + (value*value);
			//System.out.println(sum);
		}

		return sum/nbreOfSamples;
	}

	/** 
     * runs several iterations of the gradient descent algorithm
     * 
     * @param alpha the learning rate
     *
     * @param numberOfSteps how many iteration of the algorithm to run
     */

	public void gradientDescent(double alpha, int numberOfSteps) {


		for (int i=0; i<numberOfSteps; i++){
			double[] tmp = new double[nbreOfFeatures];

			for (int j=0; j<nbreOfFeatures; j++){

				for (int k=0; k<samplesValues.length; k++){
					tmp[j] = tmp[j] + (hypothesis(samplesMatrix[k]) - samplesValues[k])*samplesMatrix[k][j];
					//System.out.println(tmp[j]);
				}

				tempTheta[j] = theta[j] - alpha*(2.0/(double)nbreOfSamples)*tmp[j];

			}

			for (int m=0; m<theta.length; m++){
				theta[m] = tempTheta[m];
			}

			iteration++;

		}

		}



	/** 
     * Getter for theta
     *
	 * @return theta
	 */

	public double[] getTheta(){

		return theta;

	}

	/** 
     * Getter for iteration
     *
	 * @return iteration
	 */

	public int getIteration(){

		return iteration;

	}
}