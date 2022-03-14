package io.github.rkeeves.scene.lambdas;

import org.junit.Test;

public class LambdasTest {

    @Test
    void test() {
        new Scene().on(scene -> {
           scene.table(table -> {
              table.hasRowCount(1);
              table.find(f -> f.findByName("A"))
                      .result(row -> {
                         row.age().is(10);
                      });
           });
        });
    }
}
