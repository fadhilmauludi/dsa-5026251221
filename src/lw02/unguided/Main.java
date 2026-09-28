package lw02.unguided;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));
        
        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> fails = new Stack<>();
        LinkedList<String[]> successes = new LinkedList<>();

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        while (sc.hasNext()) {
            requests.add(new String[]{sc.next(), sc.next()});
        }
        
        queue.addAll(requests);

        while (!queue.isEmpty()) {
            String[] request = queue.poll();
            String name = request[0];
            String title = request[1];

            String[] member = null;
            for (String[] m : members) {
                if (m[0].equals(name)) member = m;
            }
            if (member == null) {
                member = new String[]{name, "0"};
                members.add(member);
            }

            String[] book = null;
            for (String[] b : books) {
                if (b[0].equals(title)) book = b;
            }

            int stock = Integer.parseInt(book[1]);
            int borrowed = Integer.parseInt(member[1]);

            if (borrowed < 2 && stock > 0) {
                book[1] = String.valueOf(stock - 1);
                member[1] = String.valueOf(borrowed + 1);
                successes.add(request);
            } else {
                fails.push(request);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] s : successes) {
            System.out.println(s[0] + " " + s[1]);
        }

        System.out.println("");

        System.out.println("=== Remaining Book Stock ===");
        for (String[] b : books) {
            System.out.println(b[0] + ": " + b[1]);
        }

        System.out.println("");

        System.out.println("=== Failed Requests ===");
        while (!fails.isEmpty()) {
            String[] f = fails.pop();
            System.out.println(f[0] + " " + f[1]);
        }
    }
}