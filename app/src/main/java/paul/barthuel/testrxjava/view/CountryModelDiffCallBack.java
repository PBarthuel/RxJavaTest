package paul.barthuel.testrxjava.view;

import androidx.annotation.NonNull;

import paul.barthuel.testrxjava.model.CountryModel;

class CountryModelDiffCallBack extends androidx.recyclerview.widget.DiffUtil.ItemCallback<CountryModel> {

    @Override
    public boolean areItemsTheSame(@NonNull CountryModel oldItem, @NonNull CountryModel newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull CountryModel oldItem, @NonNull CountryModel newItem) {
        return false;
    }
}
