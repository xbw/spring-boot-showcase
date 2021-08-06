package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.project.service.ActivitiModelService;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/activiti-explorer/model")
public class ActivitiModelController {
    @Autowired
    ActivitiModelService modelService;

    @RequestMapping("/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            Model model = modelService.saveModel();
            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + model.getId());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deploy/{modelId}")
    public String deploy(@PathVariable String modelId) {
        return modelService.deploy(modelId);
    }

    @RequestMapping("/delete/{modelId}")
    public String delete(@PathVariable String modelId) {
       return modelService.delete(modelId);
    }
}