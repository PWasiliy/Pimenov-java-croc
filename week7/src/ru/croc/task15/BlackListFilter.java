package ru.croc.task15;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

public interface BlackListFilter<T> {
    default ArrayList<T> filterByBlackList(Predicate<T> isInBlackList, Iterable<T> comments) {
        ArrayList<T> result = new ArrayList<>();
        Iterator<T> it = comments.iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (!isInBlackList.test(next))
                result.add(next);
        }
        return result;
    }
}
