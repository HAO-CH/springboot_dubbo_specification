package com.dk.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.dk.mapper.TbSpecificationMapper;
import com.dk.pojo.TbSpecification;
import com.dk.pojo.TbSpecificationOption;
import com.dk.service.SpecOptionService;
import com.dk.service.SpecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class SpecServiceImpl implements SpecService {

    @Resource
    private TbSpecificationMapper specificationMapper;

    @Reference
    private SpecOptionService specOptionService;

    @Override
    public PageInfo<TbSpecification> findAll(Integer pageNum, Integer pageSize, TbSpecification spec) {
        PageHelper.startPage(pageNum, pageSize);
        List<TbSpecification> list = specificationMapper.findAll(spec);
        return new PageInfo<>(list);
    }

    @Override
    public TbSpecification findSpecById(Long id) {
        return specificationMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void deleteSpec(Long id) {
        //删除规格表中数据
        specificationMapper.deleteByPrimaryKey(id);
        //据规格id删除选项表中的数据
        specOptionService.deleteOptionBySpecId(id);
    }

    @Override
    @Transactional
    public void addSpec(TbSpecification spec, String[] optionNames, Integer[] orders) {
        //增加spec并返回id
        specificationMapper.InsertAndGetId(spec);
        //给spec增加选项
        List<TbSpecificationOption> options = new ArrayList<>();
        for (int i = 0; i < optionNames.length; i++) {
            TbSpecificationOption option = new TbSpecificationOption();
            option.setOptionName(optionNames[i]);
            option.setSpecId(spec.getId());
            option.setOrders(orders[i]);
            options.add(option);
        }
        specOptionService.addBatch(options);
    }

    @Override
    @Transactional
    public void updateSpec(TbSpecification spec, String[] optionNames, Integer[] orders) {
        specificationMapper.updateByPrimaryKeySelective(spec);
        //据规格id删除选项表中的数据
        specOptionService.deleteOptionBySpecId(spec.getId());
        //再给spec增加选项
        List<TbSpecificationOption> options = new ArrayList<>();
        for (int i = 0; i < optionNames.length; i++) {
            TbSpecificationOption option = new TbSpecificationOption();
            option.setOptionName(optionNames[i]);
            option.setSpecId(spec.getId());
            option.setOrders(orders[i]);
            options.add(option);
        }
        specOptionService.addBatch(options);
    }
}
