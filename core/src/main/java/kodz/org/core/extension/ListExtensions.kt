package kodz.org.core.extension

import kodz.org.core.common.ZERO

fun <T> List<T>.subSafeList(fromIndex: Int = ZERO, toIndex:Int) : List<T> {
    val itemCount = if (toIndex > this.size) this.size else toIndex
    return this.subList(fromIndex, itemCount)
}