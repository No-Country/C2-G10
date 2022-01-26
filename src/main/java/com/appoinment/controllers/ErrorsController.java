package com.appoinment.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
        ModelAndView errorPage = new ModelAndView("error");
        String errorMessage = " ";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMessage = "El recurso solicitado no existe.";
                break;
            }
            case 401: {
                errorMessage = "No se encuentra autorizado";
                break;
            }
            case 403: {
                errorMessage = "No tiene permisos para acceder al recurso.";
                break;
            }
            case 404: {
                errorMessage = "El recurso solicitado no fue encontrado.";
                break;
            }
            case 500: {
                errorMessage = "Ocurrió un error interno.";
                break;
            }
        }
        errorPage.addObject("errorcode", httpErrorCode);
        errorPage.addObject("message", errorMessage);
        return errorPage;
    }


    private int getErrorCode(HttpServletRequest httpRequest) {
        Map mapa = httpRequest.getParameterMap();
        for (Object key : mapa.keySet()) {
            String[] values = (String[]) mapa.get(key);
            for (String value : values) {
                System.out.println(key.toString() + ": " + value);
            }
        }

        Enumeration<String> atributes = httpRequest.getAttributeNames();
        while (atributes.hasMoreElements()) {
            String key = atributes.nextElement();
            System.out.println(key + ":" + httpRequest.getAttribute(key));
        }
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }

    public String getErrorPath() {
        return "/error";
    }
}
