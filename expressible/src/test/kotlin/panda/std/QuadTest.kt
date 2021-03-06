/*
 * Copyright (c) 2021 dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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

package panda.std

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals

class QuadTest {

    private val quad = Quad.of("test", "test", "test", "test")

    @Test
    fun `should keep order of associated values`() {
        assertEquals("test", quad.first)
        assertEquals("test", quad.second)
        assertEquals("test", quad.third)
        assertEquals("test", quad.fourth)
    }

    @Test
    fun `should display formatted values`() {
        assertEquals("['test', 'test', 'test', 'test']", quad.toString())
    }

    @Test
    @SuppressWarnings("ChangeToOperator")
    fun `should support equals & hashcode`() {
        assertEquals(quad, quad)
        assertFalse(quad.equals(null))
        assertFalse(quad.equals(Object()))

        val same = Quad.of("test", "test", "test", "test")
        assertEquals(same, quad)
        assertEquals(same.hashCode(), quad.hashCode())

        val different = Quad.of("other", "other", "other", "other")
        assertNotEquals(different, quad)
        assertNotEquals(different.hashCode(), quad.hashCode())
    }

}
