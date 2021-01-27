package paul.barthuel.testrxjava.view

import androidx.recyclerview.widget.DiffUtil
import paul.barthuel.testrxjava.retrofit.CountryModel

internal class CountryModelDiffCallBack : DiffUtil.ItemCallback<CountryModel>() {
    override fun areItemsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
        return false
    }
}