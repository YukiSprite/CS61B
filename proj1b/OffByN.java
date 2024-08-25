import java.util.Comparator;

public class OffByN implements CharacterComparator {
    private int distance;
    OffByN(int N) {
        distance = N;
    }
    public boolean equalChars(char x, char y) {
        return (x - y == distance)||(y - x == distance);
    }
}
