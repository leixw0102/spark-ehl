package com.ehl.webservice.kafka;

import com.google.common.primitives.Longs;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;
import scala.collection.parallel.ParIterableLike;

import java.util.List;
import java.util.Map;

/**
 * Created by 雷晓武 on 2017/8/7.
 */
public class EhlPartition implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        List<PartitionInfo> partitionList = cluster.partitionsForTopic(topic);
        int partitionSize = partitionList.size();

        if(null != keyBytes){
            Long kLong  = Longs.fromByteArray(keyBytes);

            List<PartitionInfo> availableInfo = cluster.availablePartitionsForTopic(topic);
            if(null == availableInfo || availableInfo.size()>0){
                int part = (int) (kLong % availableInfo.size());
                return availableInfo.get(part).partition();
            }else{
                return (int) (kLong % partitionSize);
            }

        }
        return value.hashCode()%partitionSize;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
