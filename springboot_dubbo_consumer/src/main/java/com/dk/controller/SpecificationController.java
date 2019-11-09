package com.dk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dk.pojo.TbSpecification;
import com.dk.pojo.TbSpecificationOption;
import com.dk.service.SpecOptionService;
import com.dk.service.SpecService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("specification")
public class SpecificationController {

    @Reference
    private SpecService specService;

    @Reference
    private SpecOptionService specOptionService;

    @RequestMapping("list")
    public String list() {
        return "specification/list";
    }

    @RequestMapping("find")
    @ResponseBody
    private Map<String, Object> find(Integer pageNum, Integer pageSize, TbSpecification spec) {
        PageInfo<TbSpecification> page = specService.findAll(pageNum, pageSize, spec);
        Map<String, Object> result = new HashMap<>();
        result.put("total", page.getTotal());
        result.put("rows", page.getList());
        return result;
    }

    @RequestMapping("toAdd")
    public String toAdd() {
        return "specification/add";
    }

    @RequestMapping("addSpec")
    @ResponseBody
    public boolean addSpec(TbSpecification spec, @RequestParam("optionNames[]") String[] optionNames, @RequestParam("orders[]") Integer[] orders) {
        specService.addSpec(spec,optionNames,orders);
        return true;
    }

    @RequestMapping("toUpdate/{id}")
    public String toUpdate(Model model, @PathVariable("id") Long id) {
        TbSpecification spec = specService.findSpecById(id);
        model.addAttribute("spec", spec);
        List<TbSpecificationOption> options = specOptionService.findOptionBySpecId(id);
        model.addAttribute("options", options);
        return "specification/update";
    }

    @RequestMapping("updateSpec")
    @ResponseBody
    public boolean updateSpec(TbSpecification spec, @RequestParam("optionNames[]") String[] optionNames, @RequestParam("orders[]") Integer[] orders) {
        specService.updateSpec(spec,optionNames,orders);
        return true;
    }

    @RequestMapping("deleteSpec/{id}")
    @ResponseBody
    public boolean deleteSpec(@PathVariable("id") Long id){
        specService.deleteSpec(id);
        return true;
    }
}
