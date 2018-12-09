package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class ISysLogController {
    @Autowired
    private ISysLogService sysLogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv =new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll(page,size);
        PageInfo pageInfo =new PageInfo(sysLogs);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
    @RequestMapping("/deleteById.do")
    public String deleteById(String id) throws Exception {
        sysLogService.deleteById(id);
        return "redirect:findAll.do";
    }
}
