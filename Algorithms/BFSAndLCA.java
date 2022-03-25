package Algorithms;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

/*
 * Author    : joney_000[developer.jaswant@gmail.com]
 * Algorithm : BFS & LCA
 * Platform  : Codeforces
 * Ref       : 
 */

public class BFSAndLCA extends AlgorithmsMathematicalOperationsUtility{
  
  private InputStream inputStream ;
  private OutputStream outputStream ;
  private FastReader in ;
  private PrintWriter out ;
  
  private final int BUFFER = 100005;
  
  private int    auxInts[] = new int[BUFFER];
  private long   auxLongs[] = new long[1];
  private double auxDoubles[] = new double[1];
  private char   auxChars[] = new char[1];
  private final long mod = 1000000000+7;
  private final int  INF  = Integer.MAX_VALUE;
  private final long INF_L  = Long.MAX_VALUE / 10;

  public BFSAndLCA(){}
  public BFSAndLCA(boolean stdIO)throws FileNotFoundException{
    // stdIO = false;
    if(stdIO){
      inputStream = System.in;
      outputStream = System.out;
    }else{
      inputStream = new FileInputStream("input.txt");
      outputStream = new FileOutputStream("output.txt");
    }
    in = new FastReader(inputStream);
    out = new PrintWriter(outputStream);
  }
  

  int n, m;

  void run()throws Exception{
    clear();
    n = i(); m = n - 1;
    for(int i = 1; i <= m; i++){
      int u = i(); int v = i();
      adj[u].add(v);
      adj[v].add(u);
    }
    LinkedList<Integer> adj0[] = getCopy(adj, n);     // wow 
    bfs(adj0, 1, n);    //Assuming that node 1 is the root node
    long ans = 0;
    out.write(""+ans+"\n");
 
  }// end run

  void once(){
    
  }
  
  int MAXN = 200005;
  int depth[]  = new int[MAXN + 1];                  
  int f[]  = new int[MAXN + 1];                  // f[i] = father of i   
  LinkedList<Integer> adj[] = new LinkedList[MAXN + 1];
  boolean vis[] = new boolean[MAXN + 1]; 

  void clear(){
    for(int i = 1; i <= MAXN; i++){
      adj[i] = new LinkedList<Integer>();
    }
  }
  
  // Maintain immutability
  LinkedList<Integer>[] getCopy(LinkedList<Integer>[] adj, int n){
    LinkedList<Integer> adjCopy[] = new LinkedList[n + 1];
    for(int i = 1; i <= n; i++){
      adjCopy[i] = new LinkedList<Integer>();
      for(int x: adj[i]){
        adjCopy[i].add(x);
      }
    }
    return adjCopy; 
  }

  void bfs(LinkedList<Integer> adj[], int root, int n){
  
    LinkedList <Integer> queue = new LinkedList<Integer>();
    depth[root] = 0; 
    queue.add(root);
    vis[root] = true;
    
    while(!queue.isEmpty()){

      int u = queue.removeFirst();        // The Stack      
      if(adj[u].size() > 0){  
        int v = adj[u].removeFirst();
        if(!vis[v]){
          queue.add(v);
          vis[v]    = true;
          depth[v]  = 1 + depth[u];
          f[v] = u;
        }
      }
    }
  }
  
  int lca(int u, int v){
    while(u != v){
      if(depth[u] < depth[v]){
        v = f[v];
      }else if(depth[u] > depth[v]){
        u = f[u];
      }else{
        u = f[u];
        v = f[v];
      }
    }
    return u;
  }
  long lcm(long a, long b){
    if(a == 0 || b == 0)return 0;
    return (a * b)/gcd(a, b);
  }

  long mulMod(long a, long b, long mod){
    if(a == 0 || b == 0)return 0;
    if(b == 1)return a;
    long ans = mulMod(a, b/2, mod);
    ans = (ans * 2) % mod;
    if(b % 2 == 1)ans = (a + ans)% mod;
    return ans;
  }

  long pow(long a, long b, long mod){
    if(b == 0)return 1;
    if(b == 1)return a;
    long ans = pow(a, b/2, mod);
    ans = (ans * ans);
    if(ans >= mod)ans %= mod;

    if(b % 2 == 1)ans = (a * ans);
    if(ans >= mod)ans %= mod;

    return ans;
  }

  // 20*20   nCr Pascal Table
  long[][] ncrTable(){
    long ncr[][] = new long[21][21];

    for(int i = 0; i <= 20; i++){
      ncr[i][0] = ncr[i][i] = 1L;
    }

    for(int j = 0; j <= 20; j++){
      for(int i = j + 1; i <= 20; i++){
        ncr[i][j] = ncr[i-1][j] + ncr[i-1][j-1];
      }
    }

    return ncr;
  }

  int i()throws Exception{
    return in.nextInt();
  }

  int[] is(int n)throws Exception{
    for(int i=1 ; i <= n ;i++)auxInts[i] = in.nextInt();  
      return auxInts;
  }

  long l()throws Exception{
    return in.nextLong();
  }

  long[] ls(int n)throws Exception{
    for(int i=1 ; i <= n ;i++)auxLongs[i] = in.nextLong();  
      return auxLongs;
  }

  double d()throws Exception{
    return in.nextDouble();
  }

  double[] ds(int n)throws Exception{
    for(int i=1 ; i <= n ;i++)auxDoubles[i] = in.nextDouble();  
      return auxDoubles;
  }

  char c()throws Exception{
    return in.nextCharacter();
  }

  char[] cs(int n)throws Exception{
    for(int i=1 ; i <= n ;i++)auxChars[i] = in.nextCharacter();  
      return auxChars;
  }

  String s()throws Exception{
    return in.nextLine();
  }

  BigInteger bi()throws Exception{
    return in.nextBigInteger();
  }

  private void closeResources(){
    out.flush();
    out.close();
    return;
  }

//  IMP: roundoff upto 2 digits 
//  double roundOff = Math.round(number1 * 100.0) / 100.0;
//                    or
//  System.out.printf("%.2f", val);

//  print upto 2 digits after decimal
//  val = ((long)(val * 100.0))/100.0;    

  public static void main(String[] args) throws java.lang.Exception{
  
    BFSAndLCA driver = new BFSAndLCA(true);
    driver.run();
    driver.closeResources();
  }
}


