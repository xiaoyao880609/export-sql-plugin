import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
@SuppressWarnings("unchecked")
public static void main(String[] args) {
    try {
        Map<String, Object> map = new HashMap<>();
        for (int i=0;i<1000000;i++) {
            map.put("k_"+i, "v_"+i);
        }
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        ObjectMapper mapper = new ObjectMapper();
        long beginMillis = System.currentTimeMillis();
        long beginHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        long beginNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        String fastJson = JSON.toJSONString(map);
        long endMillis = System.currentTimeMillis();
        long endHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        long endNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        System.out.println("fastjson -> obj 2 json: " + (endMillis - beginMillis) + "ms");
        System.err.println("fastjson -> heap used: " + (endHeapUsed - beginHeapUsed) + "");
        System.err.println("fastjson -> non heap used: " + (endNonHeapUsed - beginNonHeapUsed) + "");
        Thread.sleep(10000);
        beginMillis = System.currentTimeMillis();
        beginHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        beginNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        String jackJson = mapper.writeValueAsString(map);
        endMillis = System.currentTimeMillis();
        endHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        endNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        System.out.println("jackson -> obj 2 json： " + (endMillis - beginMillis) + "ms");
        System.err.println("jackson -> heap used: " + (endHeapUsed - beginHeapUsed) + "");
        System.err.println("jackson -> non heap used: " + (endNonHeapUsed - beginNonHeapUsed) + "");
        Thread.sleep(10000);
        beginMillis = System.currentTimeMillis();
        beginHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        beginNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        ParserConfig config = new ParserConfig() ;
        config.setAutoTypeSupport(true);
        map = JSON.parseObject(fastJson ,HashMap.class, config);
        endMillis = System.currentTimeMillis();
        endHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        endNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        System.out.println("fastjson -> json 2 obj: " + (endMillis - beginMillis) + "ms");
        System.err.println("fastjson -> heap used: " + (endHeapUsed - beginHeapUsed) + "");
        System.err.println("fastjson -> non heap used: " + (endNonHeapUsed - beginNonHeapUsed) + "");
        Thread.sleep(10000);
        beginMillis = System.currentTimeMillis();
        beginHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        beginNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        map = mapper.readValue(jackJson, HashMap.class);
        endMillis = System.currentTimeMillis();
        endHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        endNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        System.out.println("jackson -> json 2 obj： " + (endMillis - beginMillis) + "ms");
        System.err.println("jackson -> heap used: " + (endHeapUsed - beginHeapUsed) + "");
        System.err.println("jackson -> non heap used: " + (endNonHeapUsed - beginNonHeapUsed) + "");
    } catch (Exception e) {
        e.printStackTrace();
    }
    
}
}
