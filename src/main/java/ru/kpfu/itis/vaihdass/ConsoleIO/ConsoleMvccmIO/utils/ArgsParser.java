package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.exceptions.InputEmptyCommandException;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class ArgsParser {
    private static final Pattern SPLIT_PATTERN = Pattern.compile("([^'\"]\\S*|[\"'].+?[\"|'])\\s*");

    public static void parseInput(Request request, Properties properties) throws InputEmptyCommandException {
        if (request == null || request.getData() == null) {
            throw new InputEmptyCommandException("Request data can't be null.");
        }

        List<String> inputList = new ArrayList<>();
        Matcher matcher = SPLIT_PATTERN.matcher(request.getData());
        while (matcher.find()) inputList.add(matcher.group(1));
        List<String> splitInput = removeEmptyStringsDashesBrackets(inputList);

        switch (splitInput.size()) {
            case 0:
                throw new InputEmptyCommandException("Input is empty.");
            case 1:
                properties.setCommand(splitInput.get(0));
                break;
            default:
                properties.setCommand(splitInput.get(0));
                properties.setArgs(splitInput.subList(1, splitInput.size()));
        }
    }

    protected static List<String> removeEmptyStringsDashesBrackets(List<String> inputArray) {
        return inputArray.stream().map(ArgsParser::removeIfFirstDash)
                .map(ArgsParser::removeIfInBrackets)
                .map(String::trim)
                .filter(s -> !s.equals(""))
                .collect(Collectors.toList());
    }

    protected static String removeIfInBrackets(String text) {
        if (
                text.length() >= 2 && ((text.charAt(0) == '\"' && text.charAt(text.length() - 1) == '\"')
                || (text.charAt(0) == '\'' && text.charAt(text.length() - 1) == '\''))
        ) return text.substring(1, text.length() - 1);

        return text;
    }

    protected static String removeIfFirstDash(String text) {
        if (text.length() != 0 && text.charAt(0) == '-') return (text.length() > 1 ? text.substring(1) : "");
        return text;
    }
}
