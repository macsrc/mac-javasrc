package numbers;

/** Compute the value of 2/3 of 5 */
// tag::main[]
public class MyFractMult04 {
    public static void main(String[] u) {

        var i6 = 5*2/3;
        System.out.println(i6);

        int i5 = 2*5/3;         // fast, approximate integer answer
        System.out.println(i5);
    }
}
// end::main[]
