package cn.ntshare.laboratory.index;

/**
 * Created By Q.Hao
 * Description: 索引接口
 * Created At 2020/1/2
 */
public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);
}
