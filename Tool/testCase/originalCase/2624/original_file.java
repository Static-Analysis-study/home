
import java.util.*;
import java.util.concurrent.Callable;

public class Foo {
    private Map<Path, List<String>> joinFutures(Map<String, List<Future<String>>> map) {
        Map<String, List<String>> joined = new HashMap<>();

        for (String p : map.keySet()) {
            List<String> evaluatedResult = map.get(p).stream()
                                              .map(f -> {
                                                  try {
                                                      return f.get();
                                                  } catch (InterruptedException | ExecutionException e) {
                                                      e.printStackTrace();
                                                      return null; // <----- false positive here
                                                  }
                                              })
                                              .filter(Objects::nonNull)
                                              .sorted(Comparator.naturalOrder())
                                              .collect(Collectors.toList());

            if (!evaluatedResult.isEmpty()) {
                joined.put(p, evaluatedResult);
            }
        }

        // checking basic lambdas and anonymous classes as well
        Callable<Object> c = a -> { return null; }; // <----- false positive here
        Callable<Object> c2 = new Callable<Object>() {
            public Object call() {
                return null; // <----- false positive here
            }
        };

        Callable<List<String>> c3 = new Callable<List<String>>() {
            public List<String> call() {
                return null;
            }
        };

        return joined;
    }
}
    