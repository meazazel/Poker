import java.util.*;

public class Evaluater {

    public static Result evaluate(ArrayList<card> cards) {

        HashMap<Integer, Integer> count = new HashMap<>();
        HashMap<String, Integer> suitCount = new HashMap<>();
        ArrayList<Integer> allValues = new ArrayList<>();

        for (card c : cards) {
            int v = c.getValue();
            String s = c.getSuit();

            allValues.add(v);

            count.put(v, count.getOrDefault(v, 0) + 1);
            suitCount.put(s, suitCount.getOrDefault(s, 0) + 1);
        }

        Collections.sort(allValues, Collections.reverseOrder());

        int four = -1;
        int three = -1;
        ArrayList<Integer> pairs = new ArrayList<>();

        for (int v : count.keySet()) {
            int f = count.get(v);

            if (f == 4) four = v;
            else if (f == 3) three = v;
            else if (f == 2) pairs.add(v);
        }

        // FOUR
        if (four != -1) {
            ArrayList<Integer> res = new ArrayList<>();
            res.add(four);
            for (int v : allValues) {
                if (v != four) {
                    res.add(v);
                    break;
                }
            }
            return new Result(7, res);
        }

        // FULL HOUSE
        if (three != -1 && pairs.size() >= 1) {
            return new Result(6, new ArrayList<>(Arrays.asList(three, Collections.max(pairs))));
        }

        // FLUSH
        String flushSuit = null;
        for (String s : suitCount.keySet()) {
            if (suitCount.get(s) >= 5) {
                flushSuit = s;
                break;
            }
        }

        if (flushSuit != null) {
            ArrayList<Integer> flushVals = new ArrayList<>();
            for (card c : cards) {
                if (c.getSuit().equals(flushSuit)) {
                    flushVals.add(c.getValue());
                }
            }
            Collections.sort(flushVals, Collections.reverseOrder());
            return new Result(5, flushVals);
        }

        // STRAIGHT
        HashSet<Integer> set = new HashSet<>(allValues);
        ArrayList<Integer> unique = new ArrayList<>(set);
        Collections.sort(unique);

        int streak = 1;
        int last = -1;

        for (int v : unique) {
            if (last != -1 && v == last + 1) {
                streak++;
                if (streak >= 5) {
                    return new Result(4, new ArrayList<>(Arrays.asList(v)));
                }
            } else {
                streak = 1;
            }
            last = v;
        }

        // THREE
        if (three != -1) {
            ArrayList<Integer> res = new ArrayList<>();
            res.add(three);
            for (int v : allValues) {
                if (v != three) res.add(v);
            }
            return new Result(3, res);
        }

        // TWO PAIR
        if (pairs.size() >= 2) {
            Collections.sort(pairs, Collections.reverseOrder());
            ArrayList<Integer> res = new ArrayList<>();
            res.add(pairs.get(0));
            res.add(pairs.get(1));
            return new Result(2, res);
        }

        // ONE PAIR
        if (pairs.size() == 1) {
            ArrayList<Integer> res = new ArrayList<>();
            res.add(pairs.get(0));
            for (int v : allValues) {
                if (v != pairs.get(0)) res.add(v);
            }
            return new Result(1, res);
        }

        return new Result(0, allValues);
    }
}