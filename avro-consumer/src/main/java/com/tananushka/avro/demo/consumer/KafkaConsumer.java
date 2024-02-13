package com.tananushka.avro.demo.consumer;

import com.tananushka.avro.demo.AvroMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(
            topics = "${spring.kafka.topic-name}",
            groupId = "${spring.kafka.consumer.group-id}",
            concurrency = "${spring.kafka.listener.concurrency}"
    )
    public void consumeMessage(ConsumerRecord<String, AvroMessage> consumerRecord) {
        try {
            AvroMessage message = consumerRecord.value();
            String topic = consumerRecord.topic();
            int partition = consumerRecord.partition();
            long offset = consumerRecord.offset();

            log.info("Consumed message from topic={}, partition={}, offset={} with value={}",
                    topic, partition, offset, message);
        } catch (Exception e) {
            log.error("Error processing message={}", e.getMessage(), e);
        }
    }
}
