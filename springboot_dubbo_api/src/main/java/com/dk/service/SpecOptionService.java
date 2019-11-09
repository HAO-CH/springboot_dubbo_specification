package com.dk.service;

import com.dk.pojo.TbSpecificationOption;

import java.util.List;

public interface SpecOptionService {
    List<TbSpecificationOption> findOptionBySpecId(Long id);

    void deleteOptionBySpecId(Long id);

    void addBatch(List<TbSpecificationOption> options);
}
