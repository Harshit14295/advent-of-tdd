package org.advent.day1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TestElfShould {
    @Test
    void have_zero_total_calories_when_created() {
        Elf elf = new Elf();
        assertThat(elf.getTotalCalories(), equalTo(0));
    }

    @Test
    void have_1000_total_calories_after_adding_1000() {
       Elf elf = new Elf();
       elf.addCalories(1000);
       assertThat(elf.getTotalCalories(), equalTo(1000));
    }

    @Test
    void compare_return_positive_value_for_elf_with_more_total_calories() {
        Elf elf1 = new Elf();
        Elf elf2 = new Elf();

        elf1.addCalories(1000);
        elf2.addCalories(2000);

        assertThat(elf2.compareTo(elf1), greaterThan(0));
    }

    @Test
    void compare_zero_for_equal_elf() {
        Elf elf1 = new Elf();
        Elf elf2 = new Elf();

        elf1.addCalories(1000);
        elf2.addCalories(1000);

        assertThat(elf2.compareTo(elf1), equalTo(0));
    }

    @Test
    void compare_return_negative_value_for_elf_with_less_total_calories() {

        Elf elf1 = new Elf();
        Elf elf2 = new Elf();

        elf1.addCalories(1000);
        elf2.addCalories(200);

        assertThat(elf2.compareTo(elf1), lessThan(0));
    }

    @ParameterizedTest
    @MethodSource("input_for_total_calories_must_be_sum_of_all_for_an_elf_test")
    void total_calories_must_be_sum_of_all_for_an_elf(List<Integer> inputCalories, int expectedTotalCalorie) {
        Elf elf = new Elf();
        inputCalories.forEach(elf::addCalories);

        assertThat(elf.getTotalCalories(), equalTo(expectedTotalCalorie));
    }

    private static Stream<Arguments> input_for_total_calories_must_be_sum_of_all_for_an_elf_test() {
        return Stream.of(
                Arguments.of(Arrays.asList(1000, 2000, 3000), 6000),
                Arguments.of(Arrays.asList(4000), 4000),
                Arguments.of(Arrays.asList(5000, 6000), 11000),
                Arguments.of(Arrays.asList(7000, 8000, 9000), 24000),
                Arguments.of(Arrays.asList(10000), 10000)
        );
    }
}
