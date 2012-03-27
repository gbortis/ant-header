package com.mirth.tools.header.builders;

public class HeaderBuilderFactory {
    public static HeaderBuilder createHeaderBuilder(String extension) {
        if ("jsp".equalsIgnoreCase(extension)) {
            return new JspHeaderBuilder();
        } else if ("java".equalsIgnoreCase(extension) || "js".equalsIgnoreCase(extension)) {
            return new JavaHeaderBuilder();
        } else {
            return new NoOpHeaderBuilder();
        }
    }
}
