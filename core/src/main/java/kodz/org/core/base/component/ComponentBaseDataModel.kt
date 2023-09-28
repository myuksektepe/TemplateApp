package kodz.org.core.base.component

import kodz.org.core.model.ClickEventModel


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

/**
 * Komponentin ihtiyacı olan verilerin atandığı temel sınıf.
 * Bu sınıftan türetilen alt sınıflarda ilgili komponentin ihtiyacı
 * olan veriler için değişkenler oluşturulur.
 */
abstract class ComponentBaseDataModel() {
    abstract val clickEventModel: ClickEventModel?
}