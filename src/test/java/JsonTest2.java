import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest2 {
@SuppressWarnings("unchecked")
public static void main(String[] args) {
    try {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (int i=0;i<5000000;i++) {
            map.put("k_"+i, "v_"+i);
        }
        list.add(map);
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        ObjectMapper mapper = new ObjectMapper();
        long beginMillis = System.currentTimeMillis();
        long beginHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        long beginNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        String jackJson = mapper.writeValueAsString(list);
        long endMillis = System.currentTimeMillis();
        long endHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        long endNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        System.out.println("jackson -> obj 2 json： " + (endMillis - beginMillis) + "ms");
        System.err.println("jackson -> heap used: " + (endHeapUsed - beginHeapUsed) + "");
        System.err.println("jackson -> non heap used: " + (endNonHeapUsed - beginNonHeapUsed) + "");
        beginMillis = System.currentTimeMillis();
        beginHeapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        beginNonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        list = mapper.readValue(jackJson, new TypeReference<List<Map<String, Object>>>(){});
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
