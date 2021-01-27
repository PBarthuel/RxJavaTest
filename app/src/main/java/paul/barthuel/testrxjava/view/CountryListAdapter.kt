package paul.barthuel.testrxjava.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import paul.barthuel.testrxjava.R
import paul.barthuel.testrxjava.retrofit.CountryModel

class CountryListAdapter : ListAdapter<CountryModel, CountryListAdapter.ViewHolder>(CountryModelDiffCallBack()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_country, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.bind(getItem(index))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTextViewCountryName = itemView.findViewById<TextView>(R.id.country_item_tv_country_name)
        private val mTextViewCapital = itemView.findViewById<TextView>(R.id.country_item_tv_capital)
        private val mImageViewFlag = itemView.findViewById<ImageView>(R.id.country_item_iv_flag)

        fun bind(countryModel: CountryModel?) {

            mTextViewCountryName.text = countryModel?.countryName
            mTextViewCapital.text = countryModel?.capital
            Glide.with(mImageViewFlag).load(countryModel?.flag).into(mImageViewFlag)
        }


    }
}