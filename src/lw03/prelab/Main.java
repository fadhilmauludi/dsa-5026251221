package lw03.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        problemOne();
        problemTwo();
        problemThree();
    }
    
    static void problemOne() {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new LinkedList<>();
        
        while (sc.hasNext()) {
            String method = sc.next();
            if (method.equals("ADD")) {
                playlist.add(sc.nextLine().trim());
            } else if (method.equals("REMOVE")) {
                playlist.remove(sc.nextLine().trim());
            } else if (method.equals("INSERT")) {
                int index = sc.nextInt();
                String song = sc.nextLine().trim();
                playlist.add(index, song);
            }
        }
        
        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
        sc.close();
    }

    static void problemTwo() {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> participants = new LinkedHashSet<>();
        int duplicate = 0;
        
        while (sc.hasNext()) {
            String name = sc.next();
            if (!participants.add(name)) {
                duplicate++;
            }
        }
        System.out.println("");

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int i = 1;
        for (String participant : participants) {
            System.out.println(i + ". " + participant);
            i++;
        }
        System.out.println("Duplicate registrations: " + duplicate);
        sc.close();
    }

    static void problemThree() {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failed = 0;
        
        while (sc.hasNext()) {
            String type = sc.next();
            String product = sc.next();
            int quantity = sc.nextInt();
            
            if (type.equals("ADD")) {
                inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product)) {
                    int stock = inventory.get(product);
                    if (stock < quantity) {
                        failed++;
                    } else {
                        inventory.put(product, stock - quantity);
                    }
                } else {
                    failed++;
                }
            }
        }
        System.out.println("");

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failed);
        sc.close();
    }
}