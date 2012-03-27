package com.mirth.tools.header.builders;

import org.apache.commons.lang.StringUtils;

public class NoOpHeaderBuilder extends HeaderBuilder {

    @Override
    public String getOpenComment() {
        return null;
    }

    @Override
    public String getCloseComment() {
        return null;
    }

    @Override
    public String getBlockComment() {
        return null;
    }

    @Override
    public String buildHeader(String header) {
        return StringUtils.EMPTY;
    }

    @Override
    public String removeHeader(String contents) {
        return contents;
    }

}
