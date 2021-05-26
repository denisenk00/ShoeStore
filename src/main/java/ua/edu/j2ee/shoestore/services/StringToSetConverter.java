package ua.edu.j2ee.shoestore.services;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class StringToSetConverter implements Converter<String, Set<String>> {

    @Override
    public Set<String> convert(String s) {
        new HashSet<>(Arrays.asList(s.split("\\+")));
        return null;
    }
}
