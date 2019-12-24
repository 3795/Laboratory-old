package cn.ntshare.laboratory.service;


import cn.ntshare.laboratory.vo.*;

/**
 * Created by Qinyi.
 */
public interface IAdUnitService {

    AdUnitResponse createUnit(AdUnitRequest request);

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request);

    AdUnitItResponse createUnitIt(AdUnitItRequest request);

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request);

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request);
}
