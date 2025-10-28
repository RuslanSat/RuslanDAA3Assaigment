import java.util.List;
public class MSTResult {
    public List<Main.Edge> edges;
    public int totalCost;
    public int operationsCount;

    public MSTResult(List<Main.Edge> edges, int totalCost, int operationsCount) {
        this.edges = edges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
    }
}