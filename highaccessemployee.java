class Solution {
    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        Map<String, List<Integer>> times = new HashMap<>();

        for(final List<String> access_time : access_times) {
            times.putIfAbsent(access_time.get(0), new ArrayList<>());
            times.get(access_time.get(0)).add(Integer.valueOf(access_time.get(1)));
        }

        final List<String> result = new ArrayList<>();

        for(final Map.Entry<String, List<Integer>> entry : times.entrySet()) {
            if(entry.getValue().size() < 3)
                continue;

            Collections.sort(entry.getValue());

            for(int i = 2; i < entry.getValue().size(); ++i) {
                if(entry.getValue().get(i) - entry.getValue().get(i - 2) < 100) {
                    result.add(entry.getKey());
                    break;
                }
            }
        }

        return result;
    }
}