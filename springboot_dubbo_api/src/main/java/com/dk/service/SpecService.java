package com.dk.service;

import com.dk.pojo.TbSpecification;
import com.github.pagehelper.PageInfo;

public interface SpecService {
    PageInfo<TbSpecification> findAll(Integer pageNum, Integer pageSize, TbSpecification spec);

    TbSpecification findSpecById(Long id);

    void deleteSpec(Long id);

    void addSpec(TbSpecification spec, String[] optionNames, Integer[] orders);

    void updateSpec(TbSpecification spec, String[] optionNames, Integer[] orders);
}
