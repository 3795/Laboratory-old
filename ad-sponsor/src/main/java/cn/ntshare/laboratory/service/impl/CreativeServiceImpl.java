package cn.ntshare.laboratory.service.impl;

import cn.ntshare.laboratory.dao.CreativeRepository;
import cn.ntshare.laboratory.entity.Creative;
import cn.ntshare.laboratory.service.ICreativeService;
import cn.ntshare.laboratory.vo.CreativeRequest;
import cn.ntshare.laboratory.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreativeServiceImpl implements ICreativeService {

    @Autowired
    private CreativeRepository creativeRepository;

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {
        Creative creative = creativeRepository.save(request.convertToEntity());
        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
