package kodz.org.core.base.row.contractor

abstract class BaseItemRowContractor(
) : BaseRowContractor() {
    abstract val isInSlider: Boolean?
    abstract val isInList: Boolean?
}