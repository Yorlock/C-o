import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class main {

    private static class Vertex {
        private int prev;
        private boolean visited;
        private int adj;
        Vertex(){
            prev = -1;
            adj = -1;
            visited = false;
        }
    }
    static Vertex[] vertices;
    static ArrayList<Integer>[] Edges;

    static void DFS2(int v, int adj){
        vertices[v].adj = adj;
        vertices[v].visited = true;
        for(int e : Edges[v]){
            if(e != adj){
                if(vertices[e].adj < 0) DFS2(e, v);
            }
        }
    }

    static boolean DFS(int v){
        vertices[v].visited = true;
        vertices[v].adj = -2;
        int prev = vertices[v].prev;
        for (int e : Edges[v]) {
            if (e != prev) {
                if (-1 == vertices[e].adj) {
                    vertices[e].prev = v;
                    if (DFS(e)) return true;
                } else {
                    DFS2(e,v);
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String arg[]) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        vertices = new Vertex[n];
        Edges = new ArrayList[n];
        for(int i = 0; i < n; i++){
            vertices[i] = new Vertex();
            Edges[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < m; i++){
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            Edges[a].add(b);
            Edges[b].add(a);
        }
        for(int i = 0; i < n; i++){
            if(!vertices[i].visited) DFS(i);
        }
        boolean exist = true;
        for(int i=0; i<n; i++){
            if(vertices[i].adj < 0){
                exist = false;
                break;
            }
        }
        if(exist){
            System.out.println("TAK");
            for(Vertex v : vertices){
                System.out.println(v.adj + 1);
            }
        }else System.out.println("NIE");
    }
}
