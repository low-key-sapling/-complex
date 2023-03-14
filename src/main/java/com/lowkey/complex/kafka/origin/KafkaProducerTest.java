package com.lowkey.complex.kafka.origin;

import kafka.common.KafkaException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.Properties;

/**
 * @author lowkey
 * @description Kafka生产者
 * @date 2022年09月06日 13:56
 */
public class KafkaProducerTest {
    {
        //kafka broker
        //在server.properties文件中配置：
        //1、broker.id
        //kafka集群是由多个节点组成的，每个节点称为一个broker，中文翻译是代理。每个broker都有一个不同的brokerId，由broker.id指定，是一个不小于0的整数，各brokerId必须不同，但不必连续。如果我们想扩展kafka集群，只需引入新节点，分配一个不同的broker.id即可。
        //启动kafka集群时，每一个broker都会实例化并启动一个kafkaController，并将该broker的brokerId注册到zooKeeper的相应节点中。集群各broker会根据选举机制选出其中一个broker作为leader，即leader kafkaController。leader kafkaController负责主题的创建与删除、分区和副本的管理等。当leader kafkaController宕机后，其他broker会再次选举出新的leader kafkaController。
        //2、log.dir
        //broker持久化消息到哪里。broker启动后，在此目录中会有多个文件及目录，文件有cleaner-offset-checkpoint、log-start-offset-checkpoint、recovery-point-offset-checkpoint、replication-offset-checkpoint、meta.properties。创建topic后，假如有副本分到这个broker上，则在log.dir目录中会创建一个此副本对应的目录，目录名格式是{topic}-{partition}，例如test-0，从名字就可以看出这是test topic的partition 0的一个副本。partition编号从0开始。test-0目录中有.log、.index、.timeindex、leader-epoch-checkpoint文件。log文件是实际存放消息的文件，称为数据文件。index文件是消息偏移量索引文件，timeindex文件是消息时间戳索引文件。log文件可能会有多个，这表示log分段了。至于什么情况下log会分段，见下面配置解释。每个数据文件的名称是该数据文件的第一条消息的偏移量左补0构成的20位数字字符。因为偏移量从0开始，所以每个分区每个副本的第一个数据文件都是00000000000000000000.log，后续每个数据文件的第一条消息的偏移量是上一个数据文件最后一条消息的偏移量+1。
        //3、log.retention.hours
        //log文件最小存活时间，默认是168h，即7天。相同作用的还有log.retention.minutes、log.retention.ms。retention是保存的意思。
        //4、log.retention.check.interval.ms
        //多长时间检查一次是否有log文件要删除。默认是300000ms，即5分钟。所以一个消息的实际存活时间是介于log.retention.hours和(log.retention.hours + log.retention.check.interval.ms)之间的。
        //5、log.retention.bytes
        //限制单个分区的log文件的最大值，超过这个值，将删除旧的log，以满足log文件不超过这个值。默认是-1，即不限制。实际上这个配置项除了-1，不应该配置成其他值。同retention.bytes。
        //6、log.roll.hours
        //多少时间会生成一个新的log segment，默认是168h，即7天。相同作用的还有log.roll.ms、segment.ms。
        //7、log.segment.bytes
        //log segment多大之后会生成一个新的log segment，默认是1073741824，即1G。个人感觉按照时间生成log segment比按照大小生成log segment的策略要好，便于管理。同segment.bytes。
        //8、log.flush.interval.messages
        //指定broker每收到几个消息就把消息从内存刷到硬盘。默认是9223372036854775807，哈哈，好大。kafka官方不建议使用这个配置，建议使用副本机制和操作系统的后台刷新功能，因为这更高效。这个配置可以根据不同的topic设置不同的值，即在创建topic的时候设置值。同flush.messages。
        //9、log.flush.interval.ms
        //指定broker每隔多少毫秒就把消息从内存刷到硬盘。默认值同log.flush.interval.messages一样。同log.flush.interval.messages一样，kafka官方不建议使用这个配置。同flush.ms。
    }

    public static void producerTest() {
        // 重要性高的配置项：
        //key.serialize：无默认值，必须指定。解释如上。
        //value.serialize：无默认值，必须指定。解释如上。
        //acks：默认值是1。解释如上。
        //bootstrap.servers：无默认值，必须指定。解释如上。
        //buffer.memory：缓冲区总大小，默认值是33554432，即32M。send就是把记录放到缓冲区中，如果缓冲区满了，send就会阻塞，当阻塞超过${max.block.ms}时，就会抛TimeoutException异常。max.block.ms默认值是60000，即1min。buffer.memory不能太小，如果连一条消息都缓存不了，会抛RecordTooLargeException异常。
        //compression.type：默认值是none。解释如上。
        //retries：默认值是Integer.MAX_VALUE。解释如上。
        //重要性中等的配置项：
        //batch.size：默认值是16384，即16k。解释如上。
        //linger.ms：默认值是0。解释如上
        //client.id：客户端id。
        //connections.max.idle.ms：默认值是540000，即9min。空闲连接最大存活时间，超过这个时间后会关闭。
        //request.timeout.ms：默认值是30000，即30s。解释如上。
        //delivery.timeout.ms：默认值是120000，即2min。解释如上。
        //max.block.ms：默认值是6000，即1min。解释如上。
        //max.request.size：默认值是1048576，即1M。解释如上。
        //send.buffer.bytes：默认值是131072，即128k。也被称为SO_SNDBUF，SO是socket的意思。解释如上。
        //receive.buffer.bytes：默认值是32768，即32k。也被称为SO_RCVBUF。解释如上。
        //partitioner.class：默认值是org.apache.kafka.clients.producer.internals.DefaultPartitioner。解释如上。
        //security.protocol：客户端和代理交互使用协议。默认值是PLAINTEXT。
        //socket.connection.setup.timeout.max.ms：默认值是30000，即30s。
        //socket.connection.setup.timeout.ms：默认值是10000，即10s。
        //在压测时，报org.apache.kafka.common.errors.TimeoutException: Expiring 20 record(s) for test-1:5001 ms has passed since batch creation
        //意思是从批次建好后，5000ms已经过去了。而5000ms是delivery.timeout.ms的值。
        // 实例化一个Properties对象，作为Kafka构造入参
        Properties properties = new Properties();

        // 设置kafka brokerIP地址列表，指定kafka集群的ip和端口，如值是192.168.56.100:9092,192.168.56.101:9092,192.168.56.102:9092，多个ip+端口用逗号分隔。
        properties.setProperty("bootstrap.servers", "192.168.126.130:9092");
        // key序列化：指定序列化类，org.apache.kafka.common.serialization.Serializer接口实现类，如org.apache.kafka.common.serialization.StringSerializer、org.apache.kafka.common.serialization.BytesSerializer。
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 指定ack应该机制
        // 0->生产者不等待分区的ACK应答机制，直接发送下一条消息
        // 1->Leader将数据保存之后，就会给Producer发送ack应答
        // -1(all)->分区所有副本将消息保存起来，才会给Producer发送ack应答
        // 默认ack模式为-1
        properties.setProperty("acks", "all");

        // 打开幂等性机制，幂等性+acks(all)=一次正好(Exactly Once)语义
        // 幂等性机制打开后，Kafka会自动将acks设置为all，所以打开幂等性acks可以设置也可以不设置
        //properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, Boolean.TRUE.toString());
        // 消息发送重试次数,默认retries = 2147483647
        properties.setProperty("retries", "3");

        // 在有重试的情况下，可能会影响分区消息的顺序性。如果要保证顺序性，必须设置max.in.flight.requests.per.connection值为1，默认是5。
        // 解释是：The maximum number of unacknowledged requests the client will send on a single connection before blocking.
        // Note that if this setting is set to be greater than 1 and there are failed sends, there is a risk of message re-ordering due to retries (i.e., if retries are enabled).
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "1");

        // 设置一个分区的缓冲大小
        // producer会为每一个主题的每一个分区都维护一个未发送记录的缓冲区。
        // 缓冲区的大小由batch.size指定，单位是byte，默认值是16384，即16K。缓冲区越大，sender线程单次向kafka服务器发送的消息就越多，这样对kafka服务器的请求数就会越少，对kafka服务器的压力就会小，当然缓冲区越大，占用的客户端内存也更多。
        // 默认情况下，只要缓冲区中有未发送的记录，就会立即发送。
        // 这是因为默认情况下，linger.ms值为0。为了减少请求次数，我们可以把linger.ms设置成一个大于0的值，如N，这样sender线程每次发送完消息后，就会等候N毫秒，以期望缓冲区有足够多的记录。
        // N毫秒后，或者缓冲区大小达到batch.size后，只要满足任意一个条件，sender线程就会发送消息。可以把batch.size理解成班车的核载人数，把linger.ms理解成班车的时间间隔。
        properties.setProperty("batch.size", "1024");

        // 缓冲区的总大小
        // 指定producer客户端缓冲区的总大小，单位是byte，默认值是33554432，即32M。
        // 如果消息发送到缓冲区的速度比从缓冲区发送到kafka服务器的速度快(可以通过设置batch.size和linger.ms都足够大来模拟这种情况)，那么缓冲区就会逐渐被用完。
        // 当缓冲区用完后，send()方法就会阻塞，当阻塞超过max.block.ms后，就会抛出TimeoutException。
        properties.setProperty("buffer.memory", "10240");

        // max.block.ms默认值是60000，即1分钟。
        // 其实除了send()方法会阻塞，partitionsFor()方法、initTransactions()方法、sendOffsetsToTransaction()方法、commitTransaction()方法、abortTransaction()方法也都会阻塞，
        // 最多阻塞${max.block.ms}，之后就会抛异常。
        properties.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, "120000");

        // 超时等待时间，即使缓冲区没到指定大小，到了超时时间也会将消息发送到Kafka
        // 同时设置batch.size和 linger.ms,就是哪个条件先满足就都会将消息发送出去
        // Kafka需要考虑高吞吐量与延时的平衡
        properties.setProperty("linger.ms", "10");

        // request.timeout.ms用于指定客户端等待请求响应的最大时间，默认值是30000，即30s。如果在超时之前没有收到响应，客户端将在必要时重新发送请求，或者在重试耗尽时失败请求。
        // If the response is not received before the timeout elapses the client will resend the request if necessary or fail the request if retries are exhausted.
        // 如果重试次数用尽，则请求失败。request.timeout.ms值应该比replica.lag.time.max.ms大。replica.lag.time.max.ms是个服务端配置，默认是30s。
        properties.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "120000");

        // delivery.timeout.ms，默认是120000，即2min。This limits the total time that a record will be delayed prior to sending,
        // the time to await acknowledgement from the broker (if expected), and the time allowed for retriable send failures.
        // The producer may report failure to send a record earlier than this config if either an unrecoverable error is encountered,
        // the retries have been exhausted, or the record is added to a batch which reached an earlier delivery expiration deadline.
        // delivery.timeout.ms包括消息在缓冲区的时间、发送后等待ack的时间和重试的时间。delivery.timeout.ms值应该大于等于request.timeout.ms+linger.ms。
        properties.setProperty(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, "130000");

        // 指定请求的最大大小，默认是1048576，即1M。有点小，可以放大点。此参数会限制生产者在单个请求中发送的批次数，以避免单次请求数据量太大。
        // 服务端也有相同功能的配置，socket.request.max.bytes，默认值是104857600，即100M。
        properties.setProperty(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "2048576");

        // send.buffer.bytes指定发送数据时tcp缓冲区的大小，默认值是131072，即128k。
        properties.setProperty(ProducerConfig.SEND_BUFFER_CONFIG, "131072");
        // receive.buffer.bytes指定读取数据时tcp缓冲区的大小，默认值是32768，即32k。
        properties.setProperty(ProducerConfig.RECEIVE_BUFFER_CONFIG, "131072");

        // 消息压缩类型，默认none，可选值有"gzip"、"snappy"、"lz4"、"zstd"。压缩的好处就是减少传输的数据量，减轻对网络传输的压力。
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");

        // 从kafka0.11版本开始，kafkaProducer支持两种额外的模式：幂等生产者和事务生产者。
        //幂等生产者增强了kafka的传输语义，由至少一次增强为刚好一次(at least once to exactly once)，这种情况下重试不会产生重复的消息。为了使用幂等生产者，只需把enable.idempotence设为true即可，默认是false。此时还需要3个关联配置，不然会抛ConfigException，即
        //max.in.flight.requests.per.connection必须小于等于5，
        //retries必须大于0，
        //acks必须为all。
        //幂等生产者和普通生产者的编程API是一样的。
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

        //事务生产者允许原子性地向多个分区或者主题发送消息。
        // 为了使用事务生产者，需要设置transactional.id。注意，设置了transactional.id后，就自动启用了幂等。
        // 事务生产者操作的主题的replication.factor最少为3，min.insync.replicas最少为2，在topic创建时指定。此外，该主题的消费者必须配置为只读取已提交的消息(read only committed messages)？？？
        //事务可以在单个生产者实例的多个会话
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "my-transactional-id");

        // 这么多属性写错怎么办？
        // ProducerConfig:将常用的属性以常量的形式定义好了，可以直接使用
        // 例如设置超时时间
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "10");

        // 实例化一个生产者对象，Kafka泛型是指消息的key和value类型
        // 在Kafka中，一条消息包含三个部分：key、value、Timestamp
        // key有填
        // 按照key进行哈希，相同key去一个partition。(如果扩展了partition的数量那么就不能保证了)
        // key没填
        // round-robin来选partition
        KafkaProducer<Object, String> kafkaProducer = new KafkaProducer<>(properties);
        for (int i = 0; i < 10; i++) {
            // 被发送的消息需要被封装到ProducerRecord对象中，构造入参：topic 主题，partition 分区，timestamp 时间戳，key 和 value
            // 这里只设置topic和value
            ProducerRecord<Object, String> producerRecord = new ProducerRecord<>("kraft-test", "this is test message-" + i);

            // 发送消息到指定主题
            // send是一个异步方法，不管是否发送成功
            // send并没有将消息立即发送到kafka，而是将消息放到了缓冲区，那么什么时候发送？当缓冲区满了或者到了超时时间，才会将缓冲中的消息批量发送给kafka
            // 为什么这样做？因为每次提交到kafka消息时候，都会产生网络IO，批量的处理可以降低这种消耗
            kafkaProducer.send(producerRecord, (recordMetadata, exception) -> {
                String topic = recordMetadata.topic();
                int partition = recordMetadata.partition();
                long offset = recordMetadata.offset();
                System.out.printf("topic=%s,partition=%s,offset=%s%n", topic, partition, offset);
            });

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 关闭资源（关闭生产者和集群的连接），该方法会做资源回收，必须关闭。如果不关闭，消费者接收不到消息。
        kafkaProducer.close();
    }

    /**
     * @author yuanjifan
     * @description 事务生产者用例：
     * 先调用生产者的initTransaction()方法，之后再beginTransaction()开启事务，send()，再调用commitTransaction()提交事务，假如抛KafkaException的话，就调用abortTransaction()中止事务。注意，initTransactions()方法仅可调用一次，且必须在调用其他事务方法之前。beginTransaction()、commitTransaction()是方法对，可以多次调用。
     * Sender、KafkaThread
     * 在new KafkaProducer()方法中，会创建一个后台Sender线程，并封装成kafkaThread后启动。Sender线程的run()方法中有个死循环：
     * while (running) {
     * try {
     * run(time.milliseconds());
     * } catch (Exception e) {
     * log.error("Uncaught error in kafka producer I/O thread: ", e);
     * }
     * }
     * @date 2022/12/21 15:47
     */
    public void transactionsTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.56.100:9092,192.168.56.101:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("linger.ms", 1000);
        props.put("max.block.ms", 5000);
        props.put("compression.type", "zstd");
        props.put("transactional.id", "my-transactional-id");
        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.initTransactions();
        try {
            producer.beginTransaction();
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<>("test", Integer.toString(i), Integer.toString(i)));
            }
            producer.commitTransaction();
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            // We can't recover from these exceptions, so our only option is to close the producer and exit.
            producer.close();
        } catch (KafkaException e) {
            // For all other exceptions, just abort the transaction and try again.
            producer.abortTransaction();
        }
        // producer.close();
    }

    public static void main(String[] args) {
        producerTest();
    }
}
