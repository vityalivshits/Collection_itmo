package Server;

public class Server {
    public static void main(String[] args) throws Exception{

        MultiThreadSolver multiThreadSolver = new MultiThreadSolver();
        multiThreadSolver.StartListening();

    }
}
