package ru.croc.task13;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter implements BlackListFilter{
    public void filterComments(List<String> comments, Set<String> blackList) {
        Pattern pattern = Pattern.compile("[a-zа-яA-ZА-Я_0-9]+");
        for (int i = 0; i < comments.size(); i++) {
            Matcher matcher = pattern.matcher(comments.get(i));
            while (matcher.find()) {
                if (blackList.contains(matcher.group().toLowerCase())) {
                    comments.remove(i);
                    --i;
                    break;
                }
            }
        }
    }
}
