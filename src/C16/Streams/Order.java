package C16.Streams;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Order {

    public static void main(String[] args) {
        IntConsumer printInt = n -> System.out.print(n + "|");
        IntStream.of(2018, 2019, 2020, 2021, 2022).forEach(printInt); // (3a)
//2018|2019|2020|2021|2022|
        IntStream.of(2018, 2019, 2020, 2021, 2022).parallel().forEach(printInt); // (3b)
//2020|2019|2018|2021|2022|

        IntStream.of(2018, 2019, 2020, 2021, 2022).forEachOrdered(printInt); // (3a)
//2018|2019|2020|2021|2022|
        IntStream.of(2018, 2019, 2020, 2021, 2022).parallel().forEachOrdered(printInt); // (3b)
//2018|2019|2020|2021|2022|
    }
}
