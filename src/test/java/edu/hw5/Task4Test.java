package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @ParameterizedTest
    @DisplayName("Task4Test: Correct Input")
    @CsvSource({
        "asdnj~am845defkj",
        "asj123ijjkn!asd",
        "zxip012auij@",
        "pqwnwuje#sda",
        "smn2$19n34n",
        "%uiabnsn189",
        "naub0n40^kasm",
        "anw712&malsdm",
        "шф0817ьячс*ьф1",
        "129381*2",
        "~!@#$%^&*|asdaiskm123312"
    })
    void shouldCheckIsPasswordHasSpecialSymbolAndReturnTrue(String password) {
        boolean calculated = Task4.isPasswordHasSpecialSymbol(password);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Task4Test: Incorrect Input")
    @CsvSource({
        "asdasdfg123",
        "фывырапоао",
        "..a.s23523",
        "asdasghsddfg",
        "/asdaskma"
    })
    void shouldCheckIsPasswordHasSpecialSymbolAndReturnFalse(String password) {
        boolean calculated = Task4.isPasswordHasSpecialSymbol(password);
        assertThat(calculated).isFalse();
    }
}
