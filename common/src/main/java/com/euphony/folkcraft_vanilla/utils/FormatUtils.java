package com.euphony.folkcraft_vanilla.utils;

import org.apache.commons.lang3.StringUtils;

public class FormatUtils {
    public static String formatName(String name) {
        if (name.contains("_")) {
            String[] parts = name.split("_");
            for (int i = 0; i < parts.length; i++) {
                parts[i] = StringUtils.capitalize(parts[i]);
            }
            return String.join(" ", parts);
        } else {
            return StringUtils.capitalize(name);
        }
    }
}
