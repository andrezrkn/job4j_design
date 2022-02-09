package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Analize {

    private static Map<Integer, String> addInMap(Set<User> set) {
        Map<Integer, String> map = new HashMap<>();
        for (User el : set) {
            map.put(el.getId(), el.getName());
        }
        return map;
    }

    private static int searchChanges(Set<User> set, Map<Integer, String> map,
                                     BiPredicate<User,
                                             Map<Integer, String>> condition) {
        int changes = 0;
        for (User user : set) {
            if (condition.test(user, map)) {
                changes++;
            }
        }
        return changes;
    }

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> prev = addInMap(previous);
        Map<Integer, String> curr = addInMap(current);
        int added = searchChanges(current, prev, (user, map)
                -> !map.containsKey(user.getId()));
        int changed = searchChanges(current, prev, (user, map)
                -> map.containsKey(user.getId())
                && !map.get(user.getId()).equals(user.getName()));
        int deleted = searchChanges(previous, curr, (user, map)
                -> !map.containsKey(user.getId()));
        return new Info(added, changed, deleted);
    }
}

