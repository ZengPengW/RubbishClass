package com.zp.rubbish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {

    @RequestMapping(value = {"/"})
    public  String page(){
        return  "index";
    }

    @RequestMapping(value = {"/{page}"})
    public  String returnPage(@PathVariable("page") String page, HttpServletRequest request){
        if(page.equals("feedbackEdit")){
            String rubbishName=request.getParameter("rubbishName");
            request.setAttribute("rubbishName", rubbishName);
        }
        return page;
    }
}
