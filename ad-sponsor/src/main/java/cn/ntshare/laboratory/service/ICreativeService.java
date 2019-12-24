package cn.ntshare.laboratory.service;


import cn.ntshare.laboratory.vo.CreativeRequest;
import cn.ntshare.laboratory.vo.CreativeResponse;

/**
 * Created by Qinyi.
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);
}
