package com.banyan.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.*;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.binary.BinaryObjectBuilder;
import org.apache.ignite.cache.CacheEntryProcessor;
import org.apache.ignite.cache.CachePeekMode;
import org.apache.ignite.cache.QueryEntity;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.compute.*;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import scala.Tuple2;

import javax.cache.Cache;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * User:krisjin
 * Date:2019/3/5
 *  
 */
@Slf4j
public class IgniteDemo {


    static Ignite ignite;

    // replace me
    static List<String> addresses = Lists.newArrayList("192.168.46.113:47500..47509",
            "192.168.46.114:47500..47509", "192.168.46.115:47500..47509");


    @BeforeClass
    public static void setup() {
        IgniteConfiguration igniteConfiguration = getIgniteConfiguration();
        ignite = Ignition.start(igniteConfiguration);
    }

    @Before
    public void prepare() {
        CacheConfiguration<Long, Foobar> cacheConf = createCacheConf();
        IgniteCache<Long, Foobar> foobarCache = ignite.getOrCreateCache(cacheConf);
        LocalDateTime now = LocalDateTime.now();
        Foobar input1 = new Foobar(1L, "hello1", 6, now);
        Foobar input2 = new Foobar(2L, "hello2", 7, now);
        Foobar input3 = new Foobar(3L, "hello3", 8, now);
        Foobar input4 = new Foobar(4L, "hello3", 9, now);

        foobarCache.put(1L, input1);
        foobarCache.put(2L, input2);
        foobarCache.put(3L, input3);
        foobarCache.put(4L, input4);
    }


    public static IgniteConfiguration getIgniteConfiguration() {
        IgniteConfiguration configuration = new IgniteConfiguration();
        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder tcpDiscoveryVmIpFinder = new TcpDiscoveryVmIpFinder();
        tcpDiscoveryVmIpFinder.setAddresses(addresses);
        tcpDiscoverySpi.setIpFinder(tcpDiscoveryVmIpFinder);

        configuration.setDiscoverySpi(tcpDiscoverySpi);
        configuration.setClientMode(true);
        configuration.setPeerClassLoadingEnabled(true);
        return configuration;
    }


    public CacheConfiguration<Long, Foobar> createCacheConf() {
        CacheConfiguration<Long, Foobar> cacheConf = new CacheConfiguration<>();
//        skuConf.setBackups(1);
        cacheConf.setName("foo");

        QueryEntity entity = new QueryEntity(Long.class, Foobar.class);
        entity.setKeyFieldName("id");
        entity.setKeyType(Long.class.getName());
        entity.setValueType(Foobar.class.getName());

        // fields
        LinkedHashMap<String, String> fields = Maps.newLinkedHashMap();
        fields.put("id", Long.class.getName());
        fields.put("name", String.class.getName());
        fields.put("val", Integer.class.getName());
        fields.put("dateTime", LocalDateTime.class.getName());

        entity.setFields(fields);
        cacheConf.setQueryEntities(Collections.singletonList(entity));
        return cacheConf;
    }

    @After
    public void tearDown() {
        ignite.getOrCreateCache("foo").destroy();
    }

    @Test
    public void testPutGet() {
        CacheConfiguration<Long, Foobar> cacheConf = createCacheConf();
        IgniteCache<Long, Foobar> foobarCache = ignite.getOrCreateCache(cacheConf);
        LocalDateTime now = LocalDateTime.now();
        Foobar input = new Foobar(1L, "hello", 6, now);
        foobarCache.put(1L, input);

//        assertThat(foobarCache.get(1L), samePropertyValuesAs(input));
    }

    @Test
    public void testJdbcSql() throws Exception {
        CacheConfiguration<Long, Foobar> cacheConf = createCacheConf();
        IgniteCache<Long, Foobar> foobarCache = ignite.getOrCreateCache(cacheConf);
        LocalDateTime now = LocalDateTime.now();
        Foobar input = new Foobar(1L, "hello", 6, now);
        foobarCache.put(1L, input);

        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
        try (Connection conn = DriverManager.getConnection("jdbc:ignite:thin://192.168.46.115");
             PreparedStatement preparedStatement = conn.prepareStatement("select * from \"foo\".\"FOOBAR\" where id =1")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            assertEquals(resultSet.getString("name"), input.getName());
            assertEquals(resultSet.getLong("id"), (long) input.getId());
            assertEquals(resultSet.getLong("val"), input.getVal());

        }
    }


    @Test
    public void testBroadCastModel() {
        CacheConfiguration<Long, Foobar> cacheConf = createCacheConf();
        IgniteCache<Long, Foobar> foobarCache = ignite.getOrCreateCache(cacheConf);
        IgniteConfiguration igniteConfiguration = getIgniteConfiguration();

        IgniteCluster cluster = ignite.cluster();
        ignite.compute(cluster.forRemotes()).broadcast(() -> {

            IgniteCache<Long, Foobar> fooCache = Ignition.getOrStart(igniteConfiguration).getOrCreateCache("foo");

            for (Cache.Entry<Long, Foobar> entry : fooCache.localEntries(CachePeekMode.PRIMARY)) {
                Foobar foobar = entry.getValue();
                foobar.setVal(foobar.getVal() + 1);
                fooCache.put(entry.getKey(), foobar);
            }
        });

        assertEquals(foobarCache.get(1L).getVal(), 7);
        assertEquals(foobarCache.get(2L).getVal(), 8);
        assertEquals(foobarCache.get(3L).getVal(), 9);
    }


    @Test
    public void testBroadCastBinary() {
        CacheConfiguration<Long, Foobar> cacheConf = createCacheConf();
        IgniteCache<Long, Foobar> foobarCache = ignite.getOrCreateCache(cacheConf);
        IgniteConfiguration igniteConfiguration = getIgniteConfiguration();

        IgniteCluster cluster = ignite.cluster();
        ignite.compute(cluster.forRemotes()).broadcast(() -> {

            IgniteCache<Long, BinaryObject> fooCache = Ignition.getOrStart(igniteConfiguration).getOrCreateCache("foo").withKeepBinary();

            for (Cache.Entry<Long, BinaryObject> entry : fooCache.localEntries(CachePeekMode.PRIMARY)) {
                BinaryObjectBuilder foobar = entry.getValue().toBuilder();

                Integer val = foobar.getField("val");
                foobar.setField("val", val + 1);

                fooCache.put(entry.getKey(), foobar.build());
            }
        });

        assertEquals(foobarCache.get(1L).getVal(), 7);
        assertEquals(foobarCache.get(2L).getVal(), 8);
        assertEquals(foobarCache.get(3L).getVal(), 9);
    }


    @Test
    public void testAffinityRunModel() {
        CacheConfiguration<Long, Foobar> cacheConf = createCacheConf();
        IgniteCache<Long, Foobar> foobarCache = ignite.getOrCreateCache(cacheConf);
        IgniteConfiguration igniteConfiguration = getIgniteConfiguration();

        int partitions = ignite.affinity("foo").partitions();
        IgniteCluster cluster = ignite.cluster();

        for (int i = 0; i < partitions; i++) {
            final int part = i;
            ignite.compute(cluster.forRemotes()).affinityRunAsync(ImmutableList.of("foo"), part, () -> {

                Ignite igniteLocal = Ignition.getOrStart(igniteConfiguration);

                IgniteCache<Long, Foobar> fooCache = igniteLocal.cache("foo");

                try (QueryCursor<Cache.Entry> cursor = fooCache.query(new ScanQuery(part))) {
                    for (Cache.Entry<Long, Foobar> entry : cursor) {
                        Foobar foobar = entry.getValue();
                        foobar.setVal(foobar.getVal() + 1);
                        fooCache.put(entry.getKey(), foobar);
                    }
                }
            });
        }

        assertEquals(7, foobarCache.get(1L).getVal());
        assertEquals(8, foobarCache.get(2L).getVal());
        assertEquals(9, foobarCache.get(3L).getVal());
        assertEquals(10, foobarCache.get(4L).getVal());

    }


    @Test
    public void testAffinityRunBinary() {
        CacheConfiguration<Long, Foobar> cacheConf = createCacheConf();
        IgniteCache<Long, Foobar> foobarCache = ignite.getOrCreateCache(cacheConf);
        IgniteConfiguration igniteConfiguration = getIgniteConfiguration();

        int partitions = ignite.affinity("foo").partitions();

        IgniteCluster cluster = ignite.cluster();

        for (int i = 0; i < partitions; i++) {
            final int part = i;
            ignite.compute(cluster.forRemotes()).affinityRunAsync(ImmutableList.of("foo"), part, () -> {

                Ignite igniteLocal = Ignition.getOrStart(igniteConfiguration);

                IgniteCache<Long, BinaryObject> fooCache = igniteLocal.cache("foo").withKeepBinary();

                try (QueryCursor<Cache.Entry> cursor = fooCache.query(new ScanQuery(part))) {
                    for (Cache.Entry<Long, BinaryObject> entry : cursor) {
                        BinaryObjectBuilder foobar = entry.getValue().toBuilder();
                        Integer val = foobar.getField("val");
                        foobar.setField("val", val + 1);
                        fooCache.put(Long.parseLong(foobar.getField("id").toString()), foobar.build());
                    }
                }
            });
        }

        assertEquals(7, foobarCache.get(1L).getVal());
        assertEquals(8, foobarCache.get(2L).getVal());
        assertEquals(9, foobarCache.get(3L).getVal());
        assertEquals(10, foobarCache.get(4L).getVal());

    }

    @Test
    public void testEntryProcessor() {
        CacheConfiguration<Long, Foobar> cacheConf = createCacheConf();
        IgniteCache<Long, Foobar> foobarCache = ignite.getOrCreateCache(cacheConf);

        HashSet<Long> ids = Sets.newHashSet(1L, 2L, 3L, 4L);

        foobarCache.invokeAll(
                ids, (CacheEntryProcessor<Long, Foobar, Object>) (entry, objects) -> {
                    Foobar foobar = entry.getValue();
                    foobar.setVal(foobar.getVal() + 1);
                    entry.setValue(foobar);
                    return null;
                });

        assertEquals(7, foobarCache.get(1L).getVal());
        assertEquals(8, foobarCache.get(2L).getVal());
        assertEquals(9, foobarCache.get(3L).getVal());
        assertEquals(10, foobarCache.get(4L).getVal());
    }


    @NoArgsConstructor
    public static class FooJob extends ComputeJobAdapter {
        List<Long> idList;

        public FooJob(List<Long> idList) {
            this.idList = ImmutableList.copyOf(idList);
        }

        @Override
        public Object execute() throws IgniteException {
            Ignite ignite = Ignition.ignite();
            IgniteCache<Long, Foobar> foobar = ignite.cache("foo");
            return idList.stream().mapToInt(id -> foobar.get(id).getVal()).sum();
        }
    }


    @NoArgsConstructor
    public static class FooTask extends ComputeTaskSplitAdapter<List<Long>, Integer> {
        @Override
        protected Collection<? extends ComputeJob> split(int gridSize, List<Long> arg) throws IgniteException {
            List<List<Long>> partitions = Lists.partition(arg, gridSize);
            return partitions.stream().map(p -> new FooJob(p)).collect(Collectors.toList());
        }

        @Nullable
        @Override
        public Integer reduce(List<ComputeJobResult> results) throws IgniteException {
            return results.stream().mapToInt(ComputeJobResult::getData).sum();
        }
    }


    @Test
    public void testSplitJoin() {
        IgniteConfiguration igniteConfiguration = getIgniteConfiguration();

        ComputeTaskFuture<Integer> future = ignite.compute(ignite.cluster().forRemotes())
                .executeAsync(FooTask.class, Lists.newArrayList(1L, 2L, 3L, 4L));
        Integer result = future.get();

        assertEquals(30, result.intValue());
    }


    @Test
    public void sparkTest() {
        CacheConfiguration<Long, Foobar> cacheConf = createCacheConf();
        IgniteConfiguration igniteConfiguration = getIgniteConfiguration();
        IgniteCache<Long, Foobar> foobarCache = ignite.getOrCreateCache(cacheConf);
        SparkConf conf = new SparkConf();

        conf.setAppName("WordCounter")//
                .setMaster("local");

        String fileName = "/Users/xiaweiran/3rdparty/spark-2.3.1-bin-hadoop2.7/LICENSE";

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile(fileName, 1);

        Random random = new Random();
        lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((e, acc) -> e + acc, 1)
                .map(e -> new Tuple2<>(e._1, e._2))
                .sortBy(e -> e._2, false, 1)

                .foreachPartition(tuple2Iterator -> {
                    while (tuple2Iterator.hasNext()) {
                        Tuple2<String, Integer> e = tuple2Iterator.next();
                        long l = random.nextLong();
                        StreamerSingleton.getIgniteDataStreamer().addData(l, new Foobar(l, e._1, e._2, LocalDateTime.now()));
                    }
                    StreamerSingleton.getIgniteDataStreamer().flush();
                });

        sc.close();
    }

    public static class StreamerSingleton {
        private static IgniteDataStreamer<Long, Foobar> streamer;

        public static IgniteDataStreamer<Long, Foobar> getIgniteDataStreamer() {
            if (streamer == null) {
                Ignite ignite = Ignition.ignite();
                streamer = ignite.dataStreamer("foo");
            }
            return streamer;
        }
    }

}
