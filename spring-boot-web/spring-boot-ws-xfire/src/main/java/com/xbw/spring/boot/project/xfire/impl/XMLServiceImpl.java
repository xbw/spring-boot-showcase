package com.xbw.spring.boot.project.xfire.impl;

import com.xbw.spring.boot.project.xfire.XMLService;

/**
 * @author xbw
 */
public class XMLServiceImpl implements XMLService {
    @Override
    public String sayXML(String text) {
        return "Exporter " + text;
    }
}
