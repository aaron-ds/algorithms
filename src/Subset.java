/**
 * Created by aarondesouza on 28/06/2015.
 */
public class Subset {

    public static void main(String[] args) {
        final int k = Integer.getInteger(args[0]);
        final String s = StdIn.readString();
        final String[] tokens = s.split(" ");
        final RandomizedQueue<String> q = new RandomizedQueue<>();

        for (int i = 0; i < tokens.length; i++) {
            String t = tokens[i].trim();
            q.enqueue(t);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(q.dequeue());
        }
    }
}
