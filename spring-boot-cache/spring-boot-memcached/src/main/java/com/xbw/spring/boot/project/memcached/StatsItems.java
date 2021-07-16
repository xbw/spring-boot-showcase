package com.xbw.spring.boot.project.memcached;

import com.whalin.MemCached.MemCachedClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Deprecated
class StatsItems {
    private String server;
    private List<StatsItem> statsItems;

    public StatsItems() {
    }

    public StatsItems(String server) {
        this.server = server;
    }

    public static void main(String[] args) {
        MemcachedManager manager = MemcachedManager.getInstance();
        for (int i = 0; i < 10; i++) {
            manager.merge(i + "", new Random().nextFloat());
        }

        List<StatsItems> list = getAllCache(manager.getCache());
        System.out.println(list);
    }

    /**
     * 缓存遍历
     *
     * @return
     */
    public static List<StatsItems> getAllCache(MemCachedClient cache) {
        List<StatsItems> list = new ArrayList<>();
        // 遍历statsItems 获取items:1:number=?
        Map<String, Map<String, String>> statsItemsMap = cache.statsItems();

        statsItemsMap.forEach((server, statsItemMap) -> {
//            System.out.printf("server: %s, statsItemMap: %s%n", server, statsItemMap);
            StatsItems statsItems = new StatsItems(server);

            List<StatsItem> statsItemList = new ArrayList<>();
            statsItemMap.forEach((statsItemKey, statsItemValue) -> {
                if (statsItemKey.startsWith("items:") && statsItemKey.endsWith(":number")) {
                    StatsItem statsItem = new StatsItem(statsItemKey);
//                    System.out.printf("statsItemKey: %s, statsItemValue: %s%n", statsItemKey, statsItemValue);
                    // 根据items:1:number=?，调用statsCacheDump，获取每个item中的key
                    Map<String, Map<String, String>> statsCacheDump = cache.statsCacheDump(new String[]{server},
                            Integer.parseInt(statsItemKey.split(":")[1]),
                            Integer.parseInt(statsItemValue.trim()));

                    List<StatsItem.Stats> statsList = new ArrayList<>();
                    statsCacheDump.forEach((statsCacheDumpKey, statsCacheDumpValue) -> { // loop 1
//                        System.out.printf("statsCacheDumpKey: %s, statsCacheDumpValue: %s%n", statsCacheDumpKey, statsCacheDumpValue);
                        statsCacheDumpValue.forEach((statsCacheDumpSubKey, statsCacheDumpSubValue) -> {
//                            System.out.printf("statsCacheDumpSubKey: %s, statsCacheDumpSubValue: %s%n", statsCacheDumpSubKey, statsCacheDumpSubValue);
                            StatsItem.Stats stats = new StatsItem.Stats();
                            stats.setKey(statsCacheDumpSubKey);
                            stats.setProps(statsCacheDumpSubValue.replaceAll("\r|\n", ""));
                            stats.setValue(cache.get(statsCacheDumpSubKey));
                            statsList.add(stats);
                        });
                    });

                    statsItem.setStats(statsList);
                    statsItemList.add(statsItem);
                }
            });


            statsItems.setStatsItems(statsItemList);
            list.add(statsItems);
        });

        return list;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public List<StatsItem> getStatsItems() {
        return statsItems;
    }

    public void setStatsItems(List<StatsItem> statsItems) {
        this.statsItems = statsItems;
    }

    static class StatsItem {
        private String item;
        private List<Stats> stats;

        public StatsItem(String item) {
            this.item = item;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public List<Stats> getStats() {
            return stats;
        }

        public void setStats(List<Stats> stats) {
            this.stats = stats;
        }

        static class Stats {
            private String key;
            private Object value;
            private String props;


            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getProps() {
                return props;
            }

            public void setProps(String props) {
                this.props = props;
            }

            public Object getValue() {
                return value;
            }

            public void setValue(Object value) {
                this.value = value;
            }
        }
    }

}
