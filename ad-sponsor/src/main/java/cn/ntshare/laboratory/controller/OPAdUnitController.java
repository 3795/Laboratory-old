package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.service.IAdUnitService;
import cn.ntshare.laboratory.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unit")
public class OPAdUnitController {

    @Autowired
    private IAdUnitService adUnitService;

    @PostMapping
    public AdUnitResponse createUnit(@RequestBody AdUnitRequest request) {
        return adUnitService.createUnit(request);
    }

    @PostMapping("/unitKeyword")
    public AdUnitKeywordResponse createUnitKeyword(@RequestBody AdUnitKeywordRequest request) {
        return adUnitService.createUnitKeyword(request);
    }

    @PostMapping("/unitIt")
    public AdUnitItResponse createUnitIt(@RequestBody AdUnitItRequest request) {
        return adUnitService.createUnitIt(request);
    }

    @PostMapping("/unitDistrict")
    public AdUnitDistrictResponse createUnitDistrict(@RequestBody AdUnitDistrictRequest request) {
        return adUnitService.createUnitDistrict(request);
    }

    @PostMapping("/creativeUnit")
    public CreativeUnitResponse createCreativeUnit(@RequestBody CreativeUnitRequest request) {
        return adUnitService.createCreativeUnit(request);
    }

}
