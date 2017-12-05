/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.core.routes;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.tarfile.TarSplitter;
import org.apache.camel.dataformat.zipfile.ZipSplitter;
import org.springframework.stereotype.Component;

/**
 * TODO: Missing class level javadoc
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Component("uploader.routes.FileInputRoute")
@Slf4j(topic = "uploader.routes.FileInputRoute.log")
public class FileInputRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .log(LoggingLevel.ERROR, "${exception.message}")
                .log(LoggingLevel.DEBUG, "${exception.stacktrace}")
                .handled(true);

        from(this.fileInputUri())
                .routeId("uploader.routes.FileInputRoute.INPUT")
                .log(LoggingLevel.DEBUG, "Input headers:        ${headers}")
                .log(LoggingLevel.DEBUG, "Input file extension: ${file:ext}")
                .choice()
                .when(simple("${file:ext} =~ 'zip'"))
                .split(new ZipSplitter())
                .streaming()
                .to("direct:split-result")
                .endChoice()
                .when(simple("${file:ext} =~ 'tar.gz'"))
                .unmarshal().gzip()
                .split(new TarSplitter())
                .streaming()
                .to("direct:split-result")
                .endChoice()
                .otherwise()
                .log(LoggingLevel.WARN, "Cannot process uploaded file: [${file:absolute.path}], moved to '${properties:camel.fileinput.sourceDir}/.unprocessable' directory")
                .to("file:{{camel.fileinput.sourceDir}}/.unprocessable")
                .end(); // End of choice

        from("direct:split-result")
                .routeId("uploader.routes.FileInputRoute.SPLIT_RESULT")
                .log(LoggingLevel.DEBUG, "Splitter output headers: ${headers}")
                .log(LoggingLevel.DEBUG, "Splitter output (IN):    ${in.body.class}")
                .process((exch) -> {
                    BufferedInputStream body = (BufferedInputStream) exch.getIn().getBody();

                    log.debug("body.available(): {}", body.available());

                    ByteArrayOutputStream buf = new ByteArrayOutputStream();
                    int result = body.read();
                    while (result != -1) {
                        buf.write((byte) result);
                        result = body.read();
                    }

                    log.debug("---- FILE BEGIN ---");
                    log.debug("{}", buf.toString());
                    log.debug("---- FILE END ---");
                })
                .stop();
    }

    private String fileInputUri() {
        final String connectionString = "file:{{camel.fileinput.sourceDir}}";
        final Map<String, String> options = new HashMap<>();
//        options.put("move", ".done");
        options.put("moveFailed", ".error");
        options.put("readLock", "fileLock");

        final StringBuilder sb = new StringBuilder(connectionString);

        if (!options.isEmpty()) {
            final String optionsString = options.entrySet().stream()
                    .map(e -> String.join("=", e.getKey(), e.getValue()))
                    .collect(Collectors.joining("&"));

            sb.append("?");
            sb.append(optionsString);
        }

        return sb.toString();
    }
}
