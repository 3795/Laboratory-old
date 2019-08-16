package cn.ntshare.laboratory.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test.topic")
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.printf("topic=%s, offset=%d, value=%s\n", record.topic(), record.offset(), record.value());
    }
}
