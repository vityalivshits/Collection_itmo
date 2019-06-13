package Server;

public class Server {
    public static void main(String[] args) {

        MultiThreadSolver multiThreadSolver = new MultiThreadSolver();
        multiThreadSolver.StartListening();

    }
}
