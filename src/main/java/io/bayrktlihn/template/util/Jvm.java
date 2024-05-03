package io.bayrktlihn.template.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jvm {

    private static Map<String, String> passedSystemProperties;

    public static Map<String, String> getPassedSystemProperties() {
        if (passedSystemProperties == null) {
            synchronized (Jvm.class) {
                if (passedSystemProperties == null) {
                    RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

                    List<String> jvmArgs = runtimeMxBean.getInputArguments();

                    passedSystemProperties = new HashMap<>();

                    for (String arg : jvmArgs) {
                        if (arg.startsWith("-D")) {
                            String[] parts = arg.substring(2).split("=", 2);
                            if (parts.length == 2) {
                                String key = parts[0];
                                String value = parts[1];
                                passedSystemProperties.put(key, value);
                            } else if(parts.length == 1){
                                String key = parts[0];
                                passedSystemProperties.put(key, null);
                            }
                        }
                    }
                }
            }
        }

        return passedSystemProperties;
    }

}
