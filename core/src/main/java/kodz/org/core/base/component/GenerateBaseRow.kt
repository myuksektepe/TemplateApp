package kodz.org.core.base.component

/**
 * Komponenti ekrana bastırmak için kullanılan temel sınıf.
 */
abstract class GenerateBaseRow<P : Any> {
    abstract fun execute(params: P): BaseRow
}