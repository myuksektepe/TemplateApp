package kodz.org.core.base.handler


/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
interface ButtonEventHandler : BaseEventHandler {
    fun onButtonClick()
    fun onButtonLongClick() {}
}