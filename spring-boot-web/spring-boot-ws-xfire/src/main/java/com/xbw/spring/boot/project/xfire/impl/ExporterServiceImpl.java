package com.xbw.spring.boot.project.xfire.impl;

import com.xbw.spring.boot.project.xfire.ExporterService;
import org.springframework.stereotype.Component;

/**
 * @author xbw
 */
@Component
public class ExporterServiceImpl implements ExporterService {
    @Override
    public String sayExporter(String text) {
        return "Exporter " + text;
    }
}
