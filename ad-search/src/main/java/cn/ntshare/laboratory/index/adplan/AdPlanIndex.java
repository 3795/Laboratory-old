package cn.ntshare.laboratory.index.adplan;

import cn.ntshare.laboratory.index.IndexAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2020/1/2
 */
public class AdPlanIndex implements IndexAware<Long, AdPlanObject> {

    private static Map<Long, AdPlanObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdPlanObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdPlanObject value) {
        objectMap.put(key, value);
    }

    @Override
    public void update(Long key, AdPlanObject value) {
        AdPlanObject oldObject = objectMap.get(key);
        if (oldObject == null) {
            add(key, value);
        } else {
            oldObject.update(value);
        }
    }

    @Override
    public void delete(Long key, AdPlanObject value) {
        objectMap.remove(key);
    }
}
