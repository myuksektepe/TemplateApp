package kodz.org.core.base.row.datamodel

import kodz.org.core.domain.models.ClickEventModel


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
abstract class BaseRowDataModel() {
    abstract val clickEventModel: ClickEventModel?
}