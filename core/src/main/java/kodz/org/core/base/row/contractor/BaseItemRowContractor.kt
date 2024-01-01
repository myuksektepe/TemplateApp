package kodz.org.core.base.row.contractor

abstract class BaseItemRowContractor(
) : BaseRowContractor() {
    abstract val isInCarousel: Boolean?
    abstract val isInList: Boolean?
}