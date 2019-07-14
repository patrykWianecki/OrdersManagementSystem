package com.app.parser.interfaces;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;

@FunctionalInterface
public interface Parser<T> {

    T parse(String line);

    static boolean isLineCorrect(String line, String regex) {
        return line != null && line.matches(regex);
    }

    static <T> List<T> parseFile(String fileName, Parser<T> parser) {
        try (
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            Stream<String> lines = Files.lines(Paths.get(fileName))
        ) {
            return lines
                .map(parser::parse)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyException(ExceptionCode.PARSER, "Failed to parse file");
        }
    }
}
