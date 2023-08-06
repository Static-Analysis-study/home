
public class Scratch {

    public static void main(String[] args) {
        Scratch scratch = new Scratch();

        scratch.callMethod();
    }

    void callMethod() {

        class InnerClass {
            private InnerClass() {
            }

            void display() {
                System.out.println("Works OK!");
            }
        }

        InnerClass innerClass = new InnerClass();
        innerClass.display();
    }
}
        