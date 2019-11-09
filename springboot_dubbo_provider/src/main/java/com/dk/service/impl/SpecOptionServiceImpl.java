package com.dk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dk.mapper.TbSpecificationOptionMapper;
import com.dk.pojo.TbSpecificationOption;
import com.dk.service.SpecOptionService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Service
public class SpecOptionServiceImpl implements SpecOptionService {

    @Resource
    private TbSpecificationOptionMapper TbSpecificationOptionMapper;

    @Override
    public List<TbSpecificationOption> findOptionBySpecId(Long id) {
        return TbSpecificationOptionMapper.findOptionBySpecId(id);
    }

    @Override
    public void deleteOptionBySpecId(Long id) {
        TbSpecificationOptionMapper.deleteOptionBySpecId(id);
    }

    @Override
    public void addBatch(List<TbSpecificationOption> options) {
        TbSpecificationOptionMapper.addBatch(options);
    }
}
