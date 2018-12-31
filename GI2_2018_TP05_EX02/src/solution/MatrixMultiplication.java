package solution;
public class MatrixMultiplication {

    //initializes variables for dimensions
    public static int M = 3;
    public static int K = 2;
    public static int N = 3;
    
    //Declares Arrays A,B,C, and an Array or WorkerThreads
    public static double [][] A = {{1,0}, {-1,1}, {0,1}}; //Initializes A
    public static double [][] B = {{-1,1,1}, {0,0,1}};    //Initializes B
    public static double [][] C = new double [M][N];
    public static WorkerThread [][] Threads = new WorkerThread[3][3];
    
    
    public static void main(String[] args) {      
        //creates 9 Worker threads. Each thread Calculates a Matrix Value and sets it to C matrix
        for (int i = 0; i<M; i++){
            for (int j=0; j<N; j++){
                Threads[i][j] = new WorkerThread(i,j,A,B,C);
                Threads[i][j].start();
            }
        }
        
        
        System.out.println("Elements of Matrix A:");
        for (int i = 0; i<M; i++){
            for (int j=0; j<K; j++){
                System.out.print(A[i][j] +"  ");
            }
            System.out.println();
        }  
        
        
        System.out.println("Elements of Matrix B:");
        for (int i = 0; i<K; i++){
            for (int j=0; j<N; j++){
                System.out.print(B[i][j] +"  ");
            }
            System.out.println();
        }  
        //Outputs the Values of Matrix C
        System.out.println("Elements of Matrix C:");
        for (int i = 0; i<M; i++){
            for (int j=0; j<N; j++){
                System.out.print(C[i][j] +"  ");
            }
            System.out.println();
        }  
    }
}