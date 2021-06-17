package ua.edu.j2ee.shoestore.services;

import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

public class StringToSetIntConverter implements Converter<String, Set<Integer>> {
    @Override
    public Set<Integer> convert(String s) {
        Set<Integer> integers = new HashSet<>();
        if(s.isEmpty()) return integers;
        String[] values = s.split(" ");
        for (int i = 0; i < values.length; i++){
            integers.add(Integer.parseInt(values[i]));
        }
        return integers;
    }
}
