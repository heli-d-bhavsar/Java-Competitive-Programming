package Algorithms;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;
/*
 * Author    : joney_000[let_me_start][jaswantsinghyadav007@gmail.com]
 * Algorithm : DSU O(log n) + path optimization
 * Platform  : Codeforces
 *
 */

/*    The Main Class                */
 class DSU{
	
	private InputStream inputStream ;
	private OutputStream outputStream ;
	private InputReaderAndProcessor in ;
    private PrintWriter out ;
	/*
		Overhead [Additional Temporary Strorage] but provides memory reusibility for multiple test cases.
		 
	*/
	
	//Critical Size Limit : 10^5 + 4
	private final int BUFFER = 100005;
	private int    tempints[] = new int[BUFFER];
	private long   templongs[] = new long[BUFFER];
	private double tempdoubles[] = new double[BUFFER];
	private char   tempchars[] = new char[BUFFER];
	private final long mod = 1000000000+7;
	private final int  INF  = Integer.MAX_VALUE / 10;
	private final long INF_L  = Long.MAX_VALUE / 10;
	
	public DSU(){}
	public DSU(boolean stdIO)throws FileNotFoundException{
		//stdIO = false;
		if(stdIO){
			inputStream = System.in;
			outputStream = System.out;
		}else{
			inputStream = new FileInputStream("laundro_matt.txt");
			outputStream = new FileOutputStream("output.txt");
		}
		in = new InputReaderAndProcessor(inputStream);
		out = new PrintWriter(outputStream);
		
	}
	
  void run()throws Exception{
	
	//	 int tests = i();
	//	 once();
		 	int n = i(); int m = i();
	//	 for(int t  = 1 ; t<= tests ; t++){
			init(n);
			for(int q = 1 ; q <= m ; q++){
				int type = i(); 
				if(type ==1){
					//join
					int a = i(); int b = i();
					join(a,b);

				}else{
					int u = i();
					out.write("root of "+u+"is :"+root(u)+"\n");

				}
			}	 	
		 	
	//	}//end tests
	}//end run
	void once(){
	
	}
   
  int f[] = new int[200005];
  int h[] = new int[200005];
    	
	void init(int n){
		for(int i = 1 ; i <= n ; i++){
		  f[i] = i;
		  h[i] = 0;
		}	
	}

	int root(int i){
  	if(f[i] != i)
     	f[i] = root(f[i]);
   	return f[i];
	}

 	void join(int x, int y){
    int xroot = root(x);
    int yroot = root(y);
 		if(h[xroot] < h[yroot])
     	f[xroot] = yroot;
    else if (h[xroot] > h[yroot])
     	f[yroot] = xroot;
 		else{
     	f[yroot] = xroot;
     	h[xroot]++;
    }
	}

 	void print_r(Object...o){
    out.write("\n"+Arrays.deepToString(o)+"\n");
    out.flush();
	}
	
	int hash(String s){
		int base = 31;
		int a = 31;//base = number1 multiplier
		int mod = 100005;//range [0..100004]
		long val = 0;
		for(int i =  1 ; i<= s.length() ;i++){
			val += base * s.charAt(i-1);
			base = ( a * base ) % 100005;
		}
		return (int)(val % 100005) ;
	}
	
 	boolean isPrime(long n){
  		if(n==1)return false;
  		if(n<=3)return true;
  		if(n%2==0)return false;
  		for(int i=2 ;i <= Math.sqrt(n); i++){
   			if(n%i==0)return false;
  		}
  		return true;
 	}
 	// sieve
 	int[] sieve(int n){
 	     
  		boolean isPrime[] = new boolean[n+1];
  		int p[] = new int[n+1];
  		int idx = 1;
  		// Put above 3 variables globle p[1..idx-1]
  		
  		
  		Arrays.fill(isPrime,true);
  		isPrime[0]=isPrime[1]=false;
  		for(int i = 2 ; i<= n ; i++){
  			if(isPrime[i]){
  				p[idx++] = i;
  				for(int j  = 2* i ; j<= n ; j+=i ){
  					isPrime[j] = false;		
  				}
  					
  			}
  			
  		}
  		return p;
 	}
 	long gcd(long a , long b){
  		if(b==0)return a;
  		return gcd(b , a%b);
 	}
 	long lcm(long a , long b){
  		if(a==0||b==0)return 0;
  		return (a*b)/gcd(a,b);
 	}
 	long mulmod(long a , long b ,long mod){
  		if(a==0||b==0)return 0;
  		if(b==1)return a;
   		long ans = mulmod(a,b/2,mod);
   		ans = (ans*2)% mod;
   		if(b%2==1)ans = (a + ans)% mod;
   		return ans;
 	}
 	long pow(long a , long b ,long mod){
   		if(b==0)return 1;
  		if(b==1)return a;
   		long ans = pow(a,b/2,mod);
   		ans = (ans * ans);
   		if(ans >= mod )ans %= mod;
   		
   		if(b%2==1)ans = (a * ans);
   		if(ans >= mod )ans %= mod;
   		
   		return ans;
 	}
 	// 20*20   nCr Pascal Table
 	long[][] ncrTable(){
  		long ncr[][] = new long[21][21];
  		for(int i=0 ;i<=20 ;i++){ncr[i][0]=1;ncr[i][i]=1;}
  		for(int j=0;j<=20 ;j++){
   			for(int i=j+1;i<= 20 ;i++){
    				ncr[i][j] = ncr[i-1][j]+ncr[i-1][j-1];
   			}
  		}
  	return ncr;
 	}
	//*******************************I/O******************************//	
	int i()throws Exception{
 		//return Integer.parseInt(br.readLine().trim());
 		return in.nextInt();
	}
	int[] is(int n)throws Exception{
  //int arr[] = new int[n+1];
  		for(int i=1 ; i <= n ;i++)tempints[i] = in.nextInt();  
 		return tempints;
	}
	long l()throws Exception{
 		return in.nextLong();
	}
	long[] ls(int n)throws Exception{
 		for(int i=1 ; i <= n ;i++)templongs[i] = in.nextLong();  
 		return templongs;
	}

	double d()throws Exception{
 		return in.nextDouble();
	}
	double[] ds(int n)throws Exception{
  		for(int i=1 ; i <= n ;i++)tempdoubles[i] = in.nextDouble();  
 		return tempdoubles;
	}
	char c()throws Exception{
 		return in.nextCharacter();
	}
	char[] cs(int n)throws Exception{
  		for(int i=1 ; i <= n ;i++)tempchars[i] = in.nextCharacter();  
 		return tempchars;
	}
	String s()throws Exception{
 		return in.nextLine();
	}
	BigInteger bi()throws Exception{
 		return in.nextBigInteger();
	}
//***********************I/O ENDS ***********************//
//*********************** 0.3%f [precision]***********************//
/* roundoff upto 2 digits 
   double roundOff = Math.round(number1 * 100.0) / 100.0;
                    or
   System.out.printf("%.2f", val);
					
*/
/*
  print upto 2 digits after decimal
  val = ((long)(val * 100.0))/100.0;
  
*/    private void closeResources(){
		 	 out.flush();
    		 out.close();
    		 return;
	}
	public static void main(String[] args) throws java.lang.Exception{
		//let_me_start Shinch Returns 
		
 
    /*  
    	  // Old Reader Writer
    	  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out=new BufferedWriter(new OutputStreamWriter(System.out));
	  BufferedReader br=new BufferedReader(new FileReader("input.txt"));
        BufferedWriter out=new BufferedWriter(new FileWriter("output.txt"));
    */
		DSU driver = new DSU();
    		 long start =  System.currentTimeMillis();
    		 driver.run();
    		 long end =  System.currentTimeMillis();
    		 //out.write(" Total Time : "+(end - start)+"\n");
    		 driver.closeResources();
    		 return ;
    		 
	}	 

}

 class CompairThreeNumbers implements Comparable<Pair>{
     private int id;
	 private long b;
	 private long a;
	 private long c;
 public CompairThreeNumbers(){
  this.id = 1000;
 
  this.a = 0;
  this.b = 0;
  this.c = 0;
 }
 public CompairThreeNumbers(int id , long a,long b , long c ){
  this.id = id;
  this.a = a;
  this.b = b;
  this.c = c;
 }
 public int compareTo(Pair p){
	if(this.a < p.a)return -1;
	else if(this.a > p.a )return 1;
	else {
		if(this.b < p.b)return -1;
		else if(this.b > p.b )return 1;
		else return 0;
	
	}
 }
 public String toString(){
  return "number1="+this.a+" number2="+this.b;
 }
 
} 
