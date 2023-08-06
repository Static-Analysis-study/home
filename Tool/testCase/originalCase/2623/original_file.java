
  import java.util.List;

  public class Foo {
      private List<Integer> foo(List<Integer> list) {
          for(Integer i : list) {
              if(condition) {
                  return null;
              }
          }
          return list;
      }
  }
    