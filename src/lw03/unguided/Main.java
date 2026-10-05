package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        Map<String, Integer> enrollment = new HashMap<>();
        List<String> courseOrder = new ArrayList<>();
        List<String> check = new ArrayList<>();
        int rejected = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");
            String command = parts[0];
            String courseCode = parts[1];

            if (command.equals("REGISTER")) {
                int count = Integer.parseInt(parts[2]);
                if (count <= 0) {
                    rejected++;
                } else {
                    if (!enrollment.containsKey(courseCode)) {
                        enrollment.put(courseCode, count);
                        courseOrder.add(courseCode);
                    } else {
                        enrollment.put(courseCode, enrollment.get(courseCode) + count);
                    }
                }
            } else if (command.equals("WITHDRAW")) {
                int count = Integer.parseInt(parts[2]);
                if (count <= 0 || !enrollment.containsKey(courseCode) || enrollment.get(courseCode) < count) {
                    rejected++;
                } else {
                    enrollment.put(courseCode, enrollment.get(courseCode) - count);
                }
            } else if (command.equals("CHECK")) {
                if (enrollment.containsKey(courseCode)) {
                    check.add(courseCode + ": " + enrollment.get(courseCode) + " students");
                } else {
                    check.add(courseCode + ": Not found");
                }
            }
        }
        sc.close();

        System.out.println("===== Enrollment Checks =====");
        for (int i = 0; i < check.size(); i++) {
            System.out.println(check.get(i));
        }

        System.out.println();
        System.out.println("===== Final Enrollment =====");
        for (int i = 0; i < courseOrder.size(); i++) {
            String course = courseOrder.get(i);
            System.out.println(course + ": " + enrollment.get(course) + " students");
        }

        System.out.println();
        System.out.println("Rejected operations: " + rejected);
    }
}
