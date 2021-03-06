/*
 * Copyright (c) 2021 dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package panda.std;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents a conditional entry
 *
 * @param <T> type of value to process
 * @param <R> type of result value
 */
public class Case<T, R> {

    private final Predicate<T> condition;
    private final Function<T, R> value;

    private Case(Predicate<T> condition, Function<T, R> value) {
        this.condition = condition;
        this.value = value;
    }

    public Predicate<T> getCondition() {
        return condition;
    }

    public Function<T, R> getValue() {
        return value;
    }

    public static <T, R> Case<T, R> of(Predicate<T> predicate, Function<T, R> function) {
        return new Case<>(predicate, function);
    }

}
