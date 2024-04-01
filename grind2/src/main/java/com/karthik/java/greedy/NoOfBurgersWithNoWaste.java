package com.karthik.java.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoOfBurgersWithNoWaste {

    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        /*
          If all tomatoSlices and cheeseSlices can be used to make Jumbo and Small burgers, that means
          1. No. of tomatoSlices should be even.
          2. No. of tomatoSlices >= 2*cheeseSlices and No. of tomatoSlices <= 4* cheeseSlices
          3. cheeseSlices = no. of Jumbo Burgers + no. of small burgers
          4. 1 Jumbo Burger contains 4 tomatoSlices. So, 2 * no. of Jumbo Burgers would contain : (tomatoSlices used for JumboBurger/2)
             1 Small burger contains 2 tomatoSlices. So No. of small burgers = tomatoSlices used for Small Burgers/2

             2*Jumbo Burgers + SmallBurgers = (No. of tomato slices used for Jumbo Burgers / 2) + (No. of tomato slices for Small Burgers/2)

             No. of tomato Slices = No. of tomato Slices for Jumbo Burger + No. of tomato Slices for Small Burger

             therefore, 2*Jumbo Burgers + Small Burgers = No. of Tomato Slices/2

          5. We have 2 equations with 2 variables
             No. of Jumbo Burgers + No. of Small Burgers = cheeseSlices
             2*No. of Jumbo Burgers + No. of Small Burgers = tomatoSlices/2

             Therefore : -

             No. of Jumbo Burgers = (tomatoSlices/2) - cheeseSlices
             No. of Small Burgers = 2*cheeseSlices - (tomatoSlices/2)
         */
        return (tomatoSlices % 2 == 0) && (tomatoSlices >= 2 * cheeseSlices && tomatoSlices <= 4 * cheeseSlices)
                ? Arrays.asList(tomatoSlices / 2 - cheeseSlices, 2 * cheeseSlices - (tomatoSlices / 2))
                : new ArrayList<>();
    }
}
