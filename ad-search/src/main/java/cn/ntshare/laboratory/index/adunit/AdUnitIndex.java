package cn.ntshare.laboratory.index.adunit;

import cn.ntshare.laboratory.index.IndexAware;
import cn.ntshare.laboratory.index.adplan.AdPlanObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2020/1/2
 */
@Slf4j
public class AdUnitIndex implements IndexAware<Long, AdUnitObject> {

    private static Map<Long, AdUnitObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdUnitObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {
        objectMap.put(key, value);
    }

    @Override
    public void update(Long key, AdUnitObject value) {
        AdUnitObject oldObject = objectMap.get(key);
        if (null == oldObject) {
            add(key, value);
        } else {
            oldObject.update(value);
        }
    }

    @Override
    public void delete(Long key, AdUnitObject value) {
        objectMap.remove(key);
    }

    public Set<Long> match(Integer positionType) {
        Set<Long> adUnitIds = new HashSet<>();
        return adUnitIds;
    }

    public List<AdUnitObject> fetch(Collection<Long> adUnitIds) {
        List<AdUnitObject> list = new ArrayList<>();
        adUnitIds.forEach(e -> {
            AdUnitObject object = get(e);
            if (object == null) {
                log.error("AdUnitObject Not Found，id = {}", e);
                return;
            }
            list.add(object);
        });
        return list;
    }
}
