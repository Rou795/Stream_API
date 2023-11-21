import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        ArrayList<Integer> polList = new ArrayList<>();

        for (int num : intList) {
            if (num > 0 && num % 2 == 0) {
                polList.add(num);
            }
        }
        polList.sort(Comparator.naturalOrder());
        for (int num : polList) {
            System.out.println(num);
        }
    }
}