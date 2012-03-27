package com.mirth.tools.header;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import com.mirth.tools.header.builders.HeaderBuilder;
import com.mirth.tools.header.builders.HeaderBuilderFactory;

public class HeaderAppender {
    public String appendHeaderToFile(String header, String filename, String contents, Map<String, String> properties) throws IOException {
        Map<String, String> values = new HashMap<String, String>();
        values.put("file", filename);

        if (properties != null) {
            values.putAll(properties);
        }

        StrSubstitutor substitutor = new StrSubstitutor(values);
        StringBuilder output = new StringBuilder();
        HeaderBuilder headerBuilder = HeaderBuilderFactory.createHeaderBuilder(FilenameUtils.getExtension(filename));
        output.append(headerBuilder.buildHeader(substitutor.replace(header)));
        output.append(headerBuilder.removeHeader(contents));
        return output.toString();
    }
}
