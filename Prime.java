import java.util.Arrays;

public class Prime {
	
	private int[] primeArray;
	private int[] exponentArray;
	
	private void populatePrimeArray(int amount) {
		int count = 0;
		this.primeArray = new int[amount]; 
		int i = 0;
		while(amount > 0){
			
			if(this.istPrimzahl(count)) {
				this.primeArray[i++] = count;
				amount--;
			}
			count++;
		}
	}
	
	public String primzahlenZerlegung(int num) {
		this.exponentArray = new int[this.primeArray.length]; 
	    int tmp = num;
	    int highestPrimeIndex = 0;
	    String result = tmp + ": ";
	    for(int i=0;i<this.primeArray.length;i++){
	    	//do something with the 
	    	while (this.isTeiler(tmp,this.primeArray[i])) {
				 tmp = tmp/this.primeArray[i];
				 System.out.println(tmp);
				 this.exponentArray[i]++;
			}
	    	//break when we hit 1  
	    	if(tmp == 1) { 
	    		highestPrimeIndex = i;
	    		break;
	    	}
	    }
	    
	    for (int x = 0; x <= highestPrimeIndex; x++) {
			if(this.exponentArray[x] > 0) {
				result = result + "e_" + this.primeArray[x] + ":" + this.exponentArray[x];
				if(x != highestPrimeIndex ) {
					result = result + ", ";
				}
			}
		}
	    
	    return result;
	}


	/**
	 * checks if the given number is dividable by 
	 * @return
	 */
	public boolean isTeiler(int dividend, int divisor){
		return (dividend%divisor == 0) ? true: false ;
	}	
	
	
	/**
	 * prints the given amount of prime numbers starting with 1 
	 * @return
	 */
	public void printPrimeAmount(int amount){
		
		int count = 1;
		
		while(amount > 0){
			
			if(this.istPrimzahl(count)) {
				System.out.println(count);
				amount--;
			}
			
			count++;
		}
	}
	
	/**
	 * checks for prime number  
	 * @param prime
	 * @return
	 */
	public boolean istPrimzahl(int prime) {
		//1 is not a prime number and all numbers below 1 
		if(prime <= 1) {
			return false;
		}
		//even numbers above 2 are not prime
		if(((prime != 2) && (prime%2 == 0))) {
			return false;
		}
		//we take the first two primes to make an easier loop
		if(prime == 3 || prime == 2) {
			return true;
		}
		//look if he is divided by an odd number till the number is greater then half the number  
		int counter = 1;
		do{
			counter += 2;
			if(this.isTeiler(prime,counter)) {
				return false;
			}
		}while(prime/2 > counter);
		return true; 
	}
	
	public static void runTests(){
		Prime primer = new Prime();
		primer.populatePrimeArray(1000);
		System.out.println(Arrays.toString(primer.primeArray));
		System.out.println(primer.primeArray.length);
		System.out.println(primer.primzahlenZerlegung(4321));
	}
}