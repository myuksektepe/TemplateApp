package kodz.org.core.domain.extensions

import kodz.org.core.domain.consts.ZERO

fun <T> List<T>.subSafeList(fromIndex: Int = ZERO, toIndex:Int) : List<T> {
    val itemCount = if (toIndex > this.size) this.size else toIndex
    return this.subList(fromIndex, itemCount)
}