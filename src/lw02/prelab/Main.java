package lw02.prelab;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> fails = new Stack<>();
        
        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (sc.hasNext()) {
            transactions.add(new String[]{sc.next(), sc.next(), sc.next()});
        }

        queue.addAll(transactions); 

        while (!queue.isEmpty()) {
            String[] t = queue.poll();
            int amount = Integer.parseInt(t[2]);
            String[] customer = null;

            for (String[] c : customers) {
                if (c[0].equals(t[0])) customer = c;
            }
            
            if (customer == null) {
                customer = new String[]{t[0], "0"};
                customers.add(customer);
            }

            
            int balance = Integer.parseInt(customer[1]);
            if (t[1].equals("DEPOSIT")) {
                customer[1] = String.valueOf(balance + amount);
            } else if (balance < amount) {
                fails.push(t);
            } else {
                customer[1] = String.valueOf(balance - amount);
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customers) System.out.println(c[0] + ": " + c[1]);

        System.out.println("");

        System.out.println("=== Failed Transactions ===");
        while (!fails.isEmpty()) {
            String[] f = fails.pop();
            System.out.println(f[0] + " " + f[1] + " " + f[2]);
        }
    }
}