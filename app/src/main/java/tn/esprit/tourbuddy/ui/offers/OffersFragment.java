package tn.esprit.tourbuddy.ui.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import tn.esprit.tourbuddy.databinding.FragmentForumBinding;
import tn.esprit.tourbuddy.databinding.FragmentOffersBinding;

public class OffersFragment extends Fragment {

    private FragmentOffersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OffersViewModel offersViewModel =
                new ViewModelProvider(this).get(OffersViewModel.class);

        binding = FragmentOffersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textOffers;
        offersViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}