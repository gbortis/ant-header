package com.mirth.tools.header.builders;


public class JspHeaderBuilder extends HeaderBuilder {

    @Override
    public String getOpenComment() {
        return "<%--";
    }

    @Override
    public String getCloseComment() {
        return "--%>";
    }

    @Override
    public String getBlockComment() {
        return "*";
    }
}
