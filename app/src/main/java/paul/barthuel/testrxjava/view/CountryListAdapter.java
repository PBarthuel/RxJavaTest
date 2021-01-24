package paul.barthuel.testrxjava.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import paul.barthuel.testrxjava.R;
import paul.barthuel.testrxjava.model.CountryModel;

public class CountryListAdapter extends ListAdapter<CountryModel, CountryListAdapter.ViewHolder> {

    protected CountryListAdapter() {
        super(new CountryModelDiffCallBack());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_country, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {
        viewHolder.bind(getItem(index));
    }

     static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextViewCountryName;
        private final TextView mTextViewCapital;
        private final ImageView mImageViewFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewCountryName = itemView.findViewById(R.id.country_item_tv_country_name);
            mTextViewCapital = itemView.findViewById(R.id.country_item_tv_capital);
            mImageViewFlag = itemView.findViewById(R.id.country_item_iv_flag);
        }

         public void bind(final CountryModel countryModel) {

            mTextViewCountryName.setText(countryModel.getCountryName());
            mTextViewCapital.setText(countryModel.getCapital());
             Glide.with(mImageViewFlag).load(countryModel.getFlag()).into(mImageViewFlag);
         }
     }
}
