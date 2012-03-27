package com.mirth.tools.header;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.resources.FileResource;

public class HeaderTask extends Task {
    private File headerFile;
    private FileSet fileSet;

    public void setHeaderFile(File headerFile) {
        this.headerFile = headerFile;
    }

    public void addFileSet(FileSet fileSet) {
        this.fileSet = fileSet;
    }

    public void execute() throws BuildException {
        HeaderAppender appender = new HeaderAppender();

        try {
            String header = FileUtils.readFileToString(headerFile);

            for (Iterator<FileResource> iterator = fileSet.iterator(); iterator.hasNext();) {
                FileResource fileResource = iterator.next();
                log("Appending header: " + fileResource.getName(), Project.MSG_VERBOSE);

                File file = fileResource.getFile();
                String contents = appender.appendHeaderToFile(header, file.getName(), FileUtils.readFileToString(file), getProject().getProperties());
                FileUtils.writeStringToFile(file, contents);
            }

            log("Header appended to " + fileSet.size() + " file(s)", Project.MSG_INFO);
        } catch (Exception e) {
            throw new BuildException(e);
        }
    }
}
