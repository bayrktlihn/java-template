package io.bayrktlihn.template.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jvm {

    public static Map<String, String> getPassedSystemProperties() {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

        List<String> jvmArgs = runtimeMxBean.getInputArguments();

        Map<String, String> passedSystemProperties = new HashMap<>();

        for (String arg : jvmArgs) {
            if (arg.startsWith("-D")) {
                String[] parts = arg.substring(2).split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];
                    passedSystemProperties.put(key, value);
                }
            }
        }
        return passedSystemProperties;
    }

}
