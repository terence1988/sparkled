package io.sparkled.model.util

import com.querydsl.core.Tuple

/**
 * Helper functions for tuples.
 */
object TupleUtils {

    /**
     * Retrieves an integer from a tuple. Importantly, this method works when the raw tuple value is a Long.
     * @param tuple The tuple from which to extract an integer value.
     * @param index The tuple index (0-based).
     * @return The tuple value as an integer, or 0 if no value exists.
     */
    fun getInt(tuple: Tuple, index: Int): Int {
        val value = tuple.get(index, Number::class.java)
        return (value ?: 0).toInt()
    }

    /**
     * @param tuple The tuple from which to extract a string value.
     * @param index The tuple index (0-based).
     * @return The tuple value as a string, or an empty string if no value exists.
     */
    fun getString(tuple: Tuple, index: Int): String {
        val value = tuple.get(index, String::class.java)
        return value ?: ""
    }
}
