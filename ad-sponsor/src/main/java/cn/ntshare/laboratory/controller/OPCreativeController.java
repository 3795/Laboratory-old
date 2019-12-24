package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.service.ICreativeService;
import cn.ntshare.laboratory.vo.CreativeRequest;
import cn.ntshare.laboratory.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creative")
public class OPCreativeController {

    @Autowired
    private ICreativeService creativeService;

    @PostMapping("/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) {
        return creativeService.createCreative(request);
    }
}
