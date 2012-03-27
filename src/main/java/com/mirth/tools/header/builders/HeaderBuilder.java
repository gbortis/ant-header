package com.mirth.tools.header.builders;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

public abstract class HeaderBuilder {
    private static final String EOL = System.getProperty("line.separator");

    public abstract String getOpenComment();

    public abstract String getCloseComment();

    public abstract String getBlockComment();

    public String buildHeader(String header) {
        StringBuilder output = new StringBuilder();
        LineIterator iterator = new LineIterator(new StringReader(header));
        int index = 0;

        while (iterator.hasNext()) {
            String line = iterator.nextLine();

            if (index == 0) {
                /* open the comment on the first line */
                output.append(getOpenComment());
                output.append(EOL);
                blockCommentLine(output, line);
            } else if (!iterator.hasNext()) {
                /* close the comment on the last line */
                blockCommentLine(output, line);
                output.append(EOL);
                output.append(" " + getCloseComment());
            } else {
                if (StringUtils.isNotBlank(line)) {
                    /* if the line is not blank, add the block comment character */
                    blockCommentLine(output, line);
                } else {
                    if (StringUtils.isNotBlank(getBlockComment())) {
                        /*
                         * if the line is blank, but the block comment character
                         * is not, then add a space before adding the character
                         */
                        output.append(" " + getBlockComment());
                    } else {
                        /*
                         * if the block comment character is blank then just add
                         * a blank line (with no space)
                         */
                        output.append(line);
                    }
                }
            }

            output.append(EOL);
            index++;
        }

        LineIterator.closeQuietly(iterator);

        /* always appends 2 lines after the header */
        output.append(EOL);
        output.append(EOL);

        return output.toString();
    }
    
    private void blockCommentLine(StringBuilder output, String line) {
        if (StringUtils.isNotBlank(line)) {
            output.append(" " + getBlockComment() + " " + line);    
        } else {
            output.append(" " + getBlockComment());
        }
    }

    public String removeHeader(String contents) {
        Pattern pattern = Pattern.compile("^\\s*(" + Pattern.quote(getOpenComment()) + ".+?" + Pattern.quote(getCloseComment()) + "\\s*)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(contents);
        return matcher.replaceFirst(StringUtils.EMPTY);
    }
}
